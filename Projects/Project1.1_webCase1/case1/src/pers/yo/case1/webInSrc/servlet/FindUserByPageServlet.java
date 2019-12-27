package pers.yo.case1.webInSrc.servlet;

import pers.yo.case1.domain.PageBean;
import pers.yo.case1.domain.User;
import pers.yo.case1.service.UserService;
import pers.yo.case1.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserByPageServlet") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //----------1.接收请求参数 currentPage 和每页显示条数rows
        String currentPage = request.getParameter( "currentPage" );
        String rows = request.getParameter( "rows" );

        //起始进入 index.jsp时，因为没有发出获取currentPage和rows的请求，所以需要为这两个参数设置默认值
        if( currentPage==null || currentPage.equals("") ){
            currentPage = "1";
        }
        if( rows==null || rows.equals("") ){
            rows = "5";
        }

        //----------2.调用自定义实例对象service查询 PageBean
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage( currentPage, rows ); //查询结果

        //----------3.将查询得到的PageBean存入request中
        request.setAttribute( "pb", pb );

        System.out.println( pb );

        //----------4.将【装有查询结果pb 的】请求 转发到jsp/list.jsp进行展示
        request.getRequestDispatcher( "/jsp/list.jsp" ).forward( request, response );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
