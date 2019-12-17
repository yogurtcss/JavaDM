package pers.yo.study.Demo01_Request.example.webInSrc.servlet;

/* 为了与最外部的 web包(纯页面展示的) 做区别，
* 包名webInSrc —— web in src，
* 此包表示 在src源代码文件夹下的、且关于web操作的代码。
*  */

import org.apache.commons.beanutils.BeanUtils;
import pers.yo.study.Demo01_Request.example.dao.UserDAO;
import pers.yo.study.Demo01_Request.example.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


@WebServlet( "/loginServlet" ) //注解
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
        req.setCharacterEncoding( "utf-8" );
        // 表单提交的格式为：username=123123&password=21313

        /*
        //2.获取请求参数
        String username = req.getParameter( "username" );
        String password = req.getParameter( "password" );
        //System.out.println( username );

        //3.封装user对象
        User userTryLogin = new User();
        userTryLogin.setUsername( username );
        userTryLogin.setPassword( password );
        */ //以下将使用 beanUtils简化数据封装

        /* 关于beanUtils工具类，看MD文档吧！！
        *  */

        // 获取所有请求参数的map集合
        Map<String, String[]> map = req.getParameterMap();
        // 创建user实例对象
        User userTryLogin = new User();

        try { //使用 populate方法 填充数据
            BeanUtils.populate( userTryLogin, map );
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


        //4.调用UserDAO中的login方法
        UserDAO dao = new UserDAO();
        User user = dao.login( userTryLogin ); //登陆成功的用户 变量名改为 user了

        //5.判断user是否登陆成功：使用 请求转发
        if( user==null ){ //登录失败
            //转发给 处理失败情况的servlet
            req.getRequestDispatcher( "/failServlet" ).forward( req,resp );

        }else{ //登录失败
            //在同一request域中 存储数据，名为 userEntity即user实例对象
            req.setAttribute( "userEntity", user );
            //转发给 处理成功情况的servlet
            req.getRequestDispatcher( "/successServlet" ).forward( req,resp );
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet( req, resp ); //只在 doGet()方法中做逻辑
    }
}
