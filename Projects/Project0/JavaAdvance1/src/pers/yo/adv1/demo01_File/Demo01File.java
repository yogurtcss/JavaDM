package pers.yo.adv1.demo01_File;

import java.io.File; //引入File类

/* java.io.File类
* 文件和目录的抽象表示形式
* java.io.File类是一个与系统无关的类，
* 任何操作系统都可以使用此类中的方法
*
*
* java把电脑中的文件和文件夹(目录)封装为了一个File类，
* 我们可以使用此File类对文件和文件夹进行操作
*
* File类中的方法：
*   创建一个文件/文件夹
*   删除文件/文件夹
*   获取文件/文件夹
*   判断文件/文件夹是否存在
*   对文件夹进行遍历
*   获取文件的大小
*
* 重点，记住这个三个单词：
* file文件，  directory文件夹/目录，  path路径
*  */


public class Demo01File {
    private static void show01(){
        /* File的构造方法1
        * new File( 路径名字的字符串 )，创建一个File类的实例对象
        * 路径名可以是 文件夹 或单个文件 之名 结尾
        * 路径名可以是 相对路径 或相对路径
        * 路径可以是 存在 或不存在的 (均可)
        *
        * 创建File实例对象：只是把传入的路径名之字符串封装为File对象，不考虑路径的真假情况
        *  */

        /* 在把 E:\Data 复制进构造方法时，编译器自动把单个反斜杠\ 转换为 双 反斜杠\\
        * 路径名一定要用 双反斜杠！！转义！！
        *  */
        File f1 = new File( "E:\\Data\\a.txt" ); //a.txt文件原本不存在
        System.out.println( f1 );//输出的是 路径名
        /* 原本f1是实例对象，打印f1对象却输出了路径名；
        * File类重写了Object类的toString方法
        *
        * Object.toString()方法返回一个表示该对象的字符串
        *  */
        File f2 = new File( "E:\\Data" ); //Data文件夹
        System.out.println( f2 );
    }


    private static void show02( String parentPath, String childPath ){ //传入父路径、子路径
        /* File的构造方法2
        * new File( 父路径名 字符串, 子路径名 字符串 )
        * 父路径和子路径是可变的，可以单独、灵活书写
        * 注意，若父路径是盘符，则需加上 双反斜杠，写法如下
        * new File( "E:\\", "Data\\a.txt" )
        *  */
        File f1 = new File( parentPath, childPath );
        System.out.println( f1 );
    }
    private static void show03(){
        /* File的构造方法3
        * new File( 父路径 File类型, 子路径名 字符串 )
        * 父路径是File类型：则可以先调用File类型的方法，再传进来 创建对象
        *  */
        File parentPath = new File( "E:\\" );
        File f1 = new File( parentPath, "c.txt" );
        System.out.println( f1 );
    }



    public static void main(String[] args) {
        /* 学习一个类的用法，该怎么学？
        * 1.类的静态属性：可通过类名用点号.直接调用 这些静态属性
        * 2.类的构造方法：通过它提供的构造方法，可以创建出不同的对象
        * 3.此类的实例对象的方法：创建实例对象后，看看它能调用啥方法
        *  */
        String pathSp = File.pathSeparator; //与系统有关的路径分隔符。windows下为; linux下为:
        System.out.println( pathSp );
        String sp = File.separator; //文件名分隔符
        /* windows下为反斜杠\，但是在书写代码时，需用两个反斜杠\\转义为一个反斜杠\
        * linux下为正斜杠/
        *  */
        System.out.println( sp );
        System.out.println("--------------");
        /* 文件路径，如 ‪E:\\Data\\a.txt，拼串如下：
        * "E:"+ File.separator + "Data" + File.separator + "a.txt"
        *  */

        show01();
        System.out.println("--------------");
        show02( "E:\\", "Data\\b.txt" );
        show03();

    }
}
