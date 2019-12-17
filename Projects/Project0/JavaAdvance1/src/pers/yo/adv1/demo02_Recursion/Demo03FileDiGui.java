package pers.yo.adv1.demo02_Recursion;

import java.io.File;

public class Demo03FileDiGui {

    public static void getAllFile2( File dir ){ //只需要特定后缀名的文件

        File[] files = dir.listFiles(); //返回File类型的数组
        for( File f: files ){
            //判断：正在被遍历的对象是否为文件夹
            if( f.isDirectory() ){ //若是文件夹，则递归，继续遍历此文件夹
                getAllFile2( f );
            }
            else{
                /* 只需要.java结尾的文件
                * 1.获取某个File类型的路径字符串中的文件名
                * 注意，这是字符串型
                *   -f.getName() 或 f.getPath() 或 f.toString()
                *  */


                /* File实例对象f1.getName()，返回由此File表示的文件或目录的名称
                * 人话：获取 实例对象f1 的构造方法中 传入的路径字符串中的 文件/文件夹 名字
                *
                * suffix 后缀
                *  */
                String name = f.getName(); //获得后缀
                if( name.endsWith(".java") ){ //字符串的endsWith方法，检查指定的后缀是否存在
                    //注意，endsWith("指定后缀") 是有一个第三人称单数s！！
                    System.out.println( f );
                }

            }
        }
    }

    public static void main(String[] args) {
        File f = new File( "E:\\Data" );
        getAllFile2( f );
    }

}
