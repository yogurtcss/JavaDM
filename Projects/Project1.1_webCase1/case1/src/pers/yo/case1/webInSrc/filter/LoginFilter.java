package pers.yo.case1.webInSrc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/* 登录验证的过滤器
1.判断用户访问的资源X是否为 【与登录页面login.jsp相关的资源：如css/js/字体/图片/验证码】
  -是，则直接放行
  -不是，则 (进入第2步)判断用户是否已登录

2.判断当前用户user是否已登录
  -因为在之前的代码中，若用户登录成功了，我们将用户信息存入session中了
  所以已登录成功的用户 必定 在session中！

  -即 只需判断 session中是否有该用户user
  -有，则已经登录，放行
  -没有，则没有登录，跳转到登录页面

*  */

//这是我更改的代码模板，使用 【注解WebFilter】的简化语法 (类似Servlet注解的简化语法)
//直接填写 过滤拦截的路径，就是填写value属性 等价于 填写 urlPattern属性！
@WebFilter("/*") //这个注解表示：当【访问所有资源的路径 即/*】之前，都会执行当前这个过滤器！！
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //获取服务器资源前对请求request过滤 —— 真正地取到服务器上的资源 —— 取到服务器资源后回到过滤器 再过滤 最终返回response给浏览器
        //多个filter会依次运行，按照: 【过滤器1->过滤器2->过滤器3->程序->过滤器3->过滤器2->过滤器1】 的顺序运行。“原路返回”

        /* HttpServletRequest 和 ServletRequest 都是接口
        * HttpServletRequest 继承自 ServletRequest
        *
        * HttpServletRequest 比 ServletRequest 多了一些【针对于 Http 协议的方法】。 例如：
        * getHeader()， getMethod() ， getSession()
        *
        * 因为我们要从请求中获取session 即getSession()，
        * 所以要将原本的请求ServletRequest类型 转为 HttpServletRequest类型嗷
        *
        * 一般地，在过滤器filter要用到 请求request中与Http协议有关的方法时(如获取cookie、session、request域中的共享数据等)
        * 第一步要将 原本的请求ServletRequest类型 转为 HttpServletRequest类型，然后才获取cookie、session！！
        *  */

        //0.强制转换为HttpServletRequest，单独【新建一个不同名字的变量】request
        // 以获取cookie、session
        HttpServletRequest request = (HttpServletRequest)req;
        /* chain.doFilter( ... ) 放行时传入哪个类型的请求req？
        * 注意，这里的形参！！ chain.doFilter( ServletRequest req, ServletResponse resp )
        * chain.doFilter( 这里不是传HttpServletRequest啊！！！传入原本的形参ServletRequest req )
        *
        * 不要搞乱了！所以要单独【新建一个不同名字的变量】request，来装着【强制类型转换后的】HttpServletRequest类型变量
        *  */

        //1.获取资源请求路径：统一资源标识符uri
        String uri = request.getRequestURI();
        //2.判断当前请求的路径，是否包含【与登录页面login.jsp相关的资源：如css/js/字体/图片/验证码等】
        if( uri.contains("/login.jsp") || uri.contains("/loginServlet") || uri.contains("/css/") ||
            uri.contains("/js/")       || uri.contains( "/fonts/" )     || uri.contains( "/checkCodeServlet" )  ){
            /* 是，则直接放行
            * 注意，这里的形参！！ chain.doFilter( ServletRequest req, ServletResponse resp )
            * chain.doFilter( 这里不是传HttpServletRequest啊！！！传入原本的形参ServletRequest req )
            *  */
            chain.doFilter( req,resp );
        }else{ //不是，则判断用户是否登录
            /* HttpSession实例对象中的方法：
            * Object getAttribute(String name)
            * 有点忘了返回值是什么类型了，原来是Object类型的！
            *  */
            Object user = request.getSession().getAttribute( "user" );
            if( user!=null ){
                /* 若已登录，则放行
                * 注意，这里的形参！！ chain.doFilter( ServletRequest req, ServletResponse resp )
                * chain.doFilter( 这里不是传HttpServletRequest啊！！！传入原本的形参ServletRequest req )
                *  */
                chain.doFilter( req,resp );
            }else{ //没有登录，则登录到跳转页面
                /* 在login.jsp页面中设置了 错误提示信息 login_msg
                * <strong>${requestScope.login_msg}</strong>
                *
                * 所以这里也要设置错误提示信息login_msg，让login.jsp去显示嗷！
                *  */
                request.setAttribute( "login_msg", "您尚未登录，请登录" );
                /* 还是要注意这里的【请求的形参】！！
                * 若需要用到 【请求request中与Http协议有关的方法时(如获取cookie、session、request域中的共享数据等)】
                * 就要使用 HttpServletRequest！！
                * 所以这里就传入形参 HttpServletRequest request实例对象
                *
                * 但由于这里 由始至终 都没有用到 响应对象——也不需用到响应response中与Http协议有关的方法
                * 直接把原本的 ServletResponse类型的resp传过去即可
                *  */
                request.getRequestDispatcher( "/jsp/login.jsp" ).forward( request,resp );
            }

        }

    }


    public void init(FilterConfig config) throws ServletException {
    }


    public void destroy() {
    }

}
