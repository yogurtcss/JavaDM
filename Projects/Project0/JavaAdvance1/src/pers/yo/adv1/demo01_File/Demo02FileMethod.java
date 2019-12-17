package pers.yo.adv1.demo01_File;

import java.io.File;

public class Demo02FileMethod {
    public static void main(String[] args) {
        File f1 = new File("E:\\Data\\a.txt" ); //绝对路径
        File f2 = new File( "b.txt" ); //相对路径

        /* File实例对象f1.getAbsoluteFile()，返回此File实例对象的绝对路径
        * 无论f1中的路径是绝对的还是相对的，调用f1.getAbsoluteFile()，返回的都是 绝对路径
        *  */
        System.out.println( f1.getAbsoluteFile() );
        System.out.println( f2.getAbsoluteFile() );

        /* File实例对象f1.getPath()，将此File类型转换为其原本的路径名字符串
        * 人话：获取 实例对象f1 的构造方法中 传入的路径字符串
        *
        * File类重写了Object类的toString方法，
        * 调用File.toString方法实际上就是调用了getPath方法
        *
        * Object.toString()方法返回一个表示该对象的字符串
        *  */
        System.out.println( f1.getPath() ); //获取 实例对象f1 的构造方法中 传入的路径字符串

        /* File实例对象f1.getName()，返回由此File表示的文件或目录的名称
        * 人话：获取 实例对象f1 的构造方法中 传入的路径字符串中的 文件/文件夹 名字
        *  */
        System.out.println( f1.getName() ); //获取 实例对象f1 的构造方法中 传入的路径字符串中的 文件/文件夹 名字

        /* File实例对象f1.length()，注意这个length有小括号，是方法啊！
        * 返回由此File表示的文件 的长度，单位是字节；
        * 人话：获取 实例对象f1 的构造方法中 指定的文件的大小，单位是字节
        *
        * 如果构造方法中的“指定文件”不存在，则调用f1.length()，返回0
        *
        * 注意，是获取指定文件的大小！！不是文件夹！！
        * 若仍用length()方法，企图获取某文件夹的大小，则返回0
        *  */
        System.out.println( f1.length() ); //获取 实例对象f1 的构造方法中 指定的文件的大小，单位是字节
        //因为f1的构造方法中的“指定文件” a.txt不存在，所以返回0

        /* File实例对象f1.exists()，此File表示的文件或目录是否实际存在
        * File实例对象f1.isDirectory()，此File表示的是否为目录
        * File实例对象f1.isFile()，此File表示的是否为文件
        *  */
        System.out.println( f1.exists() );
    }
}
