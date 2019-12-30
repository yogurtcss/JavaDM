package pers.yo.case1.webInSrc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/testSensitiveServlet") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class TestSensitiveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* SensitiveWordsFilter过滤器拦截的是所有路径 "/*"，
        * 使用增强的getParameter方法咯！
        *
        * 路径 "/*" 当然包含了 /testSensitiveServlet，
        * 所以访问 /testSensitiveServlet 必被过滤器拦截 进行过滤
        *
        * 注意，访问 /testSensitiveServlet时 【要先登录 login.jsp】--因为被我写的LoginFilter拦截了！！
        * 登录后，访问 /testSensitiveServlet时 再带上请求参数 ?name=XXX&msg=YYY
        * 写法为 /testSensitiveServlet?name=XXX&msg=YYY
        *  */
        String name = request.getParameter( "name" );
        String msg = request.getParameter( "msg" );
        System.out.println( name+" : "+msg );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
