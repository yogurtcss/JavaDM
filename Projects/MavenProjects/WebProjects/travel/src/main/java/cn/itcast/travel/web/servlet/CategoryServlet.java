package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class CategoryServlet extends BaseServlet { //继承BaseServlet
    //在成员方法外，声明私有变量 service服务层
    private CategoryService service = new CategoryServiceImpl();

    //修饰符改为public
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //---1.调用service层查询所有数据
        //底层dao操作已经try...catch过了，这里就不用try...catch了
        List<Category> cs = service.findAll();

        //---2.准备返回的数据：格式为JSON
        //ObjectMapper om = new ObjectMapper(); //Jackson核心对象
        //response.setContentType( "application/json;charset=utf-8" );
        //om.writeValue( response.getWriter(), cs ); //---3.正式返回数据

        writeValueToResponse( response, cs );
    }

}
