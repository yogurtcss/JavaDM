package pers.yo.adv1.demo03_Stream;

/* 缓冲流(也称 高效流)
缓冲流是对4个基本的 FileXXX流 的增强
字节缓冲流：BufferedInputStream， BufferedOutputStream
字符缓冲流：BufferedReader，      BufferedWriter

缓冲流的基本原理：在创建流对象时，会创建一个内置的默认大小的缓冲区数组，
通过缓冲区读写，减少系统IO次数，从而提高读写的效率

一、构造方法
public BufferedInputStream( InputStream字节输入流对象 in )：通过给定的字节输入流对象in，创建一个新的 缓冲输入流
public BufferedOutputStream( OutputStream字节输出流对象 out )：通过给定的字节输出流对象out，创建一个新的 缓冲输出流

--------------------------------------------
字节缓冲流

java.io.BufferedOutputStream字节缓冲输出流   extends OutputStream

 继承自父类的共性成员方法:
        - public void close() ：关闭此输出流并释放与此流相关联的任何系统资源。
        - public void flush() ：刷新此输出流并强制任何缓冲的输出字节被写出。
        - public void write(byte[] b)：将 b.length字节从指定的字节数组写入此输出流。
        - public void write(byte[] b, int off, int len) ：从指定的字节数组写入 len字节，从偏移量 off开始输出到此输出流。
        - public abstract void write(int b) ：将指定的字节输出流。

构造方法:
        BufferedOutputStream(OutputStream out)  创建一个新的缓冲输出流，以将数据写入指定的底层输出流。
        BufferedOutputStream(OutputStream out, int size)  创建一个新的缓冲输出流，以将具有指定缓冲区大小的数据写入指定的底层输出流。
        参数:
           OutputStream out:字节输出流
                我们可以传递FileOutputStream,缓冲流会给FileOutputStream增加一个缓冲区,提高FileOutputStream的写入效率
           int size:指定缓冲流内部缓冲区的大小,不指定则默认大小
     使用步骤(重点)
        1.创建FileOutputStream对象,构造方法中绑定要输出的目的地
        2.创建BufferedOutputStream对象,构造方法中传递FileOutputStream对象对象,提高FileOutputStream对象效率
        3.使用BufferedOutputStream对象中的方法write,把数据写入到内部缓冲区中
        4.使用BufferedOutputStream对象中的方法flush,把内部缓冲区中的数据,刷新到文件中
        5.释放资源(会先调用flush方法刷新数据,第4步可以省略)

-----------------------------------------------------------
关于释放资源close的操作顺序：
先打开了一个基本流(如 FileInputStream fis)，
然后又通过fis打开了一个字节缓冲输入流BufferedInputStream bis，
那么，先关闭哪个流？

答：先打开的(如fis)后关闭，后打开的(如bis)先关闭；

------------------------------------------------------------
2019-11-22 09:43:17 未能理解的回答：
问：JAVA的 IO流使用了装饰模式，关闭最外面的流的时候会自动调用被包装的流的 close()方法吗？

如下例子代码：
FileInputStream is = new FileInputStream(".");
BufferedInputStream bis = new BufferedInputStream(is);
bis.close();

答：从设计模式上看，java.io.BufferedInputStream是java.io.InputStream的装饰类。
BufferedInputStream装饰一个 InputStream 使之具有缓冲功能，
文件字节输入流is若要关闭，只需要调用【最终被装饰出的对象如bis】的close()方法即可，
因为它(bis)最终会调用真正数据源对象的 close()方法。
—— 【确实，后打开的(如bis)先关闭】

BufferedInputStream的close方法中对InputStream进行了关闭，
下面是jdk中附带的源代码：
java.io.BufferedInputStream的api:
close
public void close()throws IOException 关闭此输入流并释放与该流关联的所有系统资源。

------------------------------------------------------------


*  */

import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;


public class Demo06BufferedOutputStream {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream( new File("E:\\Data\\a.txt") ); //在这里暂且 throws IOException吧，不做try...catch了
        BufferedOutputStream bos = new BufferedOutputStream( fos ); //创建文件字节缓冲输出流
        bos.write( 97 ); //单个字符
        bos.write( "您好嗷！".getBytes() );

        bos.flush(); //把内部缓冲区中的数据，强制刷新到文件中

        bos.close(); //后打开的流，先关闭
        fos.close(); //先打开的流，后关闭
    }
}
