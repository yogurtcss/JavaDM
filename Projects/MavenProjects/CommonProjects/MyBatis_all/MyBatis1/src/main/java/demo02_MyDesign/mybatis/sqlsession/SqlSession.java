package demo02_MyDesign.mybatis.sqlsession;

public interface SqlSession {

    /**
     * 根据参数创建一个代理对象
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    /* 2020-01-13 20:10:12
    * 返回值类型是 泛型T，为什么要写成 <T> T ？
    * 解释：泛型要先声明后使用，声明在返回值之前
    * <T>是“先声明”，声明在返回值之前；
    * 返回值 T 就是“使用”了
    * 所以 <T>声明 就在 返回值T 之前了
    *  */
    public abstract <T> T getMapper( Class<T> daoInterfaceClass );

    public abstract void close(); //释放资源
}
