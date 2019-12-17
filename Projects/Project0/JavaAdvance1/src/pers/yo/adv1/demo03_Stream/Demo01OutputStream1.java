package pers.yo.adv1.demo03_Stream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/* 一次写入多个字节：
*
* 在GBK字符编码集中，1个汉字是由2个字节组成；
* 因为GBK兼容 ISO-8859-1, 正数的单字节已被占用
* 所以汉字的第一个字节必须为负数，且第二个字节大多也为负数。
*
* 若写入的第一个字节为正数，打开写入完成的文件：记事本将查询ASCⅡ表，把字节转换为字符显示
* 若写入的第一个字节为负数，则：按2字节组成1汉字 的规则， 每2字节地 查询GBK编码集
*
* 一次写入多个字节的方法
* public void write( byte[] b )： 【写入 整个字节数组】
* 将b.length字节从指定的字节数组 写入至 此输出流
*
* public void write( byte[] b, int off, int len )：【写入 字节数组中的部分字节】
* 从指定的字符数组的 开始索引off处 开始，写入len个字节 至 此输出流
*
*
*  */

public class Demo01OutputStream1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream( new File("E:\\Data\\a.txt") );
        /* 表示1的 ASCⅡ码是 49；表示0的 ASCⅡ码是48
        * 则要在文件中写入100(读作一零零，而不是一百！！)时：
        * 写入三个字节49 48 48
        *  */
        fos.write( 49 );
        fos.write( 48 );
        fos.write( 48 );

//        byte[] bs1 = { 65,66,67,68 }; //字节全为正数
        byte[] bs1 = { -65,-66,67,68 }; //前两个字节为负数
//        fos.write( bs1 );

        /* 写入字符串(String型的，如中文字符、英文字符等)的方法【技巧嗷！】
        * 将此String字符串s1转换为字节数组：s1.getBytes()，
        * 然后再用字节输出流 write写入至文件中
        *  */
        byte[] bs2 = "你好".getBytes();//将此String字符串s1转换为字节数组
        System.out.println( Arrays.toString(bs2) );
        fos.write( bs2 );

        fos.close(); //释放资源
    }
}
