package pers.yo.study.Demo03_Cookie.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 在服务器中的Servlet判断是否有一个名为lastTime的cookie
 1.有：不是第一次访问
 1.响应数据：欢迎回来，您上次访问时间为:2018年6月10日11:50:20
 2.写回Cookie：lastTime=2018年6月10日11:50:01
 2.没有：是第一次访问
 1.响应数据：您好，欢迎您首次访问
 2.写回Cookie：lastTime=2018年6月10日11:50:01
 */


@WebServlet("/CookieTest2") //为 注解的servlet模板 修改的代码，加个斜杠
public class CookieTest2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
