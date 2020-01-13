package demo02_MyDesign.mybatis.sqlsession;

public interface SqlSession {

    /**
     * 根据参数创建一个代理对象
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    public abstract <T> T getMapper( Class<T> daoInterfaceClass );

    public abstract void close(); //释放资源
}
