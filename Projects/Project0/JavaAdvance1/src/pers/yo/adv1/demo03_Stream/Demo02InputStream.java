package pers.yo.adv1.demo03_Stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* 字节输入流 java.io.InputStream;
 * 此抽象类是表示字节输入流的所有类的超类
 *
 * java.io.FileInputStream(文件字节输入流) extends InputStream
 *
 * FileInputStream(文件字节输入流) 把硬盘文件中的数据，读取到内存中等待使用
 * 构造方法：
 * 创建一个：向 指定文件名为fileName的文件中 读取数据 的文件字节输入流
 * 1.只传入一个形参——表示数据来源：String字符型 或 File类型
 *   new FileInputStream( String name )
 *   new FileInputStream( File file )
 *
 *
 *
 *
 * 字节输入流的使用步骤【重点】
 * 1.创建FileInputStream的实例对象fis，构造方法中传入 数据源
 * 2.调用实例对象fis.read方法(以读取文件)，读出来显示到控制台中的是 int整型，此字符所表示的ASC II码
 * 3.释放资源
 *
 *  */

public class Demo02InputStream {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream( new File("E:\\Data\\a.txt") );
        /* int read() 若没有传入形参时，
        * 默认情况下，对这个流一个一个字节地读，
        * 返回的int值 就是这个字节的int表示方式；当读取到文件的末尾时返回-1
        * 所以，此时欲打印输出“读入的数据”时。打印的就是 int read(空参) 的返回值！！
        * 【注意这与下一个方法的返回值 的区别 嗷！】
        *
        *
        * int read( 字节数组bytes[1024] ) 传入一个字节数组bytes(称为缓冲区数组)  ——一次读取多个字节的方法
        * 根据字节数组的长度len(在这里长度为1024)，一次读取len个字节存到缓冲区数组中
        * 返回值为：读入缓冲区数组的总字节数；如果因为已到达流末尾而不再有数据可用，则返回 -1。
        * 注意，此时欲打印输出“读入的数据”时，打印的不是 int read(字节数组)的返回值，而应打印 缓冲区数组中的元素！！
        *
        *  */
        int oneByte = fis.read(); //返回的int值 就是这个字节的int表示方式；当读取到文件的末尾时返回-1
        System.out.println( oneByte ); //所以，此时欲打印输出“读入的数据”时。打印的就是 int read(空参) 的返回值！！

        //以下多次调用fis.read()
//        oneChar = fis.read();
//        System.out.println( oneChar );
//        oneChar = fis.read();
//        System.out.println( oneChar );
//        oneChar = fis.read();
//        System.out.println( oneChar );

        /* 可通过循环读取read文件
        * 但我不知道文件中有多少个字节呀！所以用while循环
        *  */
        int len = 0; //记录读取到的字节
        while( (len=fis.read())!=-1 ){
            //System.out.println( len );
            System.out.println( (char)len ); //将读取到的字节int型 强制转换为char类型，然后输出
        }

        fis.close();


    }
}
