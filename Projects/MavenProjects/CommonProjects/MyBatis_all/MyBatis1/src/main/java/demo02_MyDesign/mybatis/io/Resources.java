package demo02_MyDesign.mybatis.io;

import java.io.InputStream;

//此Resources类的功能：使用类加载器，读取配置文件

/* 2020-01-15 10:06:29
Resources工具类(直接调用其中的静态方法)   —— org.apache.ibatis.io.Resources
1.从类路径加载 SQL Map 配置文件（如 sqlMap-config.xml）。
2.从类路径加载 DAO Manager 配置文件（如 dao.xml）。
3.从类路径加载各种.properties 文件。

*  */



public class Resources {
    public static InputStream getResourceAsStream( String filePath ){
        return( Resources.class.getClassLoader().getResourceAsStream(filePath) );
    }
}