package demo02_MyDesign.mybatis.io;

import java.io.InputStream;

//此Resources类的功能：使用类加载器，读取配置文件
public class Resources {
    public static InputStream getResourceAsStream( String filePath ){
        return( Resources.class.getClassLoader().getResourceAsStream(filePath) );
    }
}