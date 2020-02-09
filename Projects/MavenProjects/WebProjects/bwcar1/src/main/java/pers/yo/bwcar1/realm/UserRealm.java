package pers.yo.bwcar1.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.yo.bwcar1.pojo.SysUser;
import pers.yo.bwcar1.service.MenuService;
import pers.yo.bwcar1.service.RoleService;
import pers.yo.bwcar1.service.SysUserService;

import java.util.List;

@Component //【不要忘了！】此类 交给Spring容器来托管(由Spring容器生成bean对象)
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService suService;  //根据user_id 查询 role_id
    @Autowired
    private RoleService rService;  //根据 role_id 查询 menu_id
    @Autowired
    private MenuService mService; //根据menu_id，查出权限perms

    @Override //Authentication 认证方式
    /* 传入的形参：来自前端的令牌(暗号) authenticationToken
     * 关于Token的解释，见 P0-4-2 Token令牌 MD文档
     *
     * 认证用户提交的信息AuthenticationToken对象，AuthenticationToken包含了身份和凭证。
     * 如果认证成功，则返回AuthenticationInfo-接口类型 的实现类对象SimpleAuthenticationInfo
     * AuthenticationInfo 实现类对象SimpleAuthenticationInfo 代表了用户在Shiro中已经被认证过的账户数据。
     *
     * // 注：AuthenticationInfo-接口类型，认证信息。即认证成功之后返回该信息。最常用的实现类是 SimpleAuthenticationInfo
     *
     * 如果认证失败则抛出一下异常
     * @see ExpiredCredentialsException     凭证过期
     * @see IncorrectCredentialsException   凭证错误
     * @see ExcessiveAttemptsException      多次尝试失败
     * @see LockedAccountException          账户锁定
     * @see ConcurrentAccessException       并发访问异常(多点登录)
     * @see UnknownAccountException         账户未知
     */
    //注：AuthenticationInfo-接口类型，认证信息。即认证成功之后返回该信息。最常用的实现类是 SimpleAuthenticationInfo
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //---token从controller中调用login(UsernamePasswordToken)传递过来
        //---向下转型为 【能直接使用的实现类】u_p_Token ——封装了从前端传递的用户名和密码(用户输入)
        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;
        String username_in_token = upToken.getUsername(); //取出 token令牌中的 用户名
        //注意：public char[] getPassword()方法返回的是 字符数组char[]，需强制类型转换
        String password_in_token = new String( upToken.getPassword() ); //取出 token令牌中的 密码

        //根据令牌中的用户名username_in_token，从数据库中找到这个SysUser对象为su_in_SQL
        SysUser su_in_SQL = suService.findByUsername( username_in_token );
        if( su_in_SQL==null ){
            throw new UnknownAccountException( "帐户不存在嗷！" );
        }
        if( su_in_SQL.getStatus()==0 ){ //判断用户的状态
            throw new LockedAccountException("账户被冻结");
        }
        if(  !password_in_token.equals( su_in_SQL.getPassword() )  ){ //令牌中的密码 与【从数据库查出来的密码】不匹配
            throw new IncorrectCredentialsException( "密码不正确嗷！" );
        }

        /* 到了这里：以上的if条件都通过检查了，此时身份认证成功：将用户信息封装成 SimpleAuthenticationInfo
        * 注：返回值AuthenticationInfo-接口类型，认证信息。即认证成功之后返回该信息。最常用的实现类是 SimpleAuthenticationInfo
        *  */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                su_in_SQL, //principal 认证的实体信息，可以是传过来的 user 对象，也可以是 username 等；
                password_in_token, //认证成功的用户密码
                //这里的用户密码 可以是令牌中的密码、也可以是数据库查出来的密码。因为认证成功说明令牌密码==数据库查出来的密码了！
                this.getName()  //当前自定义Realm对象的名称，调用父类的getName()方法：直接this.getName()获取了！
        );
        return info;
    }


    @Override //Authorization 授权
    //principalCollection 身份(用户名)的集合
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //---1.通过principals获取已经认证完毕的用户名(用户对象)
        SysUser su = (SysUser)principalCollection.getPrimaryPrincipal();
        Long userId = su.getUserId(); //获取用户ID
        //---2.根据用户名(或用户ID)去数据库中查询该认证用户下角色(或权限信息)
        List<String> roles_ByUserId = rService.findRolesByUserId( userId );
        //---同时，根据用户ID查询用户的菜单权限
        List<String> perms_ByUserId = mService.findPermsByUserId( userId );

        //---3.把角色(或权限信息)封装进SimpleAuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles( roles_ByUserId );
        info.addStringPermissions( perms_ByUserId );
        //---4.返回AuthorizationInfo接口的实现类 SimpleAuthorizationInfo
        return info;
    }
}
