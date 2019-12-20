package pers.yo.study.Demo03_Cookie.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 在服务器中的Servlet判断是否有一个名为lastTime的cookie
 1.有：不是第一次访问
    1.响应数据：欢迎回来，您上次访问时间为:2018年6月10日11:50:20
    2.写回Cookie：lastTime=2018年6月10日11:50:01
 2.没有：是第一次访问
    1.响应数据：您好，欢迎您首次访问
    2.写回Cookie：lastTime=2018年6月10日11:50:01
 */


/*
* SimpleDateFormat 是一个以国别敏感的方式格式化和分析数据的具体类。
* 导包 import java.text.SimpleDateFormat;
* 构造方法：SimpleDateFormat( String pattern传递指定的模式 )
*
* String pattern传递指定的模式，区分大小写嗷！
* 相当于各日期上的占位符：“年”用“年”的占位符y，“月”用“月”的占位符M
* y 年
* M 月 【大写】
* d 日
* H 时 【大写】
* m 分
* s 秒
*
* 如 2018-01-01 15:05:00，则指定的模式字符串为：
* 模式中的字母不能改，而连接模式的符号可以变
* 如yyyy-MM-dd HH:mm:ss
* 可连接模式的符号可改为：yyyy年MM月dd日 HH时mm分ss秒
*
* 注：Date date=new Date();//直接new一个date对象出来，就是 获得系统当前的时间
*
* String 【simpleDateFormat实例对象】sdf.format( new Date()-Date对象 )
* 按照规定的日期格式，对传入的日期进行格式化
*  */

@WebServlet("/CookieTest") //为 注解的servlet模板 修改的代码，加个斜杠
public class CookieTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应的消息体的数据格式及编码
        response.setContentType( "text/html;charset=utf-8" );

        //1.获取所有Cookie
        Cookie[] cs = request.getCookies();
        boolean flag = false; //没有cookie的为lastTime

        if( cs!=null && cs.length>0 ){
            for( Cookie c : cs ){
                String name = c.getName(); //获取cookie的名字

                //每一次读取到属性名为lastTime的cookie时，都修改其属性值！！
                if( "lastTime".equals(name) ){ //判断 cookie名是否为 lastTime
                    flag = true; //是，则返回true

                    /* 重新改写当前的cookie：属性名仍然为 lastTime，值为 当前的年月日 时分秒
                    *
                    * 设置 当前的年月日 时分秒，我忘了怎么操作了，回去看 “日期类”！
                    *  */

                    //获取系统当前时间 current time
                    Date currTime = new Date(); //直接new一个date对象出来，就是获取系统当前时间了
                    //规定一个日期格式
                    //日期格式字符串pattern中的分隔符可以改变：如 yyyy-MM-dd HH:mm:ss
                    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy年MM月dd日 HH时mm分ss秒" );
                    //对日期进行格式化。格式化后的日期为 current time formatted
                    //原来format的过去式、过去分词是 双写t + ed！！佛了
                    String currTime_formatted = sdf.format( currTime );


                    //设置cookie的属性值之前：先把此属性值进行URL编码，然后再存进cookie中进行传输

                    // 解决中文传输问题：自己先URL编码，然后自行URL解码显示到浏览器中
                    System.out.println( "编码前："+currTime_formatted );
                    //正式编码咯！
                    //在一开头我已经通过response.setContentType( "text/html;charset=utf-8" )了
                    currTime_formatted = URLEncoder.encode( currTime_formatted, "utf-8" );

                    System.out.println( "编码后："+currTime_formatted );

                    /* 当前cookie对象的属性名仍为 lastTime，需要将此cookie的属性值修改为当前时间
                    * 传输时，cookie的属性值是：使用 URL编码后的属性值
                    * 若以原数据进行传输，有些特殊字符(如空格) 则无法解码！！切记！！
                    * */
                    c.setValue( currTime_formatted );
                    c.setMaxAge( 60*60*24*30 ); //设置cookie的存活时间：1个月
                    //正式在response中添加cookie (注：传输时，cookie的属性值是：使用 URL编码后的属性值)
                    response.addCookie( c );


                    //每一次读取到属性名为lastTime的cookie时，都打印其 修改后的 属性值！！
                    String value = c.getValue(); //此时 cookie c的属性值是 URL编码后的值，需手动进行URL解码进行显示！！
                    System.out.println( "解码前："+value );
                    //自行URL解码：
                    value = URLDecoder.decode( value, "utf-8" );
                    System.out.println( "解码后："+value );
                    //response.getWriter ().writer（）, 只能打印输出文本格式的（包括 html 标签），不可以打印对象。
                    response.getWriter().write( "<h1>欢迎回来，您上次的访问时间为："+value+"</h1>" );

                    break; //退出循环了
                }
            }
        }

        if( cs==null || cs.length==0 || flag==false ){ //没有cookie 即第一次访问
            Date currTime = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy年MM月dd日 HH时mm分ss秒" );
            String currTime_formatted = sdf.format( currTime );

            System.out.println( "编码前："+currTime_formatted );
            currTime_formatted = URLEncoder.encode( currTime_formatted, "utf-8" ); //编码
            System.out.println( "编码后："+currTime_formatted );

            Cookie cookie = new Cookie( "lastTime", currTime_formatted );
            cookie.setMaxAge( 60*60*24*30 );
            response.addCookie( cookie );
            response.getWriter().write( "<h1>您好，欢迎您首次访问！</h1>" );
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
