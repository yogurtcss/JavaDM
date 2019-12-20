package pers.yo.study.Demo02_Response.basic;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/resDemo3") //第一步先把这个注解改了！
public class ResDemo3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取流对象前，将原本 流的默认编码ISO-8859-1 设置为UTF-8
        response.setCharacterEncoding( "utf-8" );
        //把 服务器返回响应数据的编码格式 告诉浏览器，并命令浏览器以该编码格式(utf-8)  进行解码！！
        response.setHeader( "content-type", "text/html; charset=utf-8" );

        /* 以上两行代码的 更简便的、等价的写法
        * 以上两行代码中，
        * response.setCharacterEncoding( "utf-8" ); -- utf-8是可以从setHeader中设置的
        * response.setHeader( "content-type", "text/html; charset=utf-8" ); -- "content-type"也是固定的，不区分大小写
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

        //获取字符输出流：此流对象的默认编码是 ISO-8859-1
        //PrintWriter pw = response.getWriter();
        //pw.write( "宁好嗷！response" );

        //response.getWriter().write( "一句话带过！response!" );


        /* 返回字节数据时，可能还需要告诉浏览器的解码格式
        * response.setContentType(  "text/html; charset=utf-8" );
        *  */
        ServletOutputStream sos = response.getOutputStream();
        /* 注意，返回数据 sos.write( byte[]字节数组 b  )，传入形参为字节数组！！
        * 如要传入【中文或英文的】字符串，需转换为字节数组：【字符串.getBytes()方法】，再传进此sos.write()方法中！
        *  */
        sos.write( "俺是中文，转回字节数组啦！".getBytes( "utf-8" ) );
        sos.write( "hello! yingwen! English".getBytes() ); //备注

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
