package pers.yo.adv1.demo21_Net.TCP2_Upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.util.Random; //防止同名文件被覆盖

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888 );

        /* 【改进2】让服务器一直处于监听状态(死循环 accept方法)  while(true){...}
        * 当有一个客户端上传文件时，服务器就保存一个文件
        *  */
        while( true ){ //让服务器一直处于监听状态(死循环 accept方法)
            Socket oneSocket = server.accept();

            /* 【改进3】使用多线程技术，提高程序的效率
            * 当有一个客户端上传文件时，为此客户端单独开启一个线程 以完成文件的上传
            *  */

            new Thread( new Runnable(){

                /* 实例对象bos在重写的run方法之外提前声明了，
                * 这样 在异常处理try...catch中 就不报错了(就能识别到bos变量)
                * 一开始我也想不到会这样写的……在改进代码时才知道会报这个错，故记录一下。
                *  */
                BufferedOutputStream bos; //提前声明了一下

                public void run(){ //线程的任务就是 上传文件
                    /* 把 上传文件的代码 复制进来。差点就想用 lambda表达式了！！
                    * 但是因为代码内容贼多贼长，就用不上lambda表达式！！
                    *
                    * 注意！！文件上传的代码中，流的操作需要进行异常处理
                    *
                    * 又想偷懒在此子类中重写的run()方法 throws IOException？
                    * 不好意思，你不能这样做！！因为，最原始的接口中的方法：
                    * public abstract void run() 是没有声明任何异常的！！
                    * (原始接口的run方法没有throws IOException等异常)
                    * 所以，子类重写的run方法，也不能声明任何异常！！
                    *
                    *
                    -----------------------------------------------------------------------------------
                    【老番新看环节】子类、父类的异常——温故知新
                    1.子类在继承【父类】时，如果父类的方法抛出异常，那么子类的覆盖方法只能抛出父类的异常或者该异常的子类。
                    2.如果【父类方法】抛出多个异常，子类在覆盖该方法时，只能抛出父类异常的子类。
                    3.如果父类或者接口的方法中没有异常抛出，子类在覆盖方法时，也不可以抛出异常。
                      如果子类方法发生了异常，就必须要进行 try 处理，绝对不能抛。
                    -----------------------------------------------------------------------------------

                    * 既然不能偷懒了，那就手动try...catch吧！
                    * 注意，这里可能的异常是 IOException，则catch中的异常类型就是 IOException e 了
                    *  */

                    try{
                        //------------------文件上传，代码开始----------------------

                        /* 【改进1】为写入的文件设置不同的文件名：防止同名文件被覆盖
                        * 文件名规则：域名 + 时间毫秒值System.currentTimeMillis() + 随机数new Random().nextInt(很大的数字9999)
                        * 最后的随机数范围设置为很大99999，这样文件名就很难重复了
                        *  */
                        String fileName = "pers" + System.currentTimeMillis() + new Random().nextInt(99999);
                        //准备在服务器这里：写入文件至硬盘中——文件字节输出流
                        FileOutputStream fos = new FileOutputStream( new File("E:\\Data\\" + fileName + ".txt") );

                        /* 实例对象bos在重写的run方法之外提前声明了，
                        * 这样 在异常处理try...catch中 就不报错了(就能识别到bos变量)
                        * 一开始我也想不到会这样写的……在改进代码时才知道会报这个错，故记录一下。
                        *  */
                        bos = new BufferedOutputStream( fos ); //使用缓冲流

                        //从客户端处读取文件(的字节信息)
                        InputStream is = oneSocket.getInputStream();
                        int rst;
                        while( (rst=is.read())!=-1 ){ //注意，这个is字节输入流 是客户端socket的网络流！！不是本地的文件流！！
                            /* 本地的文件流中，它是自己知道读到文件末尾了，(fis、bis使用read方法，能知道文件的末尾为-1)
                            * 那么我们的 Socket 是两端的通信，一直等待着对方传来数据的，
                            * 并不知道啥时候会完，所以就会一直不会等于 -1，阻塞在 while 循环里了，
                            * 下面的代码就不会被执行。
                            *  */
                            bos.write( rst ); //通过服务器本地的 文件字节输出流，把读到的，写到硬盘中
                            bos.flush(); //【坑2】把内部缓冲区中的数据，强制刷新到文件中；我忘记做了，以至于输出的txt文件中没有文字内容！！
                        };
                        //向客户端回写信息
                        OutputStream os = oneSocket.getOutputStream();
                        os.write( "上传成功！".getBytes() );


                        //------------------文件上传，代码结束----------------------

                    }catch( IOException e ){
                        e.printStackTrace();
                    }finally{
                        /* 【改进4】把释放资源的代码，放进finally块中
                        * 不过，还需要多做的工作有：
                        * (1)还要为释放资源的close()的调用，再写一次异常处理try...catch...finally，
                        * 因为真的不能throws 异常了……
                        * (2)可能某个流的实例对象不能被识别了(因为它不是在此函数、方法中“全局的”变量——在这里，如实例对象bos)
                        * 得把这些流的实例对象(如实例对象bos)在此函数(如 重写的run方法)外 【提前声明】
                        *  */
                        try{ //释放资源
                            /* 实例对象bos在重写的run方法之外提前声明了，
                            * 这样 在异常处理try...catch中 就不报错了(就能识别到bos变量)
                            * 一开始我也想不到会这样写的……在改进代码时才知道会报这个错，故记录一下。
                            *  */
                            bos.close();
                            oneSocket.close(); //为宁单独整了个异常处理 try...catch，属实有排面嗷！
                        }catch( IOException e ){
                            e.printStackTrace();
                        }
                    }
                }
            } ).start();

        }
        //server.close(); //服务器就不用关闭了
    }
}
