package pers.yo.study.Demo05_FilterListener.basic;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
/* Demo02Filter_Quick 修改代码模板，快速生成一个filter
-- 更多内容，见 P11 Filter和Listener 的MD文档

一、基本原理
[当 Servlet 容器开始调用某个 Servlet 程序时： ]

- 如果发现已经注册了一个 Filter 程序来对该 Servlet 进行拦截，
- 那么容器[不再直接调用 Servlet 的 service 方法 ]，
- [而是先调用 Filter过滤器 的 doFilter 方法 ] —— 再[由 doFilter 方法决定是否去激活 ]Servlet 的 service 方法；
  - [注 ]：“是否去激活Servlet 的 service 方法” 意思是[“此servlet是否能正常工作起来” ]
  - 因为  servlet 就是靠 service方法进行正常工作的 ——service方法中又调用了 doGet()、doPost()方法


▲ 但在 Filter.doFilter 方法中不能直接调用 Servlet 的 service 方法，
[而是调用 FilterChain对象.doFilter 方法来激活目标 Servlet 的 service 方法 ]，
FilterChain 对象时通过 Filter.doFilter 方法的参数传递进来的。

- 如果在 Filter.doFilter 方法中没有调用 FilterChain.doFilter 方法，
- 则目标 Servlet 的 service 方法不会被执行，这样通过 Filter 就可以阻止某些非法的访问请求


二、Filter链

三、Filter接口

四、FilterChain接口


--------------------------------
(多个)过滤器执行流程：
过滤 —— 取资源 —— 再过滤
记住：从请求到响应这个流程会经过 Filter 对象两次！
1. 执行过滤器
2. 执行【放行】后的资源(可能是下一个过滤器，也可能是 web 资源 (JSP/Servlet))
3. 回来执行过滤器放行代码下边的代码

多个filter会依次运行，按照: 【过滤器1->过滤器2->过滤器3->程序->过滤器3->过滤器2->过滤器1】 的顺序运行

详细解说：
获取服务器资源前对请求request过滤 —— 真正地取到服务器上的资源 —— 取到服务器资源后回到过滤器 再过滤 返回response给浏览器
记住：从请求到响应这个流程会经过 Filter 对象两次！
*  */



/* 这是我更改的代码模板，使用 【注解WebFilter】的简化语法 (类似Servlet注解的简化语法)
 * 直接填写 过滤拦截的路径，就是填写value属性 等价于 填写 urlPattern属性！
 * */
//@WebFilter("/*") //这个注解表示：当【访问所有资源的路径 即/*】之前，都会执行当前这个过滤器！！
public class Demo02Filter_Quick implements Filter { //修改代码模板，快速生成一个filter
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //放行前：对request对象请求消息进行【增强处理】
        System.out.println( "放行前，filterDemo2执行了！对request进行第一次过滤" );

        //执行这一句，说明放行（让下一个过滤器执行，如果没有过滤器了，就执行执行目标资源）
        chain.doFilter(req, resp);

        //放行后：对response对象的响应消息进行【增强处理】
        System.out.println( "放行后--真正取到服务器资源了，又回到过滤器 再过滤 返回response给浏览器" );
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
