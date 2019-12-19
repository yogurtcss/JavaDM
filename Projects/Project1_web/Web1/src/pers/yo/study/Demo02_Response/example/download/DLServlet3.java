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

@WebServlet("/dlServlet3")
public class DLServlet3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* <a href=  "/Web1/dlServlet2?filename=1.avi"  >视频</a>
        * 请求参数名为 filename，请求参数值为 1.avi
        *  */

        //1.获取文件在服务器中的地址，【准备在第3步中】通过字节输入流读取到该文件
        String fileName = request.getParameter( "filename" ); //获取请求中，所需要的文件名
        ServletContext svlContext = this.getServletContext(); //获取servlet上下文对象(获取全局参数)
        String path = svlContext.getRealPath( "/img/"+fileName ); //【正式地】获取文件在服务器中的地址


        //2.设置消息头：为此文件名解决编码问题
        String mimeType = svlContext.getMimeType( fileName ); //获取 【在互联网通信过程中定义的一种文件数据类型】MIME类型
        response.setHeader( "content-type", mimeType ); //服务器告诉客户端 本次相应的数据格式为MIME类型(也可以指定编码格式)

        //当文件名为中文时，解决乱码问题
        String ua = request.getHeader( "user-agent" ); //获取 user-agent
        fileName = DownLoadUtils.getFileName( ua, fileName ); //已解决乱码问题的、改良后的fileName
        response.setHeader( "content-disposition", "attachment;filename="+fileName );


        //3.正式回写数据response
        ServletOutputStream sos = response.getOutputStream();
        FileInputStream fis = new FileInputStream( path );
        byte[] bytes = new byte[1024]; //缓冲区 字节数组
        int len = 0; //一次读取的有效字节数
        while( (len=fis.read(bytes))!=-1 ){
            sos.write( bytes, 0, len ); // 写出字节数组的内容：从下标offset=0，每次写出 len长度的内容
        }

        //关闭资源：后打开的 先关闭
        sos.close();
        fis.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
