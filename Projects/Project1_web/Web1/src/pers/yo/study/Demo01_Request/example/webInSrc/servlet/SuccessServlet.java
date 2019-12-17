package pers.yo.study.Demo01_Request.example.webInSrc.servlet;

import pers.yo.study.Demo01_Request.example.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/successServlet")
public class SuccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 之前我已在同一request域中 存储数据，名为 userEntity即user实例对象
        * 获取同一request域中共享的user对象
        *  */
        User user = (User)request.getAttribute( "userEntity" ); //向下转型

        if( user != null ){ //如果对象非空
            //为浏览器显示设置编码
            response.setContentType( "text/html;charset=utf-8" );
            //在浏览器页面上输出一句话
            response.getWriter().write( "登陆成功！"+user.getUsername()+" , 欢迎您！" );

        }else{
            response.getWriter().write( "我佛了，共享对象的变量名写错了吗？" );
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
