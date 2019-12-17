package pers.yo.study.Demo02_Response.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/resDemo1") //第一步先把这个注解改了！
public class ResDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println( "demo1嗷！" );

        //访问 /resDemo1 ，使浏览器自动跳转到 /resDemo2
        //设置状态码为 302
        //response.setStatus( 302 );
        //重新跳转的路径，必须是 /虚拟目录 /servlet名！！
        //response.setHeader( "location", "/Web1/resDemo2" );

        /* 注意到：在实现重定向功能时，以上两行代码：
        * response.setStatus( 302 );  -- 302这个重定向的状态码不会变
        * response.setHeader( "location", "/XXX虚拟目录/资源路径" );  -- location这个字符串不会变；变化的只是 重定向的路径而已！！
        *
        * 还有一个简单的语句以实现重定向
        * response.sendRedirect( "/XXX虚拟目录/资源路径")
        *
        *  */
        //response.sendRedirect( "/Web1/resDemo2" );

        //动态获取虚拟目录
        String contextPath = request.getContextPath();
        response.sendRedirect( contextPath + "/resDemo2" );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
