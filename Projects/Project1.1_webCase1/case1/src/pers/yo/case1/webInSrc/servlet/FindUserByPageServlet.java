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
import java.util.Map;

@WebServlet("/findUserByPageServlet") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
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

        //----------复杂条件查询！

        /* 在/jsp/list.jsp中
        * <form XXX...XXX action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
        * 将需要查询的【姓名name、籍贯address、邮箱email】属性 及 用户输入的“查询关键字”
        * 以post请求发给 /findUserByPageServlet
        *
        * 如，我需要查询 name=李，address=北京，email=333@qq.com
        以上3个【用户输入的查询字段】以post请求发给 /findUserByPageServlet
        *
        *  */
        Map< String, String[] > condition = request.getParameterMap(); //获取所有参数(即 需进行条件查询的参数)的map集合
        /* 根据 条件查询参数map集合中的【value值】 动态生成sql语句 —— “若某键key对应的value有值，我就 用and把此键key添进sql语句中！”
        *
        *  1.定义初始化的sql语句：使用StringBuilder变量嗷！因为要 append添加sql语句
        * StringBuilder实例对象sb 表示的字符串是 初始的sql语句
        * select count(*) from user 【where 1=1】--这是永真的，有没有1=1 都是正常运行的
        * 若某键key对应的value有值，我就 用and把此键key添进sql语句中！
        * select count(*) from user 【where 1=1】 and name=? and address=?
        *
        * 2.遍历map，判断每一个键key的value是否有值
        * 若有值， StringBuilder实例对象sb.append( "and 键key like %.." );
        *   其中 StringBuilder实例对象sb 表示的字符串是 初始的sql语句
        *
        *  */


        //----------2.调用自定义实例对象service查询 PageBean
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage( currentPage, rows, condition ); //查询结果

        //----------3.将查询得到的PageBean存入request中
        request.setAttribute( "pb", pb );
        request.setAttribute( "condition", condition );

        System.out.println( pb );

        //----------4.将【装有查询结果pb 的】请求 转发到jsp/list.jsp进行展示
        request.getRequestDispatcher( "/jsp/list.jsp" ).forward( request, response );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
