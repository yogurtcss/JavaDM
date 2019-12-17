package pers.yo.adv1.demo03_Stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo02CopyFile {
    public static void copy (File from, File to) throws IOException {
        /* 测试程序的运行时间：调用System.currentTimeMillis()
        * 程序开头计算t1，程序结尾计算t2
        * 程序结尾打印输出 t2-t1
        *  */
        long t1 = System.currentTimeMillis(); //返回以 毫秒为单位的当前时间

        FileInputStream fis = new FileInputStream( from ); //由数据源，创建文件字节输入流
        FileOutputStream fos = new FileOutputStream( to ); //由目的地，创建文件字节输出流

        /* 一次写入一个字节 */
//        int oneByte = 0;
//        while( (oneByte=fis.read())!=-1 ){
//            fos.write( oneByte );
//        };

        /* 一次写入多个字节 */
        byte[] bsFrom = new byte[1024]; //缓冲区数组
        int len = 0; //储存一次能读取到的有效字节数
        while( (len=fis.read(bsFrom))!=-1 ){
            /* 如果用到了缓冲区数组：则在写入时，
            * 是把 (每次读取的) 缓冲区数组中的内容写入到 目的地文件中！！
            * 所以使用代码： write( 缓冲区数组, 起始下标为0, 每次写入的长度len )
            *  */
            fos.write( bsFrom, 0, len );
        };


        /* 释放资源：先关写的，然后关读的
        * 如果我写完了，那么之前肯定读取完毕了
        *  */
        fos.close();
        fis.close();

        long t2 = System.currentTimeMillis();
        System.out.println( "复制文件总耗时："+(t2-t1)+"毫秒" );
    };

    public static void main(String[] args) throws IOException {
        File from = new File( "E:\\Data\\a.txt" );
        File to = new File( "E:\\Data\\a1.txt" );
        copy( from, to );
    }
}
