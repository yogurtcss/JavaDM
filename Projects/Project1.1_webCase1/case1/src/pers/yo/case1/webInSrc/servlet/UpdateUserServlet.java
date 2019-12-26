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

@WebServlet("/updateUserServlet") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //-----------1.获取请求数据时，设置编码
        request.setCharacterEncoding( "UTF-8" );

        //-----------2.获取表单数据 map
        /* 注意，此时post提交过来的表单数据 有隐藏域id，所以map中就有 隐藏域id
        * Map<String,String[]>  getParameterMap():获取所有参数的map集合
        *  */
        Map< String, String[] > map = request.getParameterMap();

        //-----------3.封装user对象
        User user = new User();
        try{ //这是IDEA快速生成的 try...catch
            /* 填充数据进user实例对象中，
            * 把填充好的user传给 自定义实例对象service，
            * 进行数据库的相关操作
            *  */
            BeanUtils.populate( user, map );
            /* 注意，map中就有 隐藏域id
            * 此时 post表单提交的【隐藏域id】已经被填充进用户user实例对象中，在sql语句中可直接从此user实例对象中取出id值
            *  */
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //-----------4.调用自定义service实例对象完成修改
        UserService service = new UserServiceImpl();
        service.updateUser( user );

        //-----------5.跳转到 查询所有数据的 findUserByPageServlet
        //因为没有“共享数据”，所以这里用 response.sendRedirect( "/虚拟目录/servlet名" )
        response.sendRedirect( request.getContextPath()+"/findUserByPageServlet" );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
