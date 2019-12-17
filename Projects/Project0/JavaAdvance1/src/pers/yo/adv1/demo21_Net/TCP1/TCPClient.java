package pers.yo.adv1.demo21_Net.TCP1;

/* TCP通信中的客户端、服务器端

一、客户端：
1.向服务器发送连接请求
2.连接成功后，向服务器发送数据
3.当服务器有返回数据时，读取服务器“回写”的数据

表示客户端的类：java.net.Socket——此类实现“客户端套接字”(简称 “套接字”)；
套接字：包含 【IP地址和端口号】的网络单位，是两台机器间通信的端点

构造方法：
Socket( String host, int port ) 创建一个“流套接字” 并将其连接到 指定主机host上 的指定端口号port
参数:
String host:服务器主机的名称/服务器的IP地址
int port:服务器的端口号

成员方法:
OutputStream getOutputStream() 返回此套接字的输出流。
InputStream getInputStream() 返回此套接字的输入流。
void close() 关闭此套接字。

实现步骤:
1.创建一个客户端实例对象Socket,构造方法绑定服务器的IP地址和端口号
▲ 注：一个Socket实例代表了TCP连接的一个客户端，而一个 ServerSocket 实例代表了TCP连接的一个服务器端，
▲ 程序中使用 30000或8888 作为该 ServerSocket 的端口号，通常推荐使用 1024 以上的端口，
主要是为了避免与其他应用程序的通用端口冲突。
▲ 一般在 TCP Socket 编程中，客户端有多个，而服务器端只有一个

2.【准备传输数据：获取客户端实例对象的字节输出流】使用Socket对象中的方法getOutputStream()获取网络字节输出流OutputStream对象
3.【正式传输数据】使用网络字节输出流OutputStream对象中的方法write,给服务器发送数据
4.使用Socket对象中的方法getInputStream()获取网络字节输入流InputStream对象
5.使用网络字节输入流InputStream对象中的方法read,读取服务器回写的数据
6.释放资源(Socket)

注意:
1.客户端和服务器端进行交互,必须使用【客户端实例对象Socket】中提供的网络流,不能使用自己创建的流对象
2.当我们创建客户端对象Socket的时候,就会去请求服务器和服务器经过3次握手建立连接通路
    这时如果服务器没有启动,那么就会抛出异常ConnectException: Connection refused: connect
    如果服务器已经启动,那么就可以进行交互了

*  */


import java.net.Socket;
import java.io.OutputStream; //客户端与服务器端通信中，使用字节输出流；因为不仅仅传输“字符的数据”，而是传输“字节数据”
import java.io.InputStream;
import java.io.IOException;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        /* 服务器的实例对象ServerSocket的端口号设置为8888或30000
        * 通常推荐使用 1024 以上的端口，主要是为了避免与其他应用程序的通用端口冲突。
        *  */
        Socket socket = new Socket( "127.0.0.1", 8888 );  //创建一个客户端实例对象
        /* 服务器端与客户端进行数据交互：必须使用客户端实例对象的网络流(字节输出流、字节输入流)，
        * 因为这样具有 客户端的特征性！！ 更精准地与客户端进行数据交互！！
        *  */
        OutputStream os = socket.getOutputStream(); //获取本客户端实例的 字节输出流，准备发送数据
        os.write( "你好，服务器端！".getBytes() );//正式发送数据：注意，要转成 字节型的数据嗷！

        //获取本客户端实例的 字节输入流，准备接收客户端回写的数据
        InputStream is = socket.getInputStream();
        /* 正式接收客户端回写的数据
        * 注意，读数据时，不要用 while(...)循环方式来读，不然会卡着不动的…
        *  */
        byte[] rst = new byte[1024];
        int len = is.read( rst ); //一次读取多个字节
        System.out.println( new String(rst) ); //将字节数组的内容转为字符串

        //释放资源：关闭客户端
        socket.close();
    }
}
