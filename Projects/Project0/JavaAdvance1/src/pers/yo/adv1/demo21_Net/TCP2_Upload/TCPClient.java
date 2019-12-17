package pers.yo.adv1.demo21_Net.TCP2_Upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket( "127.0.0.1", 8888 ); //连接服务器
        //从客户端本地处，读文件
        FileInputStream fis = new FileInputStream( new File("E:\\Data\\a.txt") );
        BufferedInputStream bis = new BufferedInputStream( fis );
        //准备使用客户端的网络字节流上传文件
        OutputStream os = socket.getOutputStream();
        int rst = 0;
        while( (rst=bis.read())!=-1 ){ //这里的read是正常的
            os.write( rst ); //使用网络流上传文件至服务器
        };
        /* 【坑1】解决服务器端 while循环的read方法 阻塞问题
        * 在网络流写完后，给服务器再写一个结束标记：
        * void shutdownOutput() 禁用此套接字socket的输出流
        * 官方：对于TCP套接字，任何以前写入的数据都将被发送，而且后跟TCP的正常连接终止序列。
        *  */
        socket.shutdownOutput();

        //准备接收服务器回写的消息
        InputStream is = socket.getInputStream();
        byte[] msg = new byte[1024];
        int byteLen = is.read(msg); //把读到的消息内容存进msg中
        System.out.println( new String( msg ) );//将字节数组，使用字符串的构造方法，转换成字符串
        //释放资源
        bis.close();
        socket.close();

    }
}
