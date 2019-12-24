package pers.yo.case1.webInSrc.servlet;

import org.apache.commons.beanutils.BeanUtils;
import pers.yo.case1.domain.User;
import pers.yo.case1.service.UserService;
import pers.yo.case1.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/* ▲ 关于 request、response乱码的复习嗷！
request 获取参数时，中文乱码的问题：
	* get 方式：tomcat 8 已经将 get 方式乱码问题解决了
	* post 方式：会乱码
	* 解决：在获取参数前，设置 request 的编码 request.setCharacterEncoding ("utf-8");

▲ 补充，向浏览器返回数据 response 时，中文乱码的问题：
response.setCharacterEncoding("UTF-8")
在获取响应数据时（如打印输出 response 的操作）：中文转换的时候把码表设置成 UTF-8，
但是浏览器未必是使用 UTF-8 码表来显示数据的呀！！

response.setContentType("text/html;charset=UTF-8");
它不仅设置浏览器用 UTF-8 显示数据，
内部（在获取响应数据时，如打印输出 response 的操作）还把中文转码的码表设置成 UTF-8 了。也就是说，
response.setContentType("text/html;charset=UTF-8");
把 response.setCharacterEncoding ("UTF-8") 的事情也干了！

-----------------------------------
▲ 用于封装JavaBean的BeanUtils工具类的详解：
1. JavaBean：标准的Java类
2. 功能：封装数据
	BeanUtils不是对“成员变量”进行操作，而是对 【setter和getter方法截取后的产物——称为“属性”】 进行操作嗷！
	例如：getUsername() --> Username--> username
3. 方法：
	1. setProperty()
	2. getProperty()
	3. populate(Object obj , Map map):将map集合的键值对信息，封装到对应的JavaBean对象中
		* populate 填充，移民
		* 可以 通过 req.getParameterMap() 获取所有请求参数的map集合，同时new出一个user实例对象，
		然后调用此populate方法，把 map中的数据 “填充”至 此user实例对象中
-----------------------------------




*  */


@WebServlet("/loginServlet") //为 注解的servlet模板 修改的代码，加个斜杠
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //----------1.获取post过来的数据前，先转为UTF-8编码 ----------这里有10个短杠，大段内容的分隔符！
        request.setCharacterEncoding( "UTF-8" );

        //----------2.获取数据：用户填写的验证码verifyCode； verify 核实、查证
        String verifyCode = request.getParameter( "verifyCode" );
        System.out.println( verifyCode ); // 测试一下是否能真正获取到用户填写的验证码！
        //----------3.验证码校验
        //通过request请求对象，验证码被存储此次会话中；会话存储在服务器端，很安全不会被截取
        HttpSession session = request.getSession();
        // Object session.getAttribute( "属性名" )，原本的返回值为Object。需向下转型为 String
        String checkCode_server = (String)session.getAttribute( "CHECKCODE_SERVER" );
        System.out.println(  checkCode_server );
        //获取了本次会话中 本次的验证码后，立马把本次验证码清除掉，下一次访问时就产生新的验证码了
        session.removeAttribute( "CHECKCODE_SERVER" );

        if( !checkCode_server.equalsIgnoreCase(verifyCode) ){ //如果验证码不正确
            //为此请求添加新的提示信息
            //即：在此请求中添加新的键值对，键名为错误提示login_msg，值为提示信息"验证码错误"
            request.setAttribute( "login_msg", "验证码错误！" );
            //跳转到登录页面：服务器内部跳转路径，【不需要加项目名称(虚拟目录名！！)】
            request.getRequestDispatcher( "/jsp/login.jsp" ).forward( request, response );
            return; //直接返回、退出
        }

        //----------4.当验证码通过检验后，说明用户是“真人”登录
        /* 把用户填写的信息封装为User对象，传给下一步数据库的检验
        * 关于BeanUtils工具类的使用，有点忘了，回看 request中的登录案例！
        *  */
        //用户填写的所有信息：在request请求中的键值对 中
        Map< String, String[] > map = request.getParameterMap();
        User userTry = new User(); //被 “填充”的实例对象userTry，尝试登录的用户
        try{ //这个 try...catch 是使用 IDEA快速生成的
            BeanUtils.populate( userTry, map );
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //----------5.调用(自定义的)service实例对象查询
        UserService service = new UserServiceImpl(); //接口的上转型
        //尝试登录的用户为 userTry；登录成功的用户为loginUser
        User loginUser = service.login( userTry );
        //----------6.判断用户是否登录成功
        /* Session 代表着服务器和客户端一次会话的过程。
        * Session 对象存储特定用户会话所需的属性及配置信息。
        * 这样，当用户在应用程序的 Web 页之间跳转时，
        * 存储在 Session 对象中的变量将不会丢失，
        * 而是在整个用户会话中一直存在下去。
        * 当客户端关闭会话，或者 Session 超时失效时会话结束。
        *  */
        if( loginUser!=null ){ //如果得出来的对象非空，说明登录成功了
            /* 将此用户X存入这个会话(而此会话就存储在服务器！！)中。
            * 相当于：在服务器中记住了这位用户的数据
            * 下次此用户X再访问时：通过cookie携带的sessionID，判断是否是同一个用户X，
            * 【跟踪此用户X的 上一次会话】
            * 比如：你在访问淘宝登录之后会持续追踪你的会话，记录你的购物车记录等等
            *  */

            /* 【JAVA代码与JSP的联动！】在当前会话中 添加属性的键值对，键为user，值为 loginUser
            * 在JSP页面中，使用 el表达式 应在 session域-sessionScope中获取此值
            *
            * 或者我们不指明域在哪，让 JSP自己在四个域找去吧
            *
            * ▲ EL 从四个域中获得某个值 ${ key键名 };
            * 同样是依次从 pageContext 域，request 域，session 域，application 域中 获取属性，
            * 在某个域中获取后将不在向后寻找
            *  */
            session.setAttribute( "user", loginUser ); //JAVA代码与JSP的联动！
            /* 登录成功后，跳转页面：/虚拟目录/自定义的文件夹jsp/index.jsp！！
            * request.getContextPath() 获取发出请求所在的虚拟目录
            *  */
            response.sendRedirect( request.getContextPath()+"/jsp/index.jsp" );
        }else{ //登录失败
            /* 在请求request中添加属性的键值对，键为login_msg，值为 "用户名或密码错误！"
            * 在JSP页面中，使用 el表达式 应在request域-requestScope中获取此值
            *  */
            request.setAttribute( "login_msg", "用户名或密码错误！" );
            //跳转到登录页面：服务器内部跳转路径，【不需要加项目名称(虚拟目录名！！)】
            request.getRequestDispatcher( "/jsp/login.jsp" ).forward( request,response );
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
