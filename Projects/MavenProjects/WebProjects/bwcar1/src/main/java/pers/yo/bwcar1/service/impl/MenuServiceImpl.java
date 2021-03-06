package pers.yo.bwcar1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.yo.bwcar1.dao.SysMenuMapper;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.SysMenu;
import pers.yo.bwcar1.service.MenuService;
import pers.yo.bwcar1.utils.R;

import java.util.*;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /* 2020-02-07 10:34:13
    * 关于PageHelper工具类、PageInfo分页信息类的使用，见MD文档！
    *  */
    @Override
    @RequiresPermissions( "sys:menu:list" ) //指定具有某种具体的权限
    //具体的权限控制 @RequiresPermissions() 写在每个具体的子类ServiceImpl的每个方法上
    public DataGridResult findMenu(QueryDTO queryDTO) {
        /* 在你需要进行分页的 MyBatis 查询方法前调用 PageHelper.startPage 静态方法即可，
        * 紧跟在这个方法后的第一个MyBatis 查询方法会被进行分页。
        * 只要你可以保证在 PageHelper 方法调用后紧跟 MyBatis 查询方法，这就是安全的
        * */
        PageHelper.offsetPage( queryDTO.getOffset(), queryDTO.getLimit() );
        if( (queryDTO.getSort()!=null)&&(!queryDTO.getSort().equals("")) ){ //查询字段不为null且不为空字符串
            queryDTO.setSort( "menu_id" ); //依据menu_id来排序
        };

        /* 2020-02-08 14:47:17
        从前端传过来的、输入至queryDTO中，也有排序字段(sort)，为什么还要将排序字段修改为字符串menu_id？
        因为前端传来的排序字段sort是字符串 "menuId"，没有下划线；(见前端发送Ajax请求的代码段)
        而数据库sys_menu表中的列名"menu_id"是有下划线的！！

        //数据库sys_menu表中没有"menuId"的列名，只有 "menu_id"(带下划线)的列名；(select一下sys_menu表看看)

        所以在后端这里，先接收到 字符串 "menuId"，然后转换为带下划线的字符串"menu_id"，
        后续把字符串"menu_id"传给dao查询的方法：让数据库能按照正确的列名"menu_id"来排序！

        以上所述，在bwcar视频第11集末尾，老师也有解释。

        //这样，bwcar视频 第22集中出现的 queryDTO.setSort( "user_id" ) 也是同理解释的。
        *  */

        List<SysMenu> menuByPage = sysMenuMapper.findMenuByPage( queryDTO );
        //关于PageInfo分页信息类的使用，见MD文档！
        PageInfo<SysMenu> pageInfo = new PageInfo<>(menuByPage); //new出一个分页信息类
        //从分页信息类中，取出 total属性和list属性，等待封装进DataGridResult工具类中
        long total = pageInfo.getTotal();
        List<SysMenu> rows = pageInfo.getList(); //list属性，我起个变量名为rows
        //使用DataGridResult的有参构造方法，封装进DataGridResult工具类中
        DataGridResult rst = new DataGridResult( total,rows );
        return rst;
    }

    @Override
    @Transactional( propagation= Propagation.REQUIRED )
    /* Spring 事务管理中 @Transactional
    propagation属性：事务的传播性
　　REQUIRED 支持当前已经存在的事务，如果还没有事务，就创建一个新事务。
        - REQUIRED 是默认值：即不进行该参数配置等于配置成 REQUIRED。
    * */
    public R deleteMenu(List<Long> ids) {
        for( Long id : ids ){
            if( id<51 ){
                return R.error( "系统菜单，不支持删除！" );
            }
        }

        int i = sysMenuMapper.deleteMenu( ids ); //影响的行数(删除的行数)
        if( i>0 ){ //删除的行数大于0
            return R.ok();
        }else{
            return R.error( -200, "删除失败" );
        }
    }

    @Override
    public R selectMenu() {
        List<SysMenu> menu = sysMenuMapper.findMenu();

        //手动添加一个根目录(树形图的标题)。
        SysMenu rootMenu = new SysMenu();
        rootMenu.setMenuId(0L);
        rootMenu.setType(0);
        rootMenu.setParentId(-1L);
        rootMenu.setName("一级菜单");
        menu.add(rootMenu);

        return( R.ok().put("menuList",menu) );
    }

    @Override
    public R saveMenu(SysMenu sysMenu) {
        int i = sysMenuMapper.insertSelective( sysMenu ); //影响的行数(插入的行数)。注：dao接口中的这方法是由“逆向工程”设置好的！
        return(  i>0 ? (R.ok()):(R.error("新增失败"))  ); //三目运算符，true就ok，否则就error
    }

    @Override
    public R findMenuById(Long menuId) {
        // menuId是主键id 即 primary key
        SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey( menuId ); //注：dao接口中的这方法是由“逆向工程”设置好的！
        return( R.ok().put("menu",sysMenu) );
        /*  注意：(1) R.ok()的返回值是R；
        (2) R的put()方法 返回值还是R！

        R.ok().put("menu",sysMenu) //指的是：在成功返回的基础上添加(成功的)键值对信息！


        public static R ok() {
		    return new R();
	    }

	    public R put(String key, Object value) {
            super.put(key, value);
            return this;
	    }
        *  */
    }

    @Override
    public R updateMenu(SysMenu sysMenu) {
        int i = sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        return( i>0 ? (R.ok()):(R.error("修改失败！"))  ); //三目运算符
    }

    @Override
    public List<String> findPermsByUserId(Long userId) {
        List<String> permsByUserId = sysMenuMapper.findPermsByUserId(userId);
        //permsByUserId的数据举例 sys:user:list,sys:user:info -- 每行以英文逗号 , 为分隔符
        Set<String> set = new HashSet<>();
        for( String s : permsByUserId ){
            if( s!=null&&!s.equals("") ){
                String[] split = s.split(",");
                for( String s1:split ){
                    set.add(s1);
                }
            }
        }
        List<String> perms = new ArrayList<>();
        perms.addAll(set);
        return perms;
    }

    @Override
    public R findUserMenu(Long userId) {
        List<Map<String,Object>> dirMenuByUserId = sysMenuMapper.findDirMenuByUserId( userId ); //查询用户的一级目录
        //查询一级目录下的 子菜单
        for( Map<String,Object> map : dirMenuByUserId ){ //取出每一个 “一级目录” map
            Long menuId = Long.parseLong( map.get("menuId")+"" );
            //查询一级目录下的 子菜单
            List<Map<String,Object>> subList = sysMenuMapper.findMenuNotButtonByUserId( userId,menuId );
            map.put( "list",subList ); //把此一级目录的子菜单，添加到该一级目录下
        }
        List<String> permsByUserId = this.findPermsByUserId(userId); //调用当前类MenuServiceImpl的findPermsByUserId()方法
        //对这个R本身 连续put()两次：即对这个R本身连续添加 "menuList"、"permissions"键值对
        return( R.ok().put("menuList",dirMenuByUserId).put("permissions",permsByUserId) );
    }


}
