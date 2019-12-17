package pers.yo.adv1.demo03_Stream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo01OutputStream2 {
    /* 字节输出流的追加写入 和换行
    * 使用 两个形参 的FileOutputStream构造方法
    *  */
    public static void main(String[] args) throws IOException {
        /* 使用 两个形参 的FileOutputStream构造方法
        * 第2个形参为 true，表示append:true 表示 本次的字节输出流为 追加写入
        *  */
        FileOutputStream fos = new FileOutputStream( new File("E:\\Data\\a.txt"), true );
        fos.write( "追加嗷！".getBytes() ); //写入字符串的方法：先将字符串转换为字节数组—— 字符串.getBytes()，然后再写入嗷
        fos.write( "\n".getBytes() ); //写换行符号：也要先将此字符串"\n"转换为字节数组—— 字符串"\n".getBytes()，然后再写入嗷
        fos.close();
    }
}
