package pers.yo.adv1.demo03_Stream;

import java.io.FileOutputStream; //文件字节输出流

/* IO流：
I 输入，把硬盘中的数据，读取到内存中使用
O 输出：把内存中的数据，写入到硬盘中保存

顶级父类(4个)：
字节输入流、字节输出流
字符输入流、字符输出流


* java.io.OutputStream 字节输出流
* 此抽象类是 输出字节流的所有类的超类
* 定义了其子类中，共有的成员方法
* 流对象.close() 释放资源
* 流对象.flush() 刷新此输出流，并强制任何缓冲中的输出字节被写出去
* 流对象.write() 将字节写入至此 输出流
*
*
* java.io.FileOutputStream(文件字节输出流) extends(继承于) OutputStream
*  */


import java.io.IOException;

public class Demo01OutputStream {
    public static void main(String[] args) throws IOException {
        /* 构造方法
        * 构造方法中 传入 欲写入数据的目的地
        *
        * 1.FileOutputStream( String fileName )
        * 创建一个：向 指定文件名为fileName的文件中 写入数据 的文件字节输出流
        *
        * 2.FileOutputStream( File file )
        * 创建一个：向 指定File对象表示的文件中 写入数据 的文件字节输出流
        *
        * 写入数据的原理( 内存->硬盘 )
        * java程序 -> JVM(java虚拟机) -> OS(操作系统) -> OS调用写数据的方法 -> 把数据写入到文件中
        *
        * 字节输出流的使用步骤(重点)
        * 务必要处理异常，如throws IOException或try catch
        * 1.创建一个FileOutputStream对象fos，构造方法中 传入 欲写入数据的目的地
        * 2.调用FileOutputStream实例对象fos.write()，把数据写入到文件中
        * 3.释放资源( 流的使用 会占用一定的内存；当流使用完毕时要把内存清空，提高程序的效率 )
        *
        *  */

        //fos 即 File Output Stream 取首字母之小写嗷
        FileOutputStream fos = new FileOutputStream( "E:\\Data\\a.txt" ); //创建一个输入输出流
        /* 写数据。系统会把十进制的整数97，转为二进制整数97。即 97 -> 1100001
        * 任意的文本编辑器(如记事本、notepad++) 在打开文件时，都会查询ASCⅡ表，把字节转换为字符显示
        * 如文件中为二进制数值0-127，将查询ASCⅡ表，这里97 转为 a
        * 若为其他数值，则查询系统默认码表( 中文系统GBK )
        *  */
        fos.write( 97 ); //写数据
        fos.close(); //释放资源
    }
}
