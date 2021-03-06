package demo1_param.controller;

import demo1_param.domain.Account;
import demo1_param.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping( "/param" ) //具体对应一个模块
public class ParamController {
    //请求参数绑定入门
    @RequestMapping( "/testParam" )
    public String testParam( String username, String password ){
        /* 2020-01-27 14:06:51
        <%-- 请求参数绑定：href开头不要带斜杠啊！ --%>
        <a href="param/testParam?username=hehe&password=enenen" >请求参数绑定</a>
        <!-- 在href跳转的链接中传递请求参数 username=hehe、password=enenen
        在后台方法testParam中的形参username、password：就会【传入请求的参数】
        public String testParam( String username, String password )
        形参被赋值 username=hehe、password=enenen

        ▲ 注意：后台方法中的形参名 要与 传递参数的键名 一致！！

        2020-01-27 20:39:17
        2020-01-27 20:39:17
        <a href="${pageContext.request.contextPath}/anno/testRequestParam?name=哈哈哈啊" >RequestParam</a>
        前台传递参数键名为name，后台方法中的形参名为username

        当 后台方法中的形参名 要与 传递参数的键名 不一致时：
        把注解 @RequestParam( name="前台传递参数键名" ) 加在后台方法的形参名上
        -->
        *  */
        System.out.println( "执行了..." );
        System.out.println( "用户名："+username ); //haha
        System.out.println( "密码："+password ); //enen
        return "success";
    }

    //请求参数绑定：把数据封装到 JavaBean类中

    /* 参数绑定，两步骤：
    * 1.在后台处理的方法中：注解@RequestMapping("/后台匹配的路径X")，return "后台指定跳转的页面名"
    * 2.在前台JSP或HTML代码中：
    * 提交请求的路径(跳转的路径)为 "${pageContext.request.contextPath}/后台匹配的路径X"
    * 这样就会匹配到后台对应的方法，并执行，从而跳转到“后台指定跳转到的页面”
    *  */
    @RequestMapping( "/saveAccount" )
    public String saveAccount( Account account ){
        System.out.println( "执行了..." );
        System.out.println( account );
        return "success";
    }

    //自定义类型转换器
    @RequestMapping( "/saveUser" )
    public String saveUser( User user ){
        System.out.println( "执行了..." );
        System.out.println( user );
        return "success";
    }

    //原生的API
    @RequestMapping( "/testServlet" )
    public String testServlet( HttpServletRequest request, HttpServletResponse response ){
        System.out.println( "执行了..." );
        System.out.println( request );

        HttpSession session = request.getSession();
        System.out.println( session );

        ServletContext sc = session.getServletContext();
        System.out.println( sc );

        System.out.println( response );
        return "success";
    }

}