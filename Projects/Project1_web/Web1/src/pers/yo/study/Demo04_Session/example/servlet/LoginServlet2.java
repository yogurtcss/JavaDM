package pers.yo.study.Demo04_Session.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet2") //为 注解的servlet模板 修改的代码，加个斜杠
public class LoginServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置request编码
        request.setCharacterEncoding( "utf-8" );
        //获取参数
        String username = request.getParameter( "username" );
        String password = request.getParameter( "password" );
        String checkCode = request.getParameter( "checkCode" );

        //获取生成的验证码
        HttpSession session = request.getSession();
        String checkCode_session = (String) session.getAttribute( "checkCode_session" );
        //删除 request请求中的session中 【上一次】存储的验证码
        //使得每一次请求都得到新的验证码
        session.removeAttribute( "checkCode_session" );

        //忽略大小写，比较验证码是否正确
        if( checkCode_session!=null && checkCode_session.equalsIgnoreCase(checkCode) ){ //验证码正确
            if( "zhangsan".equals(username) && "123".equals(password) ){ //需调用UserDAO查询数据库
                //登录成功，在session中记住此用户名
                session.setAttribute( "user", username );
                response.sendRedirect( request.getContextPath()+"/html/sessionTest1/success.jsp" );
            }else{ //登录失败，存储错误提示信息到request中
                request.setAttribute( "login_error", "用户名或密码错误" );
                //转发请求到登陆页面
                request.getRequestDispatcher( "/html/sessionTest1/login2.jsp" ).forward( request, response );
            }
        }else{ //验证码错误，存储提示信息到request
            request.setAttribute( "cc_error", "验证码错误" );
            request.getRequestDispatcher( "/html/sessionTest1/login2.jsp" ).forward( request, response );

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
