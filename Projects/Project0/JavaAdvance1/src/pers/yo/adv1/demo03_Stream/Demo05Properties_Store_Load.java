package pers.yo.adv1.demo03_Stream;

//使用 与流相关的方法：store、load

import java.io.File;
import java.io.FileWriter;
import java.util.Properties;
import java.io.IOException;

import java.io.FileReader;
import java.util.Set;
import java.util.Iterator;


public class Demo05Properties_Store_Load {
    public static void main(String[] args) {
        //1.新建Properties集合实例对象prop，并准备数据
        Properties prop = new Properties();
        prop.setProperty( "A01","北洛" );
        prop.setProperty( "A02","小缨子" );
        prop.setProperty( "A03","柿饼" );
        try{
            //2.新建“目的地”的 输出流(如 文件字节输出流 或文件输出流)，传入 要写入的文件名
            FileWriter fw = new FileWriter( new File("E:\\Data\\a.txt") );
            //3.调用Properties集合实例对象prop.store，把此输出流中的数据，写入至此文件中
            prop.store( fw, "" );
            //4.释放资源
            fw.close();
        }catch( IOException e ){
            e.printStackTrace();
        }

        Properties propRead = new Properties();
        try{
            propRead.load( new FileReader( new File("E:\\Data\\a.txt") ) );
            /* 注意，这其中new出来的FileReader(...)是一个匿名对象
            * 匿名对象使用完了就消失，即此匿名对象的流使用完了，就自动关闭了
            * 不需手动 close()
            *
            * 但是！！不推荐这样做！！
            * 匿名对象会在 GC 的时候回收，回收之前会调用 finalize 方法，
            * FileInputStream 的源码里在 finalize 方法会调用 close 方法，
            * 所以在 GC 时候流就会被关闭，但是不推荐这样做，这样资源没有及时释放，还是手动 close 好。
            *
            *  */
        }catch( IOException e ){
            e.printStackTrace();
        }

        Set<String> keys = propRead.stringPropertyNames(); //获取所有的键，暂存在一个集合keys中
        Iterator<String> it = keys.iterator();
        while( it.hasNext() ){
            String oneKey = it.next();
            System.out.println( oneKey+"对应的值为："+propRead.getProperty(oneKey) );
        }

    }
}
