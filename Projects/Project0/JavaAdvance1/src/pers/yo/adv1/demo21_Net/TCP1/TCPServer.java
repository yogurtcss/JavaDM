package pers.yo.adv1.demo21_Net.TCP1;

/*
    TCP通信的服务器端:接收客户端的请求,读取客户端发送的数据,给客户端回写数据
    表示服务器的类:
        java.net.ServerSocket:此类实现服务器套接字。

    构造方法:
        ServerSocket(int port) 创建绑定到特定端口的服务器套接字。

    服务器端必须明确一件事情,必须的知道是【哪个客户端】向我服务器申请通信！！
    所以可以使用accept方法获取到 向我服务器请求的客户端对象Socket
    成员方法:
        Socket accept() 侦听并接受到此套接字的连接。

    服务器的实现步骤:
        1.创建服务器ServerSocket对象和系统要指定的端口号
        ▲ 注：一个Socket实例代表了TCP连接的一个客户端，而一个 ServerSocket 实例代表了TCP连接的一个服务器端，
        ▲ 程序中使用 30000或8888 作为该 ServerSocket 的端口号，通常推荐使用 1024 以上的端口，
        主要是为了避免与其他应用程序的通用端口冲突。
        ▲ 一般在 TCP Socket 编程中，客户端有多个，而服务器端只有一个

        以下注意！！
        ▲ 客户端和服务器端进行交互,必须使用【客户端实例对象Socket】中提供的网络流,不能使用自己创建的流对象

        2.使用ServerSocket对象中的方法accept,获取到请求的客户端对象Socket
        3.使用【Socket对象】中的方法getInputStream()获取网络字节输入流InputStream对象
        4.使用网络字节输入流InputStream对象中的方法read,读取客户端发送的数据
        5.使用【Socket对象】中的方法getOutputStream()获取网络字节输出流OutputStream对象
        6.使用网络字节输出流OutputStream对象中的方法write,给客户端回写数据
        7.释放资源(Socket,ServerSocket)
 */

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket( 8888 ); //创建一个服务器实例对象，端口指定为8888
        /* 一般在 TCP Socket 编程中，客户端有多个，而服务器端只有一个
        * 哪个客户端向我申请通信呀？
        * 我不管你具体名字是什么，我先 accept 一下：若有客户端实例对象socket向我服务器连接，我必能accept到它
        * 若我accept到了哪个socket对象(即 客户端实例对象)，
        * 我就使用这个 socket对象(客户端实例对象)的 网络流 来与这个客户端进行通信
        *  */
        Socket oneSocket = server.accept(); //获取某个申请与我服务器连接的客户端实例对象，接下来全程用客户端实例对象来“整活”

        InputStream is = oneSocket.getInputStream(); //先获取此客户端的字节输入流，准备获取 客户端发来的数据
        byte[] rst = new byte[1024];
        int len = is.read( rst ); //一次读取多个字节
        System.out.println( new String(rst) ); //将字节数组的内容转为字符串

        OutputStream os = oneSocket.getOutputStream(); //先获取此客户端的字节输出流，准备 向客户端“回写”数据
        os.write( "宁好，客户端，已收到宁的消息！".getBytes() ); //正式回写数据：注意，要转成 字节型的数据嗷！

        // 释放资源：关闭客户端、服务器
        oneSocket.close(); //后打开的先关闭
        server.close(); //先打开的后关闭(要等所有客户端都没人连接我了，我服务器最后才关闭嗷！)
    }
}
