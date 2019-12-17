package pers.yo.adv1.demo03_Stream;

/* 字符输出流 Writer
java.io.Writer 抽象类 ——用于【写出字符流】的所有类的最顶层的父类(是一个抽象类)，定义了最基础的读取方法

注：此处“写出”、“写入”的措辞不一样，但都是“输出”之意！！
1.写出 字符流
2.写入 到硬盘中的文件中

共性的成员方法：
void write(int c): 写入单个字符；注意，是传入 int型的数据——该字符的int表示形式！！(ASC II码表)
void write(char cbuf[]): 写入字符数组。
abstract void write(char cbuf[], int off, int len): 写入字符数组的一部分。

void write(String str): 写入一个字符串。
void write(String str, int off, int len): 写入一个字符串的一部分。

Writer append(CharSequence csq): 将指定的字符序列追加写到 writer 中。
Writer append(CharSequence csq, int start, int end): 将指定的字符序列的子序列追加写入此 writer。
Writer append(char c): 将指定字符追加到这个 writer。

abstract void flush(): 刷新流。
abstract void close(): 关闭流，但要先刷新它。

----------------------------------------------------
构造方法
FileWriter( String    fileName给定的文件名, [boolean append可选的追加写入] )：根据给定的文件名，创建一个FileWriter实例对象
FileWriter( File类型  file,  [boolean append可选的追加写入] )：根据给定的File实例对象，创建一个FileWriter实例对象

构造方法中的续写：第二个缺省的参数append为true时，表示续写(追加写入)



FileWriter构造方法的作用：
创建一个FileWriter实例对象fw；并将此实例对象fw指向 要新建的文件

----------------------------------------------------
使用文件字符输出流的步骤
1.创建FileWriter的实例化对象fw，传入形参为：要创建的文件名
2.调用FileWriter的实例化对象fw的write方法，把数据写入到 内存缓冲区中(字符转换为字节的过程)
3.调用FileWriter的实例化对象fw的flush方法，把内存缓冲区中的数据，刷新到文件中 【就多了这一步】
3.【在执行了flush方法后】释放资源

----------------------------------------------------
刷新flush和关闭close的区别
flush：刷新缓冲区，流对象仍可继续使用
close：先刷新缓冲区，然后通知系统释放资源，流对象不可用再被使用了


*  */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Demo04Writer {
    public static void main(String[] args) throws IOException { //不要一直声明异常而不处理，以后尽量用 try...catch...finally
        FileWriter fw = new FileWriter( new File("E:\\Data\\a.txt") );
        fw.write( 97 );
        /* 写数据。系统会把十进制的整数97，转为二进制整数97。即 97 -> 1100001
        * 任意的文本编辑器(如记事本、notepad++) 在打开文件时，都会查询ASCⅡ表，把字节转换为字符显示
        * 如文件中为二进制数值0-127，将查询ASCⅡ表，这里97 转为 a
        * 若为其他数值，则查询系统默认码表( 中文系统GBK )
        *  */
        fw.write( "俺是字符串！" ); //把数据写入到内存缓冲区中(字符转换为字节的过程)
        fw.flush();//把内存缓冲区中的数据，刷新到文件中
        fw.close();
    }
}
