package pers.yo.adv1.demo03_Stream;

/* 转换流

----------------------------------------------------------------------------------------

java.io.OutputStreamWriter extends Writer

OutputStreamWriter: 从字符流到字节流的桥接：使用指定的字符集将【写入其中的字符】编码为字节

1) OutputStreamWriter 是从字符流到字节流的桥接怎么理解？
  答：字符的输出需要通过字符流来操作，但是【本质最后还是通过字节流输出】到计算机上进行存储的；

     因此 OutputStreamWriter 流的作用：就是：
     利用字节流作为底层输出流然后构建字符输出流，字符输出流输出字符到流中;
     然后通过【指定的字符集】把流中的字符 编码成字节 输出到 字节流 中，
     其作用就是一个桥梁，使得双方链接起来


----------------------------------------------------------------------------------------
java.io.OutputStreamWriter extends Writer
    继续自父类的共性成员方法:
        - void write(int c) 写入单个字符。
        - void write(char[] cbuf)写入字符数组。
        - abstract  void write(char[] cbuf, int off, int len)写入字符数组的某一部分,off数组的开始索引,len写的字符个数。
        - void write(String str)写入字符串。
        - void write(String str, int off, int len) 写入字符串的某一部分,off字符串的开始索引,len写的字符个数。
        - void flush()刷新该流的缓冲。
        - void close() 关闭此流，但要先刷新它。
    构造方法:
        OutputStreamWriter(OutputStream out)创建使用默认字符编码的 OutputStreamWriter。
        OutputStreamWriter(OutputStream out, String charsetName) 创建使用指定字符集的 OutputStreamWriter。
        参数:
            OutputStream out:字节输出流,可以用来写转换之后的字节到文件中
            String charsetName:指定的编码表名称,不区分大小写,可以是utf-8/UTF-8,gbk/GBK,...不指定默认使用UTF-8
    使用步骤:
        1.创建OutputStreamWriter对象,构造方法中传递字节输出流和指定的编码表名称
        2.使用OutputStreamWriter对象中的方法write,把字符转换为字节存储缓冲区中(编码)
        3.使用OutputStreamWriter对象中的方法flush,把内存缓冲区中的字节刷新到文件中(使用字节流写字节的过程)
        4.释放资源

----------------------------------------------------------------------------------------

InputStreamReader、OutputStreamWriter 实现 字符流、字节流之间的转换，使得流的处理效率得到提升，
但是如果我们想要达到最大的效率，我们应该考虑使用缓冲字符流包装转换流的思路来解决问题。


*  */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;


public class Demo08OutputStreamWriter {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream( new File("E:\\Data\\a.txt") );
        OutputStreamWriter osw = new OutputStreamWriter( fos, StandardCharsets.UTF_8);
        osw.write( "您好嗷！俺是转换流！" );
        osw.flush(); //把缓冲区数据强制刷新到文件中
        osw.close();
    }
}
