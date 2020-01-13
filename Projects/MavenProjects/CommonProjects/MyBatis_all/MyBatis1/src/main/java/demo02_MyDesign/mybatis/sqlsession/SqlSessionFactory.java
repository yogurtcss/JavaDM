package demo02_MyDesign.mybatis.sqlsession;

public interface SqlSessionFactory {

    //用于打开一个新的SqlSession实例对象
    public abstract SqlSession openSession();
}
