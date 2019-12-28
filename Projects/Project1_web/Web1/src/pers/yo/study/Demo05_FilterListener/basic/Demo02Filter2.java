package pers.yo.study.Demo05_FilterListener.basic;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//这是我更改的代码模板，使用 【注解WebFilter】的简化语法 (类似Servlet注解的简化语法)
//直接填写 过滤拦截的路径，就是填写value属性 等价于 填写 urlPattern属性！
//先把前一个过滤器Demo

/*
拦截路径配置：
1. 具体资源路径： /index.jsp   只有访问index.jsp资源时，过滤器才会被执行
2. 拦截目录： /user/*	访问/user下的所有资源时，过滤器都会被执行
3. 后缀名拦截： *.jsp		访问所有后缀名为jsp资源时，过滤器都会被执行
4. 拦截所有资源：/*		访问所有资源时，过滤器都会被执行
* 拦截方式配置：资源被访问的方式

--------------------------------------


//浏览器直接请求index.jsp资源时，该过滤器会被执行
//@WebFilter(value="/index.jsp",dispatcherTypes = DispatcherType.REQUEST)
//只有转发访问index.jsp时，该过滤器才会被执行
//@WebFilter(value="/index.jsp",dispatcherTypes = DispatcherType.FORWARD)

//浏览器直接请求index.jsp或者转发访问index.jsp。该过滤器才会被执行
//@WebFilter(value="/*",dispatcherTypes ={ DispatcherType.FORWARD,DispatcherType.REQUEST})


过滤器先后顺序问题：
1. 注解配置：按照类名的字符串比较规则比较，值小的先执行
	* 如： AFilter和BFilter。  AFilter就先执行了。
2. web.xml配置： <filter-mapping>谁定义在上边，谁先执行


*  */

@WebFilter("/*") //这个注解表示：当【访问所有资源的路径 即/*】之前，都会执行当前这个过滤器！！
public class Demo02Filter2 implements Filter {

    //每一次请求被拦截资源时，会执行。执行多次
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //获取服务器资源前对请求request过滤 —— 真正地取到服务器上的资源 —— 取到服务器资源后回到过滤器 再过滤 最终返回response给浏览器
        //多个filter会依次运行，按照: 【过滤器1->过滤器2->过滤器3->程序->过滤器3->过滤器2->过滤器1】 的顺序运行

        //放行前，对request对象请求消息进行【增强处理】
        System.out.println( "doFilter..." );
        //执行这一句，说明放行（让下一个过滤器执行，如果没有过滤器了，就执行执行目标资源）
        chain.doFilter(req, resp);

        //放行后，对response对象的响应消息进行【增强处理】

    }

    //在服务器启动后，会创建Filter对象，然后调用init方法。只执行一次。用于加载资源
    public void init(FilterConfig config) throws ServletException {
        System.out.println( "init..." );
    }

    //在服务器关闭后，Filter对象被销毁。如果服务器是正常关闭，则会执行destroy方法。只执行一次。用于释放资源
    public void destroy() {
        System.out.println( "destroy..." );
    }

}
