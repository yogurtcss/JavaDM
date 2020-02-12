package pers.yo.bwcar1.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.yo.bwcar1.dto.UserDTO;
import pers.yo.bwcar1.pojo.SysUser;
import pers.yo.bwcar1.service.SysUserService;
import pers.yo.bwcar1.utils.MD5Utils;
import pers.yo.bwcar1.utils.R;  //工具类R(即Response)：设置响应的状态码code、和提示(报错)信息msg
import pers.yo.bwcar1.utils.ShiroUtils;

import javax.imageio.ImageIO;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private SysUserService suService;

    @Autowired
    private DefaultKaptcha kaptcha;

    @RequestMapping( "/captcha.jpg" )
    public void captcha( HttpServletResponse response ){
        //缓存设置为：不缓存
        response.setHeader( "Cache-Control", "no-store,no-cache" );
        //设置响应内容
        response.setContentType( "image/jpg" );
        //生成验证码
        String text = kaptcha.createText(); //文本
        //生成图片
        BufferedImage image = kaptcha.createImage( text );
        //验证码存储到Shiro的session中
        ShiroUtils.setKaptcha( text );
        try{
            ServletOutputStream ops = response.getOutputStream();
            ImageIO.write( image, "jpg", ops );
        }catch( IOException e ){
            e.printStackTrace();
        }
    }


    /* 2020-02-10 10:14:31 控制器中关于Shiro登录拦截的流程：controller层方法 login_in_controller()
    * (1) 前台请求request 传入用户名、密码、是否记住我 ——存储在数据传输对象DTO中  → → →
    *       ▲ Shiro提供rememberMe功能，所以令牌token可以设置rememberMe功能：token.setRememberMe(true)
    * (2) 在login_in_controller()方法中，把(1)中DTO的数据存入 UsernamePasswordToken类的token(令牌)对象中 → → →
    * (3) SecurityUtils.getSubject()得到当前请求登录的主体(即用户)subject → → →
    * (4) 主体subject拿着令牌token登录：调用subject.login(token)方法  → → →
    * (4) 【调用login()之后】Shiro 会委托 SecurityManager 进行身份认证  → → →
    * (5) 【然后】SecurityManager又会交给认证器 Authenticator 根据认证策略 Authentication strategy 进行认证逻辑 → → →
    * (6) Authentication strategy 认证策略就是有一个或者多个 realm 域来实现  → → →
    * (7) 因此最终的认证逻辑是写在我们自定义的 realm 域中，
    *  */

    @RequestMapping("/sys/login") //前端login.html发送ajax请求的目的地，开头要带斜杠啊！
    @ResponseBody
    /* 前端login.html发送过来的是JSON数据user →  data: JSON.stringify(vm.user),
    * 所以后台方法这里的形参 要加注解 @RequestBody
    *  */
    public R login( @RequestBody UserDTO userDTO ){
        /* 注意，工具类R 继承了 HashMap集合
        public R() {  //重写了无参构造方法
            put("code", 0); // 状态码0表示：无异常，成功的标志
	    }
        public static R ok() { //静态方法：ok()，返回的是一个 工具类R对象
            return new R();
        }
        *  */
        //System.out.println( userDTO.getUsername() );

        //2020-02-09 21:09:43 对比验证码
        String serverKaptcha = ShiroUtils.getKaptcha();
        //用户填写的验证码userDTO.getCaptcha()与服务器端的验证码，忽略大小写 比较
        if( !userDTO.getCaptcha().equalsIgnoreCase(serverKaptcha) ){
            return R.error("验证码错误");
        }

        //----------构造令牌token，开始

        //我认为这里不应该先弹出来用户subject，要等令牌token构造出来之后，再创建subject出来！！
        //Subject subject = SecurityUtils.getSubject(); //主体，即当前请求登录的用户，也可能是一个运行的程序

        /* 构造新的加密字符串newPass，作为令牌token中的 "password"

        ▲ 新的加密字符串newPass 怎么来的？
        用户注册或登录时，系统对输入的密码进行加密，
        此处使用 MD5 算法，“密码 + 盐（用户名 + 随机数）” 的方式生成散列值
        如 加密密码 “ admin ”， 则 产生的散列值是 “ 21232f297a57a5a743894a0e4a801fc3 ”，

        //高强度加密算法,不可逆
        public  static String md5(String source,String salt,int hashIterations){
            if (source==null){
                return null;
            }
            Md5Hash md5Hash = new Md5Hash(source,salt,hashIterations); // 加密为md5Hash的内容
            return  md5Hash.toString(); //返回加密内容的字符串
        }
        *  */
        String newPass = MD5Utils.md5( //加密用户名和密码
                userDTO.getPassword(), //String source 原本的内容(即密码)
                userDTO.getUsername(), //String salt (干扰的)盐 ——对原数据撒盐，加大干扰，更难破解
                1024    //int hashIterations 散列迭代次数
        );
        //构造方法 public UsernamePasswordToken(String username, String password)
        UsernamePasswordToken token = new UsernamePasswordToken( //构造令牌的内容：原本的用户名+加密后的字符串作为新密码
                userDTO.getUsername(), //原本的用户名
                newPass //以加密后的字符串作为新密码new_password
        );
        if( userDTO.isRememberMe() ){ //数据传输对象中：是否记住我？
            token.setRememberMe(true); //Shiro提供rememberMe功能，
            // 所以令牌token可以设置rememberMe功能：token.setRememberMe(true)
        };
        //----------构造令牌token，结束

        //令牌token搞出来后，可以创建主体subject了！
        Subject subject = SecurityUtils.getSubject(); //主体，即当前请求登录的用户，也可能是一个运行的程序
        subject.login(token); //拿着令牌登录喽！
        // 此时会进行身份验证：调用自定义Realm类中的doGetAuthenticationInfo(token)方法

        return( R.ok() );
    }

    //2020-02-10 11:34:34
    @RequestMapping("/logout")
    public String logout(){
        ShiroUtils.logout();
        return "redirect:login.html";
    }

    @RequestMapping("/sys/user/info")
    @ResponseBody
    public R userInfo(){
        SysUser userEntity = ShiroUtils.getUserEntity(); //可以从Shiro中获取
        return( R.ok().put("user",userEntity) ); //键名"user"是前端指定的！！
        //记得把原本获取静态数据的get请求注释掉，还原动态的get请求
    }



}
