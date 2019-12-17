package pers.yo.study.Demo01_Request.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


/* 2. 获取请求头数据。方法：

* String getHeader(String name):通过请求头的名称获取请求头的值
* Enumeration<String> getHeaderNames():获取所有的请求头名称
    ▲ Enumeration<String>可以理解为“迭代器”，把它当作“迭代器”来用！！
    Enumeration<T>枚举类型，其中中的两个方法：
    (1) Enumeration<T>实例对象. hasMoreElements() 此枚举中是否还有更多元素 ——类比迭代器中的 hasNext();
    (2) Enumeration<T>实例对象. nextElement() 返回此枚举中的下一个元素 ——类别迭代器中的 next();

*  */

@WebServlet("/demo2") //获取请求头数据，Request Header
public class Demo02Request_requestHeader extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 请求头的格式：
        * 请求头名称: 请求头值
        *
        *  */

        //1.获取所有请求头名称
        // Enumeration实例对象 headerNames
        Enumeration<String> headerNames = request.getHeaderNames();
        //2.把Enumeration<T>当作迭代器来用！！遍历之
        while( headerNames.hasMoreElements() ){ //类似 迭代器的 hasNext()
            //获取下一个元素(请求头名称)，类似迭代器的 next()
            String oneName = headerNames.nextElement();
            String oneValue = request.getHeader( oneName ); //根据请求头名称，获取其对应的 请求头值
            System.out.println( oneName+" = "+oneValue );
        }

        //直接获取 请求头名称为user-agent的 【请求头的值】
        String ua = request.getHeader("user-agent"); //ua 即 user-agent的缩写
        System.out.println( ua );
        /* public boolean contains(CharSequence接口类型 s) ，
        * 当且仅当此字符串包含指定的 char 值序列时，返回 true。
        *
        * 注意这里传入形参是一个 CharSequence接口类型的，
        * 所以我没法直接用它的对象，只能用 CharSequence接口类型的实现类如 String、StringBuilder等
        * 所以这里直接传入 String类型的实例对象即可
        *  */
        System.out.println("----------------------");
        //判断 ua字符串中是否包含某个关键的子字符串(如浏览器名) ua.contains("浏览器名")
        if( ua.contains("Chrome") ){ //注意浏览器的英文要大写
            /* 记录 idea tomcat 控制台乱码问题
            * IDEA的Tomcat某个实例的设置中：
            * VM options(虚拟机选项)中 填写 -Dfile.encoding=UTF-8
            * 然后重启IDEA。控制台即可正常显示
            *  */
            System.out.println( "谷歌来喽！" );
        }else if( ua.contains("Firefox") ){
            System.out.println( "火狐来了" );
        }
    }
}
