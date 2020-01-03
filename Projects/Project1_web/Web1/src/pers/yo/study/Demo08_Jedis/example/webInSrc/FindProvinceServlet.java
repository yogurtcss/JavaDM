package pers.yo.study.Demo08_Jedis.example.webInSrc;

import com.fasterxml.jackson.databind.ObjectMapper;
import pers.yo.study.Demo08_Jedis.example.domain.Province;
import pers.yo.study.Demo08_Jedis.example.service.ProvinceService;
import pers.yo.study.Demo08_Jedis.example.service.impl.ProvinceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findProvinceServlet") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class FindProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //---1.请求时没有提交参数嗷！

        //---2.调用service层，查询数据
        ProvinceService ps = new ProvinceServiceImpl(); //获取service层的对象。接口回调 ——向上转型
        List<Province> rst = ps.findAll(); //正式查询数据

        //---3.返回【JSON格式的】数据
        /* 告诉浏览器：返回的数据是JSON格式的(JSON格式对应的MIME类型为application/json)
        * 同时告诉浏览器，要用utf-8来解码
        *  */
        response.setContentType( "application/json;charset=utf-8" );
        ObjectMapper om = new ObjectMapper(); //Jackson的核心对象
        om.writeValue( response.getWriter(), rst );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
