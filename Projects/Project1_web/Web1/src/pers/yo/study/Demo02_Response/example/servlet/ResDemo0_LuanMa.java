package pers.yo.study.Demo02_Response.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* 测试 request、response的乱码问题解决

* request获取参数时，中文乱码的问题：
	* get方式：tomcat 8 已经将get方式乱码问题解决了
	* post方式：会乱码
	* 解决：在获取参数前，设置request的编码request.setCharacterEncoding("utf-8");


▲ 补充，向浏览器返回数据response时，中文乱码的问题：

response.setCharacterEncoding("UTF-8")
在获取响应数据时（如打印输出response的操作）：中文转换的时候把码表设置成 UTF-8，
但是浏览器未必是使用 UTF-8 码表来显示数据的呀！！

response.setContentType("text/html;charset=UTF-8");
它不仅设置浏览器用 UTF-8 显示数据，
内部（在获取响应数据时，如打印输出response的操作）还把中文转码的码表设置成 UTF-8 了，

也就是说，
response.setContentType("text/html;charset=UTF-8");
把 response.setCharacterEncoding("UTF-8") 的事情也干了！

*  */

@WebServlet("/resDemo0") //第一步先改这里的注解！
public class ResDemo0_LuanMa extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取request参数前，设置request的编码为UTF-8
        request.setCharacterEncoding( "UTF-8" );

        System.out.println( request.getParameter("username") );
        System.out.println( request.getParameter("password") );

        //ResDemo3：获取流对象前，将原本 流的默认编码ISO-8859-1 设置为UTF-8
        //response.setCharacterEncoding( "utf-8" );
        //把 服务器返回响应数据的编码格式 告诉浏览器，并命令浏览器以该编码格式(utf-8)  进行解码！！
        //response.setHeader( "content-type", "text/html; charset=utf-8" );

        /* 以上两行代码的 更简便的、等价的写法
         * 以上两行代码中，
         * response.setCharacterEncoding( "utf-8" ); -- utf-8是可以从setHeader中设置的
         * response.setHeader( "content-type", "text/html; charset=utf-8" ); -- "content-type"也是固定的
         * 变化的只是 "text/html; charset=utf-8"
         *
         * 还有一个更简便的、等价的写法
         * response.setContentType(  "text/html; charset=utf-8" );
         *
         * 方式：类似 重定向的简便写法嗷！ ResDemo1中
         * request.sendRedirect( "/动态获取的虚拟目录/servlet资源路径" );
         *  */

        /* Content-Type：服务器告诉客户端【本次响应体数据格式以及编码格式】
         * setContentType方法 既设置了响应的编码，也通知了浏览器解码方式；
         *  */
        //response.setContentType(  "text/html; charset=utf-8" );



        //设置response的编码格式：同时告诉浏览器该以哪种方式(如utf-8)解码
        response.setContentType( "text/html; charset=UTF-8" );
        // response.setContentType( "text/html; charset=utf-8" ); // charset= 小写的utf-8也可以的

        //向客户端(网页)返回数据response
        response.getWriter().write( "俺是返回数据！" );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request,response );
    }
}
