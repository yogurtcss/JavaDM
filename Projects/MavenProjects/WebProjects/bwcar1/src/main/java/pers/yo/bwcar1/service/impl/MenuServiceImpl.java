package pers.yo.bwcar1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /* 2020-02-07 10:34:13
    * 关于PageHelper工具类、PageInfo分页信息类的使用，见MD文档！
    *  */
    @Override
    public DataGridResult findMenu(QueryDTO queryDTO) {
        /* 在你需要进行分页的 MyBatis 查询方法前调用 PageHelper.startPage 静态方法即可，
        * 紧跟在这个方法后的第一个MyBatis 查询方法会被进行分页。
        * 只要你可以保证在 PageHelper 方法调用后紧跟 MyBatis 查询方法，这就是安全的
        * */
        PageHelper.offsetPage( queryDTO.getOffset(), queryDTO.getLimit() );
        if( (queryDTO.getSort()!=null)&&(!queryDTO.getSort().equals("")) ){ //查询字段不为null且不为空字符串
            queryDTO.setSort( "menu_id" ); //依据menu_id来排序
        };
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

}
