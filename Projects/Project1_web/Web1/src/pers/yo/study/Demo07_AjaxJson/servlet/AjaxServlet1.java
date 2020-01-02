package pers.yo.study.Demo07_AjaxJson.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajaxServlet1") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class AjaxServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding( "UTF-8" );
        //1.获取请求参数
        String username = request.getParameter( "username" );
        System.out.println( username );
        //返回响应数据之前，设置contentType
        response.setContentType( "text/html;charset=utf-8" );
        response.getWriter().write( "hello！"+username );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
