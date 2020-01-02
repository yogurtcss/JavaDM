package pers.yo.study.Demo07_AjaxJson.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/findUserServlet_simple2") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class FindUserServlet_simple2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding( "utf-8" );
        //2.获取请求的参数
        String username_input = request.getParameter( "username_input" );
        //3.调用service层的方法，查询数据库中是否有这用户
        //if( username 在数据库中？ )

        /* 期盼服务器返回的数据格式：JSON格式
        注意到，此Json格式中的数据，是一个个的键值对！
        所以就用map来填充【返回的数据resData】了
        { "userIsExist": true,  msg: "此用户已存在！请换另一个用户名！" }
        { "userIsExist": false, msg:"此用户名可用嗷！" }
        *  */

        //4.准备返回的数据resData，注意转换为JSON格式！！
        /*  注意到，此Json格式中的数据，是一个个的键值对！
        * 所以就用map来填充【返回的数据resData】了
        *  */
        Map<String,Object> resData = new HashMap<>();
        if( "tom".equals(username_input)==true ){
            //按照前端要求的数据格式，填充数据嗷！
            resData.put( "userIsExist", true );
            resData.put( "msg", "此用户已存在！请换另一个用户名！" );
        }else{
            resData.put( "userIsExist",false );
            resData.put( "msg", "此用户名可用嗷！" );
        };

        //5.正式返回数据喽！
        /* 返回数据之前，告诉浏览器 返回数据的格式为JSON格式，要使用utf-8解码
        *
        * 设置返回数据的MIME类型-JSON格式 以及解码格式utf-8
        * ▲ JSON格式对应的 MIME类型为 application/json
        * response.setContentType( "application/json;charset=utf-8" );
        * 忘记 application/json的写法了！
        *  */
        response.setContentType( "application/json;charset=utf-8" );
        //Jackson的核心对象
        ObjectMapper om = new ObjectMapper();
        //正式返回数据！
        om.writeValue( response.getWriter(), resData );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
