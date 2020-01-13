package demo02_MyDesign.mybatis.sqlsession;

import demo02_MyDesign.mybatis.cfg.Configuration;
import demo02_MyDesign.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    /**
     * 根据参数的字节输入流来构建一个SqlSessionFactory工厂
     * @param config
     * @return
     */
    public SqlSession build( InputStream config ){
        Configuration cofig = XMLConfigBuilder.loadConfiguration( config );
        //return( new DefaultSqlSessionFactory(cfg) )
        return null;
    }
}
