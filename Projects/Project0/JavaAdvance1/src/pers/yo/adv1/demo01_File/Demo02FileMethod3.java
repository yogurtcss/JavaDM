package pers.yo.adv1.demo01_File;

import java.io.File;

public class Demo02FileMethod3 {
    public static void main(String[] args) {
        /* File类遍历文件夹(目录)的方法
        * 若遍历时，构造方法中给出的目录、路径不存在，则会抛出 空指针异常
        *
        * public String[] list() 返回值为String数组，数组元素为String型，且为该File目录下所有子文件或子文件夹(子目录)
        * File实例对象f1.list()
        *
        * public File[] listFiles() 返回值为File类型数组，数组元素为：File类型的、且为该File目录下所有子文件或子文件夹(子目录)
        * File实例对象f1.listFiles()
        * 具体地说：遍历构造方法中给出的目录，系统自动将所得文件/文件夹封装为File对象，这些File对象存到数组中
        *
        *  */
       File f1 = new File( "E:\\Data" );
       String[] arrF1_String = f1.list();

       assert arrF1_String != null; //断言？！断言，换句话就是 立 flag，false 则啪啪啪打脸。
       for(int i = 0, length = arrF1_String.length; i<length; i++ ){
           System.out.println( arrF1_String[i] );
       }

        System.out.println( "---------------" );
       File[] arrF1_File = f1.listFiles();
       for(int i = 0, length = arrF1_File.length; i<length; i++ ){
            System.out.println( arrF1_File[i] );
       }


    }
}
