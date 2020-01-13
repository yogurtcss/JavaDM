package demo02_MyDesign.mybatis.sqlsession.defaults;

import demo02_MyDesign.mybatis.cfg.Configuration;
import demo02_MyDesign.mybatis.sqlsession.SqlSession;
import demo02_MyDesign.mybatis.sqlsession.proxy.MapperProxy;
import demo02_MyDesign.mybatis.utils.DataSourceUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class DefaultSqlSession implements SqlSession {
    private Configuration cfg;
    private Connection connection;

    public DefaultSqlSession(Configuration cfg){
        this.cfg = cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }


    /**
     * 用于创建代理对象
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) { //传入形参的就是接口了……
        T rst = (T) Proxy.newProxyInstance(
                //真实对象的类加载器
                daoInterfaceClass.getClassLoader(),
                //被代理对象实现的接口 数组
                new Class[]{ daoInterfaceClass },

                //处理器
                new MapperProxy( cfg.getMappers(), connection )
        );

        return rst;
    }

    @Override
    public void close() {
        if(connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
