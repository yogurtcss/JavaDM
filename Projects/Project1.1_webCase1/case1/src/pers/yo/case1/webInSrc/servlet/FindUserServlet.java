package pers.yo.case1.webInSrc.servlet;

import pers.yo.case1.domain.User;
import pers.yo.case1.service.UserService;
import pers.yo.case1.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //----------1.获取用户id
        //在list.jsp的“修改” <a>标签的href中，传来的post请求参数为id
        request.setCharacterEncoding( "UTF-8" ); //设置 接收参数时的编码
        String id = request.getParameter( "id" );

        //----------2.根据id查询用户信息 User
        UserService service = new UserServiceImpl();
        User user = service.findUserById( id );

        //查看user的数据是否有乱码
        System.out.println( user.toString() );

        //----------3.将user对象存到request
        request.setAttribute( "user", user );

        //----------4.转发到/jsp/update.jsp页面，回显信息
        /* 回显信息：在/jsp/update.jsp页面中：
        * 因为我们把数据存入了request域中 request.setAttribute( "user", user );
        * 所以就从 requestScope中通过键名 "user" 取到这个user的值
        *
        * 我们手动地将用户的数据 挨个匹配地、用el表达式动态显示回各个html标签中
        * value属性为(第四声) <input />元素设定值；对于不同的输入类型，value属性的用法、表达的意义也不同。
        *   type="text"时，( <input />标签默认type为"text" )， value表示文本框里的值( 或 显示在文本框中的值 )
        *
        * 写法：<input/>标签中的value属性，表示：(显示在)文本框中的值value="${requestScope.user.name}"
        *
        * 因为这里用到了共享数据：request.setAttribute( "user", user );
        * 所以用 请求转发，不用加虚拟目录(项目名)嗷！
        * //服务器内部跳转路径，【不需要加项目名称(虚拟目录名！！)】
        *  */

        request.getRequestDispatcher( "/jsp/update.jsp" ).forward( request, response );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
