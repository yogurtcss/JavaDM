package pers.yo.case1.webInSrc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* 关于 request、response乱码的复习嗷！
request 获取参数时，中文乱码的问题：
	* get 方式：tomcat 8 已经将 get 方式乱码问题解决了
	* post 方式：会乱码
	* 解决：在获取参数前，设置 request 的编码 request.setCharacterEncoding ("utf-8");

▲ 补充，向浏览器返回数据 response 时，中文乱码的问题：
response.setCharacterEncoding("UTF-8")
在获取响应数据时（如打印输出 response 的操作）：中文转换的时候把码表设置成 UTF-8，
但是浏览器未必是使用 UTF-8 码表来显示数据的呀！！

response.setContentType("text/html;charset=UTF-8");
它不仅设置浏览器用 UTF-8 显示数据，
内部（在获取响应数据时，如打印输出 response 的操作）还把中文转码的码表设置成 UTF-8 了。也就是说，
response.setContentType("text/html;charset=UTF-8");
把 response.setCharacterEncoding ("UTF-8") 的事情也干了！

*  */


@WebServlet("/loginServlet") //为 注解的servlet模板 修改的代码，加个斜杠
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //----------1.获取post过来的数据前，先转为UTF-8编码 ----------这里有10个短杠，大段内容的分隔符！
        request.setCharacterEncoding( "UTF-8" );

        //----------2.获取数据：验证码verifyCode； verify 核实、查证




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
