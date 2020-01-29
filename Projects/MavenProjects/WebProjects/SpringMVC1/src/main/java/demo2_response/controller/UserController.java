package demo2_response.controller;

import demo2_response.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* 2020-01-28 14:55:28
<!-- 配置 spring创建容器时，要扫描的包
每新增一个包，就要在springmvc.xml中增加扫描的包！！
-->
<context:component-scan base-package="demo1_param" />
<context:component-scan base-package="demo2_response" />  //新增扫描的包
*  */

@Controller
@RequestMapping( "/user" )
public class UserController {

    /*
    在返回值为空的控制器方法中的操作：
    请求转发、重定向、设置返回响应的编码、设置相应内容

    2020-01-28 14:29:52
    关于跳转资源路径的开头斜杠问题：
    1.action里面的请求不能加斜杠；否则代表站点根目录：此时应加上项目路径才有效
    2.而servlet映射应加上斜杠/代表的是项目根目录 如@WebServlet("/login")
    3.请求转发应加上斜杠，代表项目根目录
      如request.getRequestDispatcher("/success.jsp").forward(request, response);
    4.重定向不应加上斜杠，因为其代表的是站点根目录。
    *  */

    @RequestMapping( "/testVoid" )
    public void testVoid( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        System.out.println( "testVoid..." );

        //请求转发：应加上斜杠，代表项目根目录
        //请求转发能访问到WEB-INF目录下的资源
        //request.getRequestDispatcher( "/WEB-INF/pages/success.jsp" ).forward( request,response );

        //重定向：开头不带斜杠，要写虚拟目录
        //response.sendRedirect( "/WEB-INF/pages/success.jsp" ); //重定向不能访问到WEB-INF下的资源
        //response.sendRedirect( request.getContextPath()+"/index.jsp" );

        /* 注意 WEB-INF 下的资源！
        WEB-INF 目录是不对外开放的，外部没办法直接在地址栏URL访问到。
        不能 “重定向” 来访问，只能通过 “请求转发” 方式来访问。

        何为“转发”？
        1.由“中央指挥的”DispatcherServlet 拦截到所有的请求(/)，把请求转发到 WEB-INF下的资源，这样可以访问；
          //在web.xml中配置的 DispatcherServlet 来加载springmvc.xml，
          //从而使得springmvc.xml中的【视图解析器】能 设置签注prefix="/WEB-INF/.../" 来访问WEB-INF下的资源
        2.直接进行请求转发：request.getRequestDispatcher( "/WEB-INF/.../..." ).forward( request,response );
        这样也可以访问
        *  */

        response.setContentType( "text/html;charset=utf-8" );
        response.getWriter().write( "您好！" );
    }

    @RequestMapping( "/testModelAndView" )
    public ModelAndView testModelAndView(){
        //---1.创建ModelAndView实例对象
        //用作控制器方法的返回值
        ModelAndView mv = new ModelAndView();
        //模拟从数据库中查询出user实例对象
        User user = new User();
        user.setUsername( "小明" );
        user.setPassword( "456" );
        user.setAge( 30 );

        /* 把user对象存储到mv对象中，
        * 同时也会把user对象存入request对象中 --为什么？
        *     因为ModelAndView类中 使用 ModelMap 存储属性(如这里的user对象)
        *     而 ModelMap是Model接口的实现类，
        *     SpringMVC 会把 ModelAndView 的 model 中数据放入到 request 域对象中
        *  */
        mv.addObject( "user",user );

        //指定跳转页面的名字为 success
        mv.setViewName( "success" );

        /* 当返回 ModelAndView实例对象时：
        * 使用自定义springmvc.xml中配置的视图解析器：
        * 为指定名字视图名success 添加前缀prefix、后缀suffix，
        * 来跳转到指定页面
        *  */
        return mv;
    }

    //使用关键字的方式，进行转发或者重定向
    @RequestMapping( "/testForwardOrRedirect" )
    public String testForwardOrRedirect(){
        System.out.println( "testForwardOrRedirect方法执行了..." );
        //请求转发
        //return( "forward:/WEB-INF/pages/success.jsp" );

        /* 使用关键字redirect做重定向时：不用加虚拟目录名，springmvc自动帮你加了！
        * 这是特殊情况！
        *  */
        return( "redirect:/index.jsp" );
    }

    @RequestMapping( "/testAjax" )
    public @ResponseBody User testAjax( @RequestBody User user ){
        System.out.println( "testAjax方法执行了..." );
        /* 客户端发送ajax请求，传来json字符串
        * 后端把json字符串封装到user对象中
        *  */
        System.out.println( user );

        //做响应，模拟查询数据库
        user.setUsername( "hahahahah" );
        user.setAge( 40 );

        //做响应
        return user;
    }
}
