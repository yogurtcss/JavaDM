package pers.yo.study.Demo01_Request.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/demo6")
public class Demo06Request_dispatch2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println( "/demo6被访问了" );

        /* 通过 键名msg 获取 同一request域中的数据(此键名msg对应的值)
        * 注意，此键名msg对应的值 是 Object型 的！！
        *  */
        Object receive = request.getAttribute( "msg" );
        System.out.println( receive );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
