package pers.yo.study.Demo03_Cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CookieDemo1") //为 注解的servlet模板 修改的代码，加个斜杠，不想改为首字母小写了，直接和类名相同吧！
public class CookieDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建Cookie实例对象：cookie实例对象的值为 字符串msg，值为 字符串hello
        Cookie c = new Cookie( "msg", "hello" );
        //2.通过response发送Cookie
        response.addCookie( c );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
