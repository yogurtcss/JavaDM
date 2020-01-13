package demo02_MyDesign.mybatis.sqlsession.defaults;

import demo02_MyDesign.mybatis.cfg.Configuration;
import demo02_MyDesign.mybatis.sqlsession.SqlSession;
import demo02_MyDesign.mybatis.sqlsession.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    @Override
    public SqlSession openSession() {
        return( new DefaultSqlSession(cfg) );
    }
}