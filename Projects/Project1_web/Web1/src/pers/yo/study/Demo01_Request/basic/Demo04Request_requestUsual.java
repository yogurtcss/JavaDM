package pers.yo.study.Demo01_Request.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet("/demo4")
public class Demo04Request_requestUsual extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post 获取请求参数

        //get请求方式：tomcat 8 已经将get方式的中文乱码问题解决了
        //解决post请求中获取中文时乱码的问题：在一开始设置request的编码格式为utf-8
        request.setCharacterEncoding("utf-8");
        //根据参数名称获取参数值
        String username = request.getParameter( "username" );
        System.out.println( "post："+username );

        //根据参数名称获取参数值的数组
        System.out.println( "-------------" );
        String[] hobbies = request.getParameterValues( "hobby" );
        for( String one: hobbies ){
            System.out.println( one );
        }

        //获取所有请求的参数名
        System.out.println( "-------------" );
        Enumeration<String> paraNames = request.getParameterNames();
        while( paraNames.hasMoreElements() ){
            String oneParaName = paraNames.nextElement();
            /* 注意到 oneParaName实际名为hobby，它对应着两个值 study和game！
            * 所以 oneParaValue应声明为 字符串数组 String[]
            * hobby 对应一个字符串数组 [study,game]
            * 然后再到字符串数组中遍历，输出值
            *  */

            //request.getParameter( ... ) 这个方法 根据方法名，只返回一个值，而不是数组
            //String oneParaValue = request.getParameter( oneParaName ); //在request请求中，根据参数名获取参数值
            //System.out.println( oneParaName+"="+oneParaValue );

            String[] paraValues = request.getParameterValues( oneParaName );
            for( String oneParaValue : paraValues ){
                System.out.println( oneParaName+"="+oneParaValue );
            }
        }

        /* 获取所有参数的map集合
        * 键为 请求参数名；
        * 值为 此请求参数名对应的(可能)多个参数值的【数组】
        *
        * 如 键为hobby，它对应着两个值：study和game
        *  */
        System.out.println( "-------------" );
        Map<String, String[]> paraMap = request.getParameterMap();
        Set<String> keySet = paraMap.keySet();
        for( String oneKey : keySet ){
            String[] values = paraMap.get(oneKey);
            for( String oneValue: values ){
                System.out.println( oneKey+"=="+oneValue );
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
