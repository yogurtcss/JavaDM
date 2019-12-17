package pers.yo.study.Demo01_Request.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/demo1") //获取请求行数据，Request Line
public class Demo01Request_requestLine extends HttpServlet {
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException{

    }
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException{
        /* 获取请求行数据。
        * 请求行示例 GET /day14/demo1?name=zhangsan HTTP/1.1
        *
        * 启动tomcat服务器后，用浏览器访问该地址
        * 在浏览器地址栏输入 localhost:8080/虚拟目录名/Servlet的映射名
        * 如demo00Request ?请求参数名1=请求参数值1 & 请求参数名2=请求参数值2 & ...&
        * http://localhost:8080/JavaWeb_Web_exploded/demo00Request?name=haha&sex=enen
        *
        *  */

        //1.获取请求方式：GET
        String method = request.getMethod();
        System.out.println( method );

        //2.获取虚拟目录
        String contextPath = request.getContextPath();
        System.out.println( contextPath );

        //3.获取Servlet路径
        String servletPath = request.getServletPath();
        System.out.println( servletPath );

        //4.获取get方式请求参数
        String queryString = request.getQueryString();
        System.out.println( queryString ); //把所有请求参数名、参数值连同分隔符&一起输出 name=haha&sex=enen


        /* URI 与 URL 的区别
        * URI的范围比URL范围大
        * URI 在于 I (Identify) 是统一资源标示符，可以唯一标识一个资源。
        * URL 在于 L (Location)，一般来说（URL）统一资源定位符，可以提供找到该资源的路径，
        *  */

        /* 5. (*)获取请求URI：/day14/demo1
        * String getRequestURI():		    资源的标识(虚拟目录/资源名)，即 /day14/requestDemo1
        * StringBuffer getRequestURL()：    资源的路径，即 http://localhost/day14/requestDemo1
        *  */
        String requestURI = request.getRequestURI();
        System.out.println( requestURI );

        //6.获取协议及版本
        String protocol = request.getProtocol();
        System.out.println( protocol );

        //7.获取客户机的IP地址
        String remoteAddr = request.getRemoteAddr();
        System.out.println( remoteAddr );
    }
}
