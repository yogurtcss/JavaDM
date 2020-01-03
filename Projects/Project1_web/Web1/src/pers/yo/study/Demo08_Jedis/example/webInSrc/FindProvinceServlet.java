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
        //List<Province> rst = ps.findAll(); //正式查询数据

        String json = ps.findAllJson(); //从redis中查询

        //---3.返回【JSON格式的】数据
        /* 告诉浏览器：返回的数据是JSON格式的(JSON格式对应的MIME类型为application/json)
        * 同时告诉浏览器，要用utf-8来解码
        *  */
        response.setContentType( "application/json;charset=utf-8" );
        //ObjectMapper om = new ObjectMapper(); //Jackson的核心对象
        //om.writeValue( response.getWriter(), rst );

        /* 2020-01-03 23:21:08
        * Tomcat 是一个 web应用服务器，是一个Servlet/Jsp 容器，
        * 主要负责 【将客户端请求传递给对应的 Servlet】，
        * 【并且将 Servlet 的响应数据返回给客户端】。
        *
        * 原来Tomcat能自动地 接收请求、将客户端请求传递给对应的 Servlet；
        * Tomcat能自动地 将Servlet的响应数据返回给客户端；
        *
        * 我们只需要 接受请求、处理请求即可
        *
        * ▲响应体：——我们真正要返回的数据，就塞在【响应体】中
        * 是服务端返回给客户端的 HTML 文本内容，
	    * 或者【其他格式的数据】，比如：视频流、图片或者音频数据。
        *  */

        /* 设置 返回给浏览器的响应体(响应体正文)。
        * 由Tomcat帮我们把响应数据 返回给浏览器
        * 因为这里返回的是字符数据，所以用getWriter().write()了
        * 如果响应体的内容是字节(如下载时)，那么可以使用 response.getOutputStream()
        *  */
        response.getWriter().write( json ); //设置 返回给浏览器的响应体
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
