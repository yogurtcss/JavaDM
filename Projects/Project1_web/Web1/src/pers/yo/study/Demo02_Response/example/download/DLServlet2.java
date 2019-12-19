package pers.yo.study.Demo02_Response.example.download;

import pers.yo.study.Demo02_Response.example.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/dlServlet2")
public class DLServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.从请求参数中，获取文件名

        //<a href=  "/Web1/dlServlet2?filename=1.avi"  >视频</a>
        //请求参数名为 filename，请求参数值为 1.avi
        String fileName = request.getParameter( "filename" ); // 1.avi

        ServletContext svlContext = this.getServletContext(); //获取servlet上下文对象(运行环境)

        //找到此文件 "/img/"+fileName 在服务器中的地址为path
        String path = svlContext.getRealPath( "/img/"+fileName );

        // 2.设置响应头：告诉浏览器，我要下载东西喽
        String mimeType = svlContext.getMimeType( fileName );
        response.setHeader( "content-type", mimeType  ); //告诉浏览器 返回的文件的MIME类型是啥

        //解决中文的文件名问题
        //获取user-agent请求头
        String ua = request.getHeader( "user-agent" );
        //使用工具类方法编码文件名即可
        fileName = DownLoadUtils.getFileName( ua, fileName );

        response.setHeader( "content-disposition", "attachment;filename="+fileName );


        //根据path，利用文件字节输入流 读入，然后通过 servlet的字节输出流 写出到页面中
        FileInputStream fis = new FileInputStream( path );
        ServletOutputStream sos = response.getOutputStream(); //获取servlet的字节输出流

        int len = 0; //记录一次读取的有效字节数目
        byte[] bytes = new byte[1024]; //缓冲区数组：字节数组
        while( (len=fis.read(bytes))!=-1 ){ //当读取的字节数还没到-1时
            sos.write( bytes, 0, len ); //写出此缓冲区数组的内容：从 下标offset=0开始，每次输出的长度为len
        }

        //关闭资源：后打开的先关闭
        sos.close();
        fis.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
