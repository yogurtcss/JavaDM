package pers.yo.case1.webInSrc.servlet;

import pers.yo.case1.service.UserService;
import pers.yo.case1.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delUserServlet") //为 注解的servlet模板 修改的代码，加个斜杠
public class DelUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //自己根据注释手写的嗷！根据id值 删除数据库中的用户数据
        //----------0.设置接收参数时的编码(因为id不是中文类型的，其实不用设置了)
        request.setCharacterEncoding( "UTF-8" );
        //----------1.获取 将被删除的用户的id
        //查看 list.jsp 可知 post请求中传递参数的名 为"id"
        String id = request.getParameter( "id" );

        //----------2.调用 自定义实例对象service 删除
        UserService service = new UserServiceImpl();
        service.deleteUser( id ); //正式删除嗷！

        //----------3.跳转至 findUserByPageServlet 重新查询一遍结果
        response.sendRedirect( request.getContextPath()+"/findUserByPageServlet" );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
