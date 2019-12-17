package pers.yo.study.Demo01_Request.example.webInSrc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/failServlet")
public class FailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 由于 Tomcat 是外国人的写，Tomcat 默认的编码是 ISO 8859-1，
        * 当我们输出中文数据的时候，Tomcat 会依据 ISO 8859-1 码表给我们的数据编码，
        * 中文不支持这个码表呀，所以出现了乱码
        *
        * 将返回的响应，设置为编码 utf-8
        *  */
        //response.setCharacterEncoding( "utf-8" );

        /* response.setContentType("text/html;charset=UTF-8");
        * 这个方法是最简便的，它不仅设置浏览器用 UTF-8 显示数据，内部还把中文转码的码表设置成 UTF-8 了，
        * 也就是说，response.setContentType("text/html;charset=UTF-8");
        * 把 response.setCharacterEncoding("UTF-8") 的事情也干了！
        *  */
        //设置浏览器用UTF-8编码显示数据
        response.setContentType( "text/html; charset=utf-8"  );

        /* 调用 getWriter () 方法向浏览器输出数据
        * 对于 getWriter () 方法而言，是 Writer 的子类，那么只能向浏览器输出字符数据(如中文数据)，不能输出二进制数据
        *
        *
        * 获取到printWriter对象
        * PrintWriter printWriter = response.getWriter();
        * printWriter.write("看完博客点赞！");
        *
        * 通常使用链式编程，不产生中间变量printWriter了
        *  */
        response.getWriter().write( "登录失败，用户名或密码错误" );


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
