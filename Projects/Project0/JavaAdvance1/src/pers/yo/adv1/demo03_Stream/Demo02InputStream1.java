package pers.yo.adv1.demo03_Stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* 字节输入流一次读取多个字节的方法：
* int read( byte[] b ) 从输入流中读取若干个字节并存储在缓冲区数组b中，
* 返回值为：读入缓冲区数组的总字节数；如果因为已到达流末尾而不再有数据可用，则返回 -1。
* 注意，此时欲打印输出“读入的数据”时，打印的不是 int read(字节数组)的返回值，而应打印 缓冲区数组中的元素！！
*
*
*
* 形参byte[] b的注释
* 1.缓冲作用，存储读取到的字节
* 2.byte[]数组的长度定义为 1024(1kb)或1024的整数倍
* 3.若缓冲数组长度为len，则一次可读取len个字节
*
*  */

public class Demo02InputStream1 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream( new File("E:\\Data\\a.txt") );

        byte[] bs1 = new byte[2]; //缓冲数组，以存储 从输入流中读取到的 多个字节
        //在这里，缓冲数组长度为2，即：一次可读取2个字节

//        int len = fis.read( bs1 ); //记录每次读取的有效字节个数
//        System.out.println( len );

        /* Arrays.toString( 某数组arr )
        * 返回指定数组arr(全部元素)内容的字符串
        * 接着可通过 println 打印它
        *  */
//        System.out.println( Arrays.toString(bs1) );

        /* String的构造方法
        * String( byte[] bytes ) 将字节数组转换为字符串
        * String( byte[] bytes, int offset, int length )
        * 把字节数组的一部分转换为字符串，offset为字符数组的开始索引
        * length转换的个数
        * */
//        System.out.println( new String(bs1) ); //将字节数组转换为字符串
//
//        len = fis.read( bs1 );
//        System.out.println( len );
//        System.out.println( new String(bs1) );

        int len = 0; //记录每次read读取的有效字节数
        while( (len=fis.read(bs1))!=-1 ){ // len=fis.read(bs1)为每次读取的有效字节数，还没到-1，则表示还没到结尾
            // 注意，此时欲打印输出“读入的数据”时，打印的不是 int read(字节数组)的返回值，而应打印 缓冲区数组中的元素！！
            System.out.println( new String( bs1, 0, len ) ); //每次输出 缓冲区数组中 len个元素
        }

        fis.close();//释放资源
    }
}
