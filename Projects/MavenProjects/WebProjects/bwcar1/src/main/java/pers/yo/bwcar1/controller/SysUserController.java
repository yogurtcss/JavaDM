package pers.yo.bwcar1.controller;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.SysUser;
import pers.yo.bwcar1.service.SysUserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequiresRoles( "qf" ) //指定角色
    //若直接访问 /findAll，则会报错 Subject does not have role [qf]
    @RequestMapping("/findAll")
    @ResponseBody
    /* @ResponseBody注解 表示把该方法 返回的结果(根据前端指定的数据格式，如JSON)写入 HTTP 响应体(响应正文，即ResponseBody)中，
    * 一般在异步获取数据时使用； 前端需要异步获取 json 数据；后端方法加上 @Responsebody 注解后，就会直接返回 json 数据。
    *  */
    public List<SysUser> findAll(){
        return( sysUserService.findAll() );
    }

    @RequestMapping( "/sys/user/list" )
    @ResponseBody
    public DataGridResult findUser( QueryDTO queryDTO ){
        return sysUserService.findUserByByPage(queryDTO);
    }

    @RequestMapping( "/sys/user/export" )
    public void exportUser( HttpServletResponse response ){
        Workbook workbook = sysUserService.exportUser();
        try {
            //设置响应头
            response.setContentType("application/octet-stream");//所有文件都支持
            String fileName = "员工信息.xls";
            fileName = URLEncoder.encode(fileName,"utf-8");
            response.setHeader("content-disposition","attachment;filename="+fileName);
            //文件下载
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}