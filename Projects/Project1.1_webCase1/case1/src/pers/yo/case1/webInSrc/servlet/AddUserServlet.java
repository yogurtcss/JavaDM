package pers.yo.case1.webInSrc.servlet;

import org.apache.commons.beanutils.BeanUtils;
import pers.yo.case1.domain.User;
import pers.yo.case1.service.UserService;
import pers.yo.case1.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addUserServlet") //为 注解的servlet模板 修改的代码，加个斜杠。一般servlet的首字母小写嗷！
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //自己根据注释手写的嗷！
        //----------1.设置编码
        request.setCharacterEncoding( "UTF-8" );

        //----------2.获取用户在add页面提交的所有数据
        Map< String, String[] > map = request.getParameterMap();

        //----------3.把 用户在add页面提交的所有数据 封装为对象BeanUser(这是我自定义的变量名而已)
        User user = new User(); //新创建的user实例对象，用来 “被填充数据” 嗷！
        try{ //这是IDEA快速生成的 try...catch！！
            BeanUtils.populate( user, map );
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //----------4.调用 自定义的service对象，把第3步中封装好的用户数据(对象BeanUser)存入数据库中
        UserService service = new UserServiceImpl(); //接口实现类的实例对象，上转型
        service.addUser( user ); //正式把 封装好的用户数据user 存入数据库中

        //----------5.为了能查看到 添加完成的新数据，应跳转到 【外部页面-需要加虚拟目录】userListServlet中
        /* 【查看 添加完成的 新数据】，相当于 又发一次新的请求request，进行一次新的 select * from XXX表 --查询操作
        * 在 userListServlet 中 就有这个操作：【进行一次新的 select * from XXX表 --查询操作】
        *
        * 2019-12-25 16:10:48 更新：
        * 本来应该跳转到 userListServlet中的，但list.jsp中用的是 “分页查询”了
        * 就改跳转到 "/findUserByPageServlet" 了
        *
        * 而 /jsp/list.jsp 是跳转到的目的地【展示页面】，它本身【没有数据】，也没有查询数据的功能！
        *  */

        /* ▲ 注：跳转页面时，可用到 【请求转发】、【重定向】，该怎么用？
	    * 1.当请求中需要用到【共享数据】时，才用 “请求转发”
	    *    request.getRequestDispatcher("/虚拟路径").forward(request.response)；
	    * 2.若请求中没有共享数据，则直接用“重定向” response.sendRedirect("/虚拟目录/servlet虚拟路径或jsp路径")
	    * 即：response.sendRedirect( request.getContextPath()+"/servlet虚拟路径或jsp路径" )
        *  */
        //这里没有用到共享数据，直接重定向了(记得要带上 虚拟目录！！request.getContextPath() )
        response.sendRedirect( request.getContextPath()+"/findUserByPageServlet" );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
