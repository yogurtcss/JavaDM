package pers.yo.study.Demo07_AjaxJson.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/findUserServlet_simple") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class FindUserServlet_simple extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //--------------1.获取用户名
        String username = request.getParameter( "username" );

        //--------------2.调用 用户服务UserService查询 用户名是否存在

        /* 发送Ajax请求

          期望服务器响应回的数据格式：
          {"userExsit":true,   "msg":"此用户名太受欢迎,请更换一个"}
          {"userExsit":false,   "msg":"用户名可用"}

        * */

        //--------------3.准备返回响应的数据 resMap
        Map<String,Object> resMap = new HashMap<>(); //返回数据

        /* 这里简单起见，用tom示例
        * 应该使用 UserService查出的结果
        *    if( 此username是否在数据库中？ ) {....}
        *  */
        if( "tom".equals(username)==true ){ //此用户名已存在
            resMap.put( "userExsit", true );
            resMap.put( "msg", "此用户名太受欢迎了，请换一个啦！" );
        }else{ //此用户名不在我现在的数据库中
            resMap.put( "userExsit", false );
            resMap.put( "msg", "此用户名可用嗷！" );
        }

        //--------------4.正式向浏览器返回数据resMap，注意要把返回数据转换为JSON格式！

        //将Java对象转换为Json字符串
        /* 转换方法：
        (1)writeValue(参数1，obj):
           参数1：
              File：将obj对象转换为JSON字符串，并保存到指定的文件中
              【？与response怎么关联】Writer：将obj对象转换为JSON字符串，并将json数据填充到字符输出流中
              【？】OutputStream：将obj对象转换为JSON字符串，并将json数据填充到字节输出流中

        (2)writeValueAsString(obj): 将对象转为json字符串
        * */

        //正式返回数据之前：告诉浏览器 返回数据的格式是json 和解码要用utf-8
        response.setContentType( "application/json;charset=utf-8" );

        ObjectMapper om = new ObjectMapper(); //传进 Jackson核心对象
        om.writeValue( response.getWriter(), resMap ); //把 数据转为JSON格式 并返回给浏览器

        /* 【？与response怎么关联】Writer：将obj对象转换为JSON字符串，并将json数据填充到字符输出流中
       【？】OutputStream：将obj对象转换为JSON字符串，并将json数据填充到字节输出流中
        *  */
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
