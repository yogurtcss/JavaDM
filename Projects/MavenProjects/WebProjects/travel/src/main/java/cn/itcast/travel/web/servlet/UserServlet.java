package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*") //一个user模块对应一个servlet
/* 访问任意一个关于user模块的方法，都会被匹配到这个 UserServlet；
* 然后再到这个UserServlet中找到对应的方法来执行
*  */
public class UserServlet extends BaseServlet { //继承BaseServlet，等候方法的分发嗷！
     /* ---------------关于各模块对应的servlet下的各方法名的【修饰符】
     * protected void doPost(HttpServletRequest request, HttpServletResponse response)
     * 注意，UserServlet类下的修饰符 不要写成private，也不要写成protected！【要修改为 public】
     * 因为我要在BaseServlet中，通过【反射】 获取到 这个类UserServlet下的 任意一个方法！！
     * 若方法名的修饰符为private或protected，则我只能【暴力反射】来获取方法了！显然是不安全的！
     *  */


    /* ---------------关于【暴力反射】
    * Method[] getDeclaredMethods(); //不考虑修饰符，获取所有的成员方法（包括private修饰的）
    * Method getDeclaredMethod( String name, 类<?>... parameterTypes ); //不考虑修饰符，获取指定名字的成员方法（包括private修饰的）
    *
    * 在使用 【两个带Declared】的方法后，
    * 必须紧接着执行一个语句：
    * 此private或proteced的【Field 或Constructor 或Method 的】中间实例对象.setAccessible(true)
    * 暴力反射，忽略 “访问权限修饰符”的安全检查；
    * 这样，此 private或protected的成员方法才能被正常访问/使用
    * 否则抛出“非法访问”的异常
    * */

    private UserService service = new UserServiceImpl(); //接口回调，向上转型

    //注册
    public void regist( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        //---0.设置编码
        //request.setCharacterEncoding( "utf-8" );
        //不用设置编码了，使用过滤器 CharsetFilter 即可自动设置编码

        //---1.验证校验
        //1.1 拿到用户填写的验证码、客户端持有的正确的验证码
        String ccode_user = request.getParameter( "check" );//用户输入的验证码 check code
        //验证码是存储在服务器的session中，所以通过session来获取服务器中正确的验证码
        HttpSession session = request.getSession();
        //在 checkcodeServlet提前设置session的attribute，键名为"CHECKCODE_SERVER"，值为当前的验证码
        String ccode_server = (String)session.getAttribute("CHECKCODE_SERVER");

        //1.2 清除上一次的验证码：获取了验证码后，过河拆桥，清除本次验证码，为了验证码只能用一次嗷
        session.removeAttribute( "CHECKCOE_SERVER" );

        //1.3 比较验证码
        if( ccode_user==null || !ccode_user.equalsIgnoreCase(ccode_server) ){ //不区分大小写比较equalsIgnoreCase
            //这是反面情况：验证码为空或验证码错误
            ResultInfo info = new ResultInfo(); //用于封装后端返回前端数据对象

            //1.3.1 注册失败时，对后端的数据对象【封装】(填充)数据
            info.setFlag( false ); //设置状态码为false
            info.setErrorMsg( "验证码错误" ); //设置错误提示信息

            //1.3.2 将封装好的对象转换为 JSON格式对象
            ObjectMapper om = new ObjectMapper(); //Jackson核心对象
            response.setContentType( "application/json;charset=utf-8" );
            //将obj对象转换为JSON字符串，并将json数据填充到字节输出流中
            om.writeValue( response.getWriter(), info );
            return;  //这里可以不用写return了，直接就没事干了
        }

        //---2.验证码通过时，封装对象
        Map<String,String[]> map = request.getParameterMap();
        //2.1 将map封装为对象，待传入regist(User user)方法中
        User userBean = new User(); //待填充的对象
        try { //使用IDEA快速生成的try...catch
            BeanUtils.populate( userBean,map );
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //---3.调用service层 ，完成注册
        boolean flag = service.regist( userBean ); //注册是否成功的标志flag

        //---4.向后端返回数据ResultInfo info
        ResultInfo info = new ResultInfo();
        /* 还需要根据注册标志flag，填写errorMsg
         * 注册成功时，true，无错误返回信息
         * 注册失败时，false，回写错误提示信息
         *  */
        if( flag==true ){ //
            info.setFlag( true );
        }else{
            info.setFlag( false );
            info.setErrorMsg( "注册失败！" );
        }

        //正式向后端返回JSON格式数据
        ObjectMapper om = new ObjectMapper();
        //这次暂时不用 om.writeValue( response.getWriter流对象, info )这语句喽！
        String json = om.writeValueAsString( info ); //先转换为JSON格式数据

        response.setContentType( "application/json;charset=utf-8" ); //设置 数据接受格式及编码
        response.getWriter().write( json ); //正式回写数据
    }

    //登录
    public void login( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        //---1.在登录页面，获取用户输入的数据 map_用户名Username与密码Password，各取其首字母大写 UP
        Map<String,String[]> map_UP = request.getParameterMap(); //U即用户名Username，P即密码Password，取首字母大写嗷！

        //---2.将 map_用户名与密码 封装为对象 user_umpwd  只包含用户名username、password的user对象
        User user_UP = new User(); //待填充数据的 实例对象
        try { //使用IDEA快速生成 try...catch
            BeanUtils.populate( user_UP, map_UP ); //正式填充数据
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //---3.调用service层的 findByUsernameAndPassword，通过用户名和密码 在数据库中查询有无此用户
        User userFromSQL = null; //初始化这个实例对象，以接收数据库查询的结果
        try{
            userFromSQL = service.login( user_UP ); //正式通过用户名和密码 在数据库中查询有无此用户
        }catch( Exception e ){
            //不做处理嗷
        }
        //---4.准备响应数据
        // -----判断：4.1 用户是否存在 --回写后端数据；  4.2 当用户已存在时此用户是否已激活 status='Y'---回写后端数据
        // 4.1 、4.2 需分别 回写后端数据ResultInfo嗷！

        ResultInfo info = new ResultInfo(); //在后端准备回写的数据。 稍后通过response发给前端
        /* 用于封装后端返回前端数据对象 ResultInfo，内容如下
         * private boolean flag;//后端返回结果正常为true，发生异常返回false
         * private Object data;//后端返回结果数据对象
         * private String errorMsg;//发生异常的错误消息
         *  */

        //通过用户名和密码 在数据库中查询不到此用户(结果为null)，说明用户名或密码错误了
        if( userFromSQL==null ){
            info.setFlag( false ); //发生异常
            info.setErrorMsg( "用户名或密码错误" ); //填写报错信息
        }
        //接着判断，此用户存在时，看看此用户的status是否为Y
        if( userFromSQL!=null && !userFromSQL.getStatus().equals("Y") ){ //此用户status不为Y，则未激活
            info.setFlag( false ); //发生异常
            info.setErrorMsg( "您尚未激活，请进入您的个人邮箱中点击\"激活邮件\"进行激活嗷！" ); //里面用了一个转义的双引号
        }
        if( userFromSQL!=null && userFromSQL.getStatus().equals("Y") ){ //此用户激活状态为Y，激活喽！
            info.setFlag( true ); //无异常
            //登录成功后，在服务器中保存此用户对象的全部信息！！注意，键名是 成功登录的用户！"user_successfulLogin"
            request.getSession().setAttribute( "user_successfulLogin", userFromSQL );
            /* 在index.html的header.html中：实现效果 欢迎回来，XXX用户
             * 已登录用户的信息，被储存在服务器的session中，
             * 通过ajax请求，从session中拿到这个已登录用户的信息！
             *  */
        }

        //---5.正式返回数据response
        ObjectMapper om = new ObjectMapper();
        response.setContentType( "application/json;charset=utf-8" ); //设置返回数据格式
        //om.writeValue( response.getWriter(), info ); //正式返回数据：转换为json格式，并放入流中
        String json = om.writeValueAsString( info );
        response.getWriter().write( json ); //正式返回数据
    }

    //获取当前已登录用户的信息
    public void findOne( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* index.html 首页的 header.html 头部中，
         * 【已登录的用户，被储存在服务器的session中！
         * 通过ajax请求，从session中拿到这个已登录用户的信息！】
         *
         * request.getSession().setAttribute( 键名"user_successfulLogin", userFromSQL );
         *
         * 前端期望后端返回的数据内容是：
         * { uid:1, name:'张三' }
         *  */
        //登录成功后，在服务器中保存此用户对象的全部信息！！注意，键名是 成功登录的用户！"user_successfulLogin"

        //---header.html的get请求中，没有提交请求参数
        ResultInfo info = new ResultInfo(); //后端封装的数据对象
        /* 从本次请求中，获取此请求所属的会话session，
         * 此会话session就储存着当前用户的信息
         * session实例对象.getAttribute( "..." ) //返回的是Object类型
         *  */
        User user_successfulLogin = (User)request.getSession().getAttribute( "user_successfulLogin" );

        //---准备返回的数据
        ObjectMapper om = new ObjectMapper(); //Jackson核心对象
        response.setContentType( "application/json;charset=utf-8" ); //设置数据格式和编码
        //---正式返回数据
        //String json = om.writeValueAsString( user_successfulLogin );
        //response.getWriter().write( json ); //返回的可能是中文数据，用writer的流
        om.writeValue( response.getWriter(), user_successfulLogin ); //把已登录用户数据转为json格式，并放入流中
    }

    //退出
    public void exit( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        //---前端没有发出请求参数

        //---1.访问此ExitServlet，销毁整个session
        request.getSession().invalidate();
        /* request.getSession().invalidate()
         * session.removeAttribute ()：删除session中的某一个用户状态属性。
         * session.invalidate()：销毁整个会话session，此 session中所有的用户状态属性都将不存在。
         *  */

        //---2.跳转到登陆页面，重定向时，必需带上虚拟目录！
        //String request实例对象.getContextPath()方法：获取虚拟目录
        response.sendRedirect( request.getContextPath()+"/login.html" );
    }

    //激活
    public void active( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* 前面的铺垫：在RegistUserServlet中：调用了service层的regist()方法，——向用户user数据中填入了【全局唯一字符串】UUID
         * 注册成功后，发送给用户的激活邮件中：跳转的链接把【请求参数code=用户的UUID】传给了 activeUserServlet
         * 过滤器 CharsetFilter已经完成了 UTF-8的设置
         *  */
        //---1.获取请求参数code的值
        //发送给用户的激活邮件中：跳转的链接把【请求参数code=用户的UUID】传给了 activeUserServlet
        String code = request.getParameter("code");
        //---2.判断激活码是否为空：然后激活用户——修改用户status为Y
        if( code!=null ){
            //激活用户: 根据激活码code找到该用户，并修改该用户的状态status为 Y；返回激活状态的标志flag --布尔值
            boolean flag = service.active( code );
            //---3.根据激活标志，准备返回数据 resMsg
            // 向浏览器返回数据——HTML文本(如纯文字 或a标签、div标签等) 直接显示在html页面上的！
            String resMsg = null; //初始化：返回的字符串
            if( flag==true ){ //激活标志为true
                /* 【坑】注意这里 login.html的地址：
                 * 正确的访问网址名应该是 localhost:8080/travel/login.html
                 * 这里写的是相对位置 省略了前面的服务器地址localhost:8080/travel/ 了(末尾已经带斜杠了)
                 *
                 * Maven中：src/main/webapp 这个目录存放 Web配置文件，JSP 和静态文件
                 * src/main/webapp 相当于IDEA中 Java WEB项目的【web目录】(下边放 WEB-INF 目录)
                 * 访问 某个静态资源页面时，总是到 此webapp目录(类比 Java WEB项目的web目录)下去找此静态资源！！
                 *
                 * 而我已知在IDEA中 Java WEB项目中 不带前缀地访问项目中的某个网页
                 * 默认就是带上前缀 localhost:8080/项目名/ 了
                 *
                 * 在maven中，这里也是同理的！不带前缀地访问项目中的某个网页
                 * 默认就是带上前缀 localhost:8080/项目名/ 了
                 *
                 *  */
                resMsg = "激活成功，请<a href='login.html'>登录</a>";
            }else{
                resMsg = "<h1>激活失败，请联系管理员！</h1>";
            }

            //---4.正式返回数据resMsg
            // ——向浏览器返回数据——HTML文本(如纯文字 或a标签、div标签等) 直接显示在html页面上的！
            // HTML文本(如纯文字 或a标签、div标签等) 对应的MIME类型为 text/html
            response.setContentType( "text/html;charset=utf-8" ); //设置 数据格式与解码格式
            response.getWriter().write( resMsg ); //正式返回数据resMsg
        }
    }


}
