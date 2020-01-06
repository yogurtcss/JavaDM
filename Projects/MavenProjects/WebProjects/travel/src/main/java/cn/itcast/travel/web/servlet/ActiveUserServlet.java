package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeUserServlet") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class ActiveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 前面的铺垫：在RegistUserServlet中：调用了service层的regist()方法，——向用户user数据中填入了【全局唯一字符串】UUID
        * 注册成功后，发送给用户的激活邮件中：跳转的链接把【请求参数code=用户的UUID】传给了 activeUserServlet
        * 过滤器 CharsetFilter已经完成了 UTF-8的设置
        *  */
        //---1.获取请求参数code的值
        //发送给用户的激活邮件中：跳转的链接把【请求参数code=用户的UUID】传给了 activeUserServlet
        String code = request.getParameter("code");
        //---2.判断激活码是否为空：然后激活用户——修改用户status为Y
        if( code!=null ){
            UserService service = new UserServiceImpl(); //接口回调， 向上转型
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
