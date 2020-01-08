package cn.itcast.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @WebServlet("/BaseServlet") //注意，这个BaseServlet(基础servlet)不需要被访问到！！只是根据模块名分发方法而已
public class BaseServlet extends HttpServlet {
    /* 为什么继承了HttpServlet的类 BaseServlet，要重写HttpServlet类的service()方法？
    ▲ servlet的生命周期
    1.被创建：执行 init 方法，【只执行一次】
　　  --默认情况下，第一次被访问时，Servlet 被创建，然后执行 init 方法；
     --可以配置执行 Servlet 的创建时机；
    2.提供服务：【执行 service() 方法，执行多次】
    3.被销毁：当服务器正常关闭时，执行 destroy 方法，只执行一次

    从servlet的生命周期来看：servlet中的service()方法必定会被执行的。
    若我们不重写HttpServlet类的service()方法，则【仍会默认执行 HttpServlet类的service()方法——按请求方式不同 来“分发方法”】，
    而不是我们所要的【按模块不同 来“分发方法”】；
    --因为 BaseServlet 有 service() 方法了，就不会去找 父类HttpServlet 的 service()方法
    --所以 BaseServlet 必须写一个 service 的方法来【截断】父类HttpServlet 的 service()方法

    *  */
    @Override  //不需重写doPost()、doGet()方法！只需重写HttpServlet的service方法，我们自行【根据模块名分发方法】
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //在servlet的生命周期中：提供服务，执行service()方法，执行多次！！
        // System.out.println( "BaseServlet的service方法被执行了！" );

        /* 【问】如何根据模块的不同，进行“方法分发”？
        * 如路径为：/user/find
        * 即 /user(模块名) /find(方法名)，
        * 那BaseServlet就把请求分发到 UserServlet的find方法中
        *
        * 【答】1.获取用户请求的路径字符串uri(统一资源标识符uri，即 标识、定位任何资源的字符串)
        * 如 localhost:8080/travel/user/find
        * 此统一资源标识符uri中，包含了 【被匹配到的】模块名user和方法名find，
        *
        * 2.截取uri字符串：最后一个斜杠后开始的【子字符串】就是方法名，
        *   -- 截取字符串 String substring( int begin-包含起始处， int end-不包含终点处 )
        * 利用反射：获取 【此模块对应的servlet的字节码对象ccc (即Class 类_类型实例对象ccc)】，——>如何获取？巧妙！this
        *        ——这是成功调取反射方法执行 模块对应Servlet 的关键；
        *
        * 从这个 Class 类_类型实例对象ccc中 getMethod(传入方法名) 得到指定名字的方法对象，
        * invoke(此方法所属实例对象, 形参列表) 执行此方法
        *
        *  */


        //---完成方法分发
        //---1.获取请求路径uri
        /* request.getRequestURL() 返回全路径
        * request.getRequestURI() 返回 【除去 host部分(即除去 “主机名:端口号” 之外、“突出的”后面那段、开头带上斜杠！！)】的路径，
        *
        * 记忆：
        * (1)URL重要在 L：Location，定位 --> 全路径
        * (2)URI重要在 I：
        * Identify，标志 --> 什么东西可以标志一个资源？
        * --> 越小、越突出的部分就能标志一个资源，而 “主机名:端口号” 【太大众】了，无法确定标志一个资源！
        * --> 除去 “主机名:端口号” 之外 “突出的”后面那段，就是“标志了”
        *
        * 举例：
        * request.getRequestURL() 返回全路径  http://localhost:8080/jqueryLearn/resources/request.jsp
        * 注意  【http://localhost:8080】 就是 主机名:端口号
        *
        *
        * request.getRequestURI() 返回 【除去 host部分(即除去 “主机名:端口号” 之外、“突出的”后面那段、开头带上斜杠！！)】的路径，
        * 返回 /jqueryLearn/resources/request.jsp 【开头带上斜杠！】
        *
        *  */

        /* uri_xieGang 即 uri_斜杠，开头带斜杠的字符串
        * 除去“主机名:端口号” 之外的、“突出的”后面那段、开头带上斜杠xieGang！
        *  */
        String uri_xieGang = req.getRequestURI(); //   返回的uri字符串为 /user/add
        System.out.println( "返回的uri字符串为："+uri_xieGang );

        //---2.获取方法名(截取字符串uri)
        int lastXieGang_index = uri_xieGang.lastIndexOf( "/" ); //获取最后一个斜杠的下标
        //方法名，截取uri的范围：从 最后一个斜杠所在下标+1处 直到末尾，
        String methodName = uri_xieGang.substring( lastXieGang_index+1 ); //返回方法名为add
        System.out.println( "返回的方法名为："+methodName );

        //---3.获取方法对象Method
        /* 注意到，每个模块对应的servlet类中：
        * 每个方法的【修饰符均被提前修改为public】、返回值均为void、传入的形参列表均一样！
        * 只是方法名不一样而已！
        * 这样获取方法对象就很容易了
        *
        * 利用反射：获取 【此模块对应的servlet的字节码对象ccc (即Class 类_类型实例对象ccc)】，——>如何获取？巧妙！this
        *        ——这是成功调取反射方法执行 模块对应Servlet 的关键；
        *
        * 从这个 Class 类_类型实例对象ccc中 getMethod(传入方法名) 得到指定名字的方法对象，
        * invoke(此方法所属实例对象, 形参列表) 执行此方法
        *  */

        /* 【谁调用我service()方法？我this就指向谁。】
        * 这个在BaseServlet中重写的service()方法，被哪个子类调用，this(上下文对象)就指向谁
        *
        * 注意，this是上下文对象。考虑到当前的上下文环境在哪？this就指向谁
        * 当前的上下文环境(被调用方法所处的环境)是在UserServlet中，this就指向UserServlet了！
        *
        *
        * 根据servlet的生命周期：
        * 如访问路径 /user/add时，被匹配到的UserServlet就开始执行service()方法，来提供服务；
        * 而子类UserServlet中没有service()方法，
        * 那么子类UserServlet 就到父类BaseServlet上找：调用 父类重写的service()方法
        * 【关键】这时候 BaseServlet中重写的service()方法就在 UserServlet中被调用了！
        *
        * this就指向 子类UserServlet！！
        *  */
        //这是谁？cn.itcast.travel.web.servlet.UserServlet@1db44e0b
        System.out.println( "【关键】这时候 BaseServlet中重写的service()方法就在 UserServlet中被调用了！" );
        System.out.println( "this："+this+"就指向 子类UserServlet！！" );

        //---4.执行这个方法



    }
}
