package cn.itcast.travel.web.servlet.past;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exitServlet") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class ExitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //---前端没有发出请求参数

        //---1.访问此ExitServlet，销毁整个session
        request.getSession().invalidate();
        /* request.getSession().invalidate()
        * session.removeAttribute ()：删除session中的某一个用户状态属性。
        * session.invalidate()：销毁整个会话session，此 session中所有的用户状态属性都将不存在。
        *  */

        //---2.跳转到登陆页面，重定向时，必需带上虚拟目录！
        //String request实例对象.getContextPath()方法：获取虚拟目录
        response.sendRedirect( request.getContextPath()+"/login.html" );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
