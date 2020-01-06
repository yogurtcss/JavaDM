package cn.itcast.travel.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//这是我更改的代码模板，使用 【注解WebFilter】的简化语法 (类似Servlet注解的简化语法)
//直接填写 过滤拦截的路径，就是填写value属性 等价于 填写 urlPattern属性！
@WebFilter("/*") //这个注解表示：当【访问所有资源的路径 即/*】之前，都会执行当前这个过滤器！！
public class CharsetFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //获取服务器资源前对请求request过滤 —— 真正地取到服务器上的资源 —— 取到服务器资源后回到过滤器 再过滤 最终返回response给浏览器
        //多个filter会依次运行，按照: 【过滤器1->过滤器2->过滤器3->程序->过滤器3->过滤器2->过滤器1】 的顺序运行。“原路返回”

        //放行前，对request对象请求消息进行【增强处理】，并向chain.doFilter()中 传入增强后的req

        //将父接口转为子接口，目的是为了使用HTTP中的方法，如getXXX
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //获取【当前】客户端发出请求的方式，返回值为 GET或者POST的字符串
        String reqMode = request.getMethod();
        if( reqMode.equalsIgnoreCase("post") ){
            //接收POST请求的中文数据时，解决乱码问题
            request.setCharacterEncoding( "utf-8" );
        }
        /* 返回响应时，过滤器不做处理，让各个servlet自己处理response
        * 即：让各个servlet自行设置 response.setContentType( "..." );
        *  */

        //执行这一句，说明放行（让下一个过滤器执行，如果没有过滤器了，就执行执行目标资源）
        chain.doFilter(req, resp);

        //放行后，对response对象的响应消息进行【增强处理】

    }


    public void init(FilterConfig config) throws ServletException {
    }


    public void destroy() {
    }

}
