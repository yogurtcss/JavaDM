package pers.yo.adv1.demo03_Stream;

/* 序列化流
1.序列化(也称 【写对象】或【对象的序列化】)：对象转换成字节
用【字节序列】表示一个对象；该字节序列包含了该对象的类型、数据等信息；
当将此字节序列【以文件字节输出流的形式】写入至文件中后，此文件就持久保存了一个对象的信息。

2.反序列化(也称 【读对象】或【对象的反序列化】)：字节重构为对象
对象的类型、数据等信息，可以在内存中创建对象

-----------------------------------------------------------

 java.io.ObjectOutputStream extends OutputStream
    ObjectOutputStream:对象的序列化流
    作用:把对象以【以文件字节输出流的形式】写入到文件中保存

    构造方法:
        ObjectOutputStream(OutputStream out) 创建写入指定 OutputStream 的 ObjectOutputStream。
        参数:
            OutputStream out:字节输出流
    特有的成员方法:
        void writeObject(Object obj) 将指定的对象写入 ObjectOutputStream。

    使用步骤:
        1.创建ObjectOutputStream对象,构造方法中传递字节输出流
        2.使用ObjectOutputStream对象中的方法writeObject,把对象写入到文件中
        3.释放资源

-------------------------------------------------------------------------
问题：
Java 在使用 ObjectOutputStream 将对象写入 txt 文件，打开文件是乱码
解决：
并非是乱码。ObjectOutputStream.writeObject () 的作用是把一个实例的对象以文件的形式保存到磁盘上，这个过程叫对象的持久化。
而这个文件是以二进制的形式编写的，当用文本编辑器打开，这些二进制代码映射到某个字符集后，显示出来的东西就成了乱码。
即使是输出一个 String 对象，也是以该对象的二进制编码的形式输出，而不是输出 String 对象的内容。

*  */

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import pers.yo.adv1.demo03_Stream.Person;

public class Demo09ObjectOutputStream {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream( new File("E:\\Data\\Person.txt") );
        ObjectOutputStream oos = new ObjectOutputStream( fos );
        oos.writeObject( new Person("我是对象的序列化流！",12) );
        oos.close();
    }
}
