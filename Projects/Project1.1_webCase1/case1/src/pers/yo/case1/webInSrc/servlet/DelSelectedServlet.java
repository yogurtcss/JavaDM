package pers.yo.case1.webInSrc.servlet;

import pers.yo.case1.service.UserService;
import pers.yo.case1.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedServlet") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class DelSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //----------1.获取选中的复选框的数组
        /* 注意：此数组中的元素是 将被删除的用户的id，可根据此id在数据库中找到该用户所在该行数据，然后删除之
        * <input type="checkbox" name="uid" value="${user.id}"></td> --提交的复选框
        *
        * 这是每一个用户专属的复选框，在servlet中通过 uid 来 获取单个用户的复选框的值value="${user.id}"
        * 因为多个用户都是使用相同的 键名"uid"，
        * 所以使用String[] request.getParameterValues("uid")
        * 得到的是 String数组，数组元素为 被选中的用户的${user.id}
        *  */
        String[] uids = request.getParameterValues( "uid" ); //数组元素为 被选中的用户的${user.id}

        //----------2.调用自定义实例对象service删除这些 选中的条目
        UserService service = new UserServiceImpl();
        /* delSeletedUser( String[] uids )
        * 遍历这些用户id，逐个地删除之
        *  */
        service.delSeletedUser( uids );

        //----------3.跳转到 findUserByPageServlet，查看所有数据
        //因为没有共享数据，所以直接 response重定向
        response.sendRedirect( request.getContextPath()+"/findUserByPageServlet" );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
