package demo3_myxmlioc.utils;

public class TransactionManager {
    private ConnectionUtils connUtils;

    public void setConnUtils(ConnectionUtils connUtils) {
        this.connUtils = connUtils;
    }

    //开启事务
    public void beginTransaction(){
        try{
            //不准你自动提交事务，我来手动提交事务嗷！
            connUtils.getThreadConnection().setAutoCommit(false);
        }catch( Exception e ){
            e.printStackTrace();
        }
    }
    //提交事务
    public void commit(){
        try{
            connUtils.getThreadConnection().commit();
        }catch( Exception e ){
            e.printStackTrace();
        }
    }
    //回滚事务
    public void rollback(){
        try{
            connUtils.getThreadConnection().rollback();
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    //释放连接
    public void release(){
        try{
            connUtils.getThreadConnection().close(); //把连接归还到连接池中
            connUtils.removeConnection();
        }catch( Exception e ){
            e.printStackTrace();
        }
    }
}
