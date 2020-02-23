package pers.yo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadPropertiesUtil {

    public static Properties props; //全局变量

    static{ // properties文件中都是一些静态的常量,所以把这块读取的代码写在静态代码块中 不是写在普通的方法
        props = new Properties(); //定义一个properties集合，用来接收数据
        InputStream is = LoadPropertiesUtil.class.getClassLoader().getResourceAsStream( "youku.properties" );
        try{
            props.load(is);
        }catch( IOException e ){
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //读取配置文件，通过键名KeyName，从youku.properties中读对应的属性值
    public static String getPropsFromYouKuByKeyName( String keyName ){
        //System.out.println( "属性值："+props.getProperty(keyName) ); //我佛了，原来正则表达式中的中文变成乱码了！
        return props.getProperty(keyName); //直接return就完事了嗷
    }


    public static void main(String[] args) { //测试一哈
        System.out.println( LoadPropertiesUtil.getPropsFromYouKuByKeyName("aliasRegEx") );
    }

}
