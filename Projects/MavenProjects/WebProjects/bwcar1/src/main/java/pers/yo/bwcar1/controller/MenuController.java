package pers.yo.bwcar1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.SysMenu;
import pers.yo.bwcar1.service.MenuService;
import pers.yo.bwcar1.utils.R;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/sys/menu/list")
    @ResponseBody
    /* 2020-02-07 11:57:25
    * 注意思考：后端方法的传入形参：使用与不使用 @RequestBody 注解的区别，及使用 @RequestBody 的一些问题(见MD文档)
    * 零、记住，@RequestBody注解 接收的参数是 Json对象的字符串。
    * 一、当前端没有向后端发送JSON格式数据时 【默认-不需要特别指定contentType参数】，服务端接收key-value形式参数。
    * 详情见： P2 菜单模块-分页展示.md 文档 - 表现层
    *  */
    public DataGridResult findMenu( QueryDTO queryDTO ){
        return( menuService.findMenu(queryDTO) );
    }

    @RequestMapping("/sys/menu/del")
    @ResponseBody
    public R deleteMenu( @RequestBody List<Long> ids ){
        return menuService.deleteMenu(ids);
    }

    @RequestMapping("/sys/menu/select")
    @ResponseBody
    public R selectMenu(){
        return menuService.selectMenu();
    }

    @RequestMapping( "/sys/menu/save" )
    @ResponseBody
    public R saveMenu( @RequestBody SysMenu sysMenu ){
        return menuService.saveMenu( sysMenu );
    }

    @RequestMapping( "/sys/menu/info/{menuId}" )
    /* 请求url中 代表请求参数的占位符 {XXX}，其中这里的XXX --由@PathVariable("XXX")来指定！
    *
    * 通过 @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中：
    * URL 中的 {xxx} 占位符可以通过 @PathVariable("xxx") 绑定到操作方法的入参中。
    *  */
    @ResponseBody
    //详细的 {占位符XXX} 和注解@PathVariable("XXX") 解释，见SpringMVC的MD文档 —— P2 请求参数绑定
    public R findMenuById( @PathVariable("menuId") Long menuId ){
        return menuService.findMenuById( menuId );
    }

    @RequestMapping("/sys/menu/update")
    @ResponseBody
    public R updateMenu( @RequestBody SysMenu sysMenu ){
        return  menuService.updateMenu( sysMenu );
    }
}
