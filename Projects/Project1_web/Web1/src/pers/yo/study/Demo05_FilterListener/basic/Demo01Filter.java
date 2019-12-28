package pers.yo.study.Demo05_FilterListener.basic;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


/* 使用Filter过滤器的步骤：
1.定义一个类，实现接口Filter
  注：应导入的是 import javax.servlet.Filter; 这个包嗷！
2.复写接口中的所有方法 --也可以空实现，因为需重写的方法，返回值为空void嘛！

3.配置拦截路径：两种方法 (1)web.xml；或(2)注解
(1)web.xml中：
▲ 配置方法和配置普通的 Servlet 一样的哦

<filter>
      <filter-name> 此过滤器的名字AAA-可任意命名 </filter-name>
      <filter-class>【这个过滤器所在的位置-全类名，即包名.类名】</filter-class>
   </filter>

<filter-mapping>  <%-- 此过滤器与 “过滤、拦截地址”的映射关系：我这过滤器要对应(过滤)哪些链接？ --%>
    <filter-name> 此过滤器的名字AAA-可任意命名 </filter-name>
    <url-pattern> /resource/* 【--过滤器要对应(过滤)哪些链接】 </url-pattern>
</filter-mapping>


(2)注解。使用的语法为：@WebFilter( ...需配置的属性... )
我们需配置的属性是—— String[] urlPatterns 指定一组过滤器的 URL 匹配模式。等价于 <url-pattern> 标签
默认的属性是—— String[] value 该属性等价于 urlPatterns 属性。但是两者不应该同时使用。

▲所以，类似配置servlet路径的写法：【直接配置默认的value属性】就等价于 配置urlPatterns属性了
写法为 @WebFilter( "/..XXX.." )

*  */

@WebFilter( "/*" ) // 注意到，/*代表任意、所有资源的路径。
/* 这个注解表示：当【访问所有资源的路径】之前，都会执行此Demo01Filter过滤器！！
* 配好后，直接开tomcat服务器，访问index.jsp试试看
*
* 若配置 @WebFilter( "/demo.jsp" ) 表示访问 demo.jsp之前，都会执行此Demo01Filter过滤器！！
* java 会在处理每一个 Servlet 时都走一次 Filter 的。所以你不必担心你的过滤不起作用了
*  */
public class Demo01Filter implements Filter {
    //使用IDEA 快速帮我生成需重写的方法嗷！
    @Override //也可以空实现，因为需重写的方法，返回值为空void嘛！
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override //接口Filter的实现类 的核心方法 doFilter
    //当一个 Filter 对象能够拦截访问请求时，Servlet容器将调用 Filter 对象的 doFilter方法。
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /* 参数 servletRequest 和 servletResponse，是 【Web容器】 或 【Filter链中上一个Filter传递过来的】请求和响应对象；
        * 参数 filterChain 表示 当前Filter链中的对象。
        *  */

        //在这里写过滤条件：即 if-else条件嗷！
        System.out.println( "俺是Filter！Demo01Filter被执行啦！" );

        //“放行”：什么样的东西能通过我的“过滤、拦截”？
        //执行这一句，说明放行（让下一个过滤器执行，如果没有过滤器了，就执行执行目标资源）
        filterChain.doFilter( servletRequest, servletResponse );

    }

    @Override //也可以空实现，因为需重写的方法，返回值为空void嘛！
    public void destroy() {

    }
}
