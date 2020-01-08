package cn.itcast.travel.web.servlet.past;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//复习：BaseServlet2

//@WebServlet("/BaseServlet2") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class BaseServlet2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //---1.获取请求URI
        String uri_xieGang = req.getRequestURI(); //请求的URI，前面带斜杠的！！

        //---2.从请求URI中，截取字符串：得到方法名methodName
        String methodName = uri_xieGang.substring( uri_xieGang.lastIndexOf("/")+1 );

        //---3.通过this关键字，拿到方法对象method
        try { //使用IDEA 快速帮我生成try...catch
            Method method = this.getClass().getMethod( methodName, HttpServletRequest.class, HttpServletResponse.class );
            //---4.invoke执行方法：各模块下的各方法，形参列表也是 HttpServletRequest req, HttpServletResponse resp
            //这里直接使用父类的形参 req、resp即可！
            method.invoke( this, req, resp );
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
