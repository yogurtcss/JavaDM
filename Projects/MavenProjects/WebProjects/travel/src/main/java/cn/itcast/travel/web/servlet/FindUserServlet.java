package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* index.html 首页的 header.html 头部中，
        * 【已登录的用户，被储存在服务器的session中！
        * 通过ajax请求，从session中拿到这个已登录用户的信息！】
        *
        * request.getSession().setAttribute( 键名"user_successfulLogin", userFromSQL );
        *
        * 前端期望后端返回的数据内容是：
        * { uid:1, name:'张三' }
        *  */
        //登录成功后，在服务器中保存此用户对象的全部信息！！注意，键名是 成功登录的用户！"user_successfulLogin"

        //---header.html的get请求中，没有提交请求参数
        ResultInfo info = new ResultInfo(); //后端封装的数据对象
        /* 从本次请求中，获取此请求所属的会话session，
        * 此会话session就储存着当前用户的信息
        * session实例对象.getAttribute( "..." ) //返回的是Object类型
        *  */
        User user_successfulLogin = (User)request.getSession().getAttribute( "user_successfulLogin" );

        //---准备返回的数据
        ObjectMapper om = new ObjectMapper(); //Jackson核心对象
        response.setContentType( "application/json;charset=utf-8" ); //设置数据格式和编码
        //---正式返回数据
        //String json = om.writeValueAsString( user_successfulLogin );
        //response.getWriter().write( json ); //返回的可能是中文数据，用writer的流
        om.writeValue( response.getWriter(), user_successfulLogin ); //把已登录用户数据转为json格式，并放入流中
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}