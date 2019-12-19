package pers.yo.study.Demo02_Response.example.download;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/*
既然浏览器发送所有的请求都是去找 Servlet 的话，
那么我就写一个 Servlet，当别人访问我这个 Servlet 的时候，它们就可以下载我这个图片了！

*  */


@WebServlet("/dlServlet1") // dl servlet 即 download servlet
public class DLServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过 IO流 读取到这个文件嗷！

        /* java 的文件上传下载都是通过 io 流来完成的，
        * 既然要下载图片，首先要能够读取到它
        * 我佛了，文件在服务器中的路径"/" 就是 对应于 web根目录下！！
        *  */

        String path = this.getServletContext().getRealPath( "/img/九尾.jpg" ); //获取资源在服务器上的路径
        /* 若文件名出现中文：为了解决文件名乱码，我们要进行 URL 编码
        * response.setHeader(   "Content-Disposition",
        *                       "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8")
        * );
        *  */

        FileInputStream fis = new FileInputStream( path ); //以文件字节输入流读取该path指向的资源

        /* 根据资源在服务器上的路径全名， 获取到该资源文件名 -- 字符串截取substring
        * 注意：路径在电脑上保存是\\形式的
        * 如： H:\\programs\\ AAA \\ a.txt
        *
        * 显然我要的文件名是 a.txt
        * 问：字符串截取substring()的范围是从哪到哪？ [ begin, end)
        * 答：最后一个路径分隔符 \\ 的下一个字符 'a' 开始，直到原字符串的末尾 't'
        *
        * lastIndexOf( "\\" ) 获取到 \\的下标
        * lastIndexOf( "\\" )+1 获取的就是 'a'的下标了！ 我佛了真妙啊！
        *  */

        /* public int lastIndexOf(String str):
        * 返回指定字符在此字符串中最后一次出现处的索引，
        * 如果此字符串中没有这样的字符，则返回 -1。
        *  */
        //截取 从【最后一个路径分隔符的下标+1】 处开始，直到原字符串末尾的 子字符串，就是我要的文件名了！！
        String fileName = path.substring( path.lastIndexOf("\\")+1 );

        //2.告诉浏览器 我要下载这个文件喽！

        //设置响应头：setHeader(String name, String value)
        //我佛了，之前我这里的 "Content-Disposition" 又写错了！
        response.setHeader( "Content-Disposition", "attachment;filename"+fileName  );

        //3.将读取到的内容，回写给浏览器 servletOutputStream

        //有点忘了 fis.read(缓冲区数组)的操作了

        /* int read() 若没有传入形参时，
        * 默认情况下，对这个流一个一个字节地读，
        * 返回的int值 就是这个字节的int表示方式；当读取到文件的末尾时返回-1
        * 所以，此时欲打印输出“读入的数据”时。打印的就是 int read(空参) 的返回值！！
        * 【注意这与下一个方法的返回值 的区别 嗷！】
        *
        *
        * int read( 字节数组bytes[1024] ) 传入一个字节数组bytes(称为缓冲区数组)  ——一次读取多个字节的方法
        * 根据字节数组的长度len(在这里长度为1024)，一次读取len个字节存到缓冲区数组中
        * 返回值为：读入缓冲区数组的总字节数；如果因为已到达流末尾而不再有数据可用，则返回 -1。
        * 注意，此时欲打印输出“读入的数据”时，打印的不是 int read(字节数组)的返回值，而应打印 缓冲区数组中的元素！！
        *
        * 形参byte[] b的注释
        * 1.缓冲作用，存储读取到的字节
        * 2.byte[]数组的长度定义为 1024(1kb)或1024的整数倍
        * 3.若缓冲数组长度为len，则一次可读取len个字节
        *
        *  */

        /*  实例代码

        int len = 0; //记录每次read读取的有效字节数
        while( (len=fis.read(bs1))!=-1 ){ // len=fis.read(bs1)为每次读取的有效字节数，还没到-1，则表示还没到结尾
            // 注意，此时欲打印输出“读入的数据”时，打印的不是 int read(字节数组)的返回值，而应打印 缓冲区数组中的元素！！
            System.out.println( new String( bs1, 0, len ) ); //每次输出 缓冲区数组中 len个元素
        }

        */
        ServletOutputStream sos = response.getOutputStream();

        int len = 0; //记录每次read读取的有效字节数
        byte[] bytes = new byte[1024]; //缓冲区数组：字节数组
        while( (len=fis.read(bytes))!=-1 ){ //当每次读取的字节数未达到流的末尾时
            /* sos.write(
            *   byte[] bytes 一次读取内容的字节数组
            *   int off 全称即offset，此字节数组的开始索引
            *   int len 每次传输/处理/操作的长度或有效字节数 );
            *  */
            sos.write( bytes, 0, len );
        }

        //关闭资源：后打开的先关闭
        sos.close();
        fis.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
