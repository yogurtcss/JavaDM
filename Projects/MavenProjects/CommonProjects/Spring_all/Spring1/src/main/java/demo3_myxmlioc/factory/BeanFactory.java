package demo3_myxmlioc.factory;

import demo3_myxmlioc.service.AccountService;
import demo3_myxmlioc.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BeanFactory {
    private AccountService service;
    private TransactionManager tsManager;

    //为什么setService这个方法要加 final？
    public final void setService(AccountService service) {
        this.service = service;
    }

    public void setTsManager(TransactionManager tsManager) {
        this.tsManager = tsManager;
    }

    //获取 service代理对象
    public AccountService getAccountService_proxy(){

        return( (AccountService) Proxy.newProxyInstance(
                service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if( "test".equals(method.getName()) ){
                            return( method.invoke(service,args) );
                        }

                        Object rtValue = null;
                        try{
                            tsManager.beginTransaction(); //开启事务
                            rtValue = method.invoke( service, args ); //操作数据库
                            tsManager.commit(); //提交事务
                        }catch( Exception e ){
                            tsManager.rollback(); //回滚事务
                            e.printStackTrace();
                        }finally{
                            tsManager.release(); //释放资源
                        }

                        return rtValue;
                    }
                }
        ) );
    }


}
