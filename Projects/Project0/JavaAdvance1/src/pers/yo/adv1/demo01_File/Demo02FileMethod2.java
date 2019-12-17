package pers.yo.adv1.demo01_File;

import java.io.File;
import java.io.IOException;

public class Demo02FileMethod2 {
    public static void main(String[] args) throws IOException { //使用f1.createNewFile()时，必须加上throws IOException
        /* File实例对象f1.createNewFile()，【创建不存在的文件】，返回值boolean，需处理IOException异常嗷
        *
        * 此File表示的文件若不存在则创建并返回true，
        * 若存在则不创建并返回false；
        *
        * 使用f1.createNewFile()时，若抛出IOException，则必须处理异常:
        * 加上throws IOException或者 try catch
        *
        * File实例对象f1.delete()，删除File表示的文件，返回值boolean
        * 删除成功时，返回true；否则返回false
        * f1.delete()删除时，不会走回收站嗷，需谨慎
        *
        * File实例对象f1.mkdir()，只能在已经存在的目录中，创建由此File表示的 单级空文件夹，返回值boolean
        *
        * File实例对象f1.mkdirs()，【创建不存在的目录】，返回值boolean
        * 可以在不存在的目录中，创建由此File表示的 单级或多级空文件夹，返回值boolean
        *  */
        File f1 = new File( "E:\\Data\\f1.txt" );
        boolean b1 = f1.createNewFile();
        System.out.println( "b1："+b1 );

        File f2 = new File( "E:\\Data\\f2.txt" );
        System.out.println( "b2："+f2.createNewFile() );
        System.out.println( "f1删除成功了🐎？"+f1.delete() );
    }

}
