package pers.yo.adv1.demo02_Recursion;

import java.io.File;

public class Demo02FileDiGui {
    public static void getAllFile( File dir ){
        System.out.println( dir ); //打印被遍历的目录名称

        File[] files = dir.listFiles(); //返回File类型的数组
        /* 增强型for循环：快速遍历数组的元素，但丢失了下标信息
         *
         *  for( type类型  OneElement原数组中每一个待遍历的元素: array正在被遍历的数组 ){
         * 　  System.out.println( OneElement );
         *  }
         *
         * 当遍历集合或数组时，如果需要访问集合或数组的下标，那么最好使用旧式的方式来实现循环或遍历，
         * 而不要使用增强的 for 循环，因为它丢失了下标信息。
         *
         * 在IDEA中，对数组使用增强型for循环的快捷键：
         * 敲下files.for 然后按回车
         *  */
        for( File f: files ){
            //判断：正在被遍历的对象是否为文件夹
            if( f.isDirectory() ){ //若是文件夹，则递归，继续遍历此文件夹
                getAllFile( f );
            }
            else{
                System.out.println( f );
            }
        }
    }


    public static void main(String[] args) {
        File f = new File( "E:\\Data" );
        getAllFile( f );
    }
}
