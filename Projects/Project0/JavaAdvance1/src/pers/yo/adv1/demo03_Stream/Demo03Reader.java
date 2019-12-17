package pers.yo.adv1.demo03_Stream;

/* 字符输入流Reader
java.io.Reader 抽象类 ——用于读取字符流的所有类的最顶层的父类(是一个抽象类)，定义了最基础的读取方法
这其中定义的共性的成员方法有：(3个)
1.int read()：读取单个字符，返回的int值 就是这个字节的int表示方式；当读取到文件的末尾时返回-1

2.int read( char[] cbuf )：一次读取多个字符，将字符读入【缓冲区(字符)数组】；
返回值为：读入缓冲区(字符)数组的总字节数；如果因为已到达流末尾而不再有数据可用，则返回 -1
▲ ▲【联想记忆】注：此处的int read(空参)与int read(数组) 与 文件字节输入流 InputStream中的read方法【大致相同】！！联想记忆即可

3.void close()：关闭该流，并释放与之相关的所有资源


-------------------------------------------------------------
java.io.FileReader文件字符输入流   extends   InputStreamReader   extends Reader
FileReader文件字符输入流：把硬盘文件中的数据以字符形式读取到内存中

一、构造方法：
FileReader(     String    fileName文件的路径 );
FileReader( File文件类型   file );   //需要导入包 import java.io.File类

FileReader构造方法的作用：
创建一个FileReader实例对象fr；并将此实例对象fr指向要读取的文件


----------------------------------------------------------
字符输入流的使用步骤：
1.在FileReader构造方法中读取 欲读取的数据源，创建FileReader实例对象fr
2.使用FileReader实例对象fr.read方法读取文件中的(字符)数据
3.释放资源

*  */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Demo03Reader {
    public static void main(String[] args) {
        FileReader fr = null; //实例化对象提前了！！这样在finally中就不会报错了
        try{
            /* 直接在try代码块中：实例化一个流对象，
            * 在try中关闭资源close()即可！！
            *  */

            fr = new FileReader( new File("E:\\Data\\a.txt") );
//            int oneChar = fr.read(); // int read(空参)：读取单个字符，返回值是 该字符的int表示形式
//            System.out.println( (char)oneChar ); //将 该字符的int表示形式强制转换为char型，然后输出

            System.out.println("读取单个字符 int read(空参)------------------");
//            int oneChar1 = 0; //用于while循环中：记录当前读取的字符
//            while( (oneChar1=fr.read())!=-1  ){ //oneChar1=fr.read() 当读取的内容还没有到结尾时( 结尾处将返回-1嗷)
//                System.out.println( (char)oneChar1 );
//            }

            System.out.println("一次性读取多个字符，放入字符数组中 int read( char[] cbuf )------------------");
            char[] cbuf = new char[2]; //缓冲区(字符)数组，长度为2
            // fr.read(cbuf) 一次性读取2个字符进 此缓冲区(字符)数组中
            while( fr.read(cbuf)!=-1 ){ //当还没到结尾处(-1)时
                System.out.println( new String(cbuf) );
            }
        }catch( IOException e ){
            e.printStackTrace();
        }finally{
            try{
                fr.close(); //close还得整个try...catch，属实有排面嗷！
            }catch( IOException e1 ){
                e1.printStackTrace();
            }
        }

    }
}
