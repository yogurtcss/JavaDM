package demo3_myxmlioc.dao.impl;

import demo3_myxmlioc.dao.AccountDao;
import demo3_myxmlioc.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
/* Dbutils 三个核心功能介绍
1.QueryRunner 中提供对 sql 语句(insert，update，delete)操作的 API;
2.ResultSetHandler 接口，用于定义 select 操作后，怎样封装结果集;
3.DbUtils 类，它就是一个工具类，定义了关闭资源与事务处理的方法；

选择查询，【方法本身不提供数据库连接】。返回值的类型由rsh参数决定
SQL语句中，用 ?问号 作为占位符；

在查询中，可变参数-对象阵列的值被用来作为查询的置换 ?的 参数。
T query(
     String sql,
     ResultSetHandle<T> rsh, -结果集处理，用来把结果集映射成你想要的结果
     [...Object params-可选的，可变参数-总是在最后一个位置！！],
);

----------rsh参数：传入 【所要封装为某个实例对象 的类】 的Class类_类型实例对象
BeanHandler<某个类T>( 某个类T.class实例对象 ) -- 把结果集转为一个 Bean
BeanListHandler<某个类T>( 某个类T.class实例对象 ) -- 返回 List，其中每行是 Bean
ArrayListHandler<某个类T>( 某个类T.class实例对象 ) -- 返回 List，其中每行是 Object[] //对象数组
MapListHandler<某个类T>( 某个类T.class实例对象 ) -- 返回 List，其中每行是 Map

执行增、删、改语句；返回的int是影响的行数
int update (String sql, Object params)：
int update (String sql, Object params [])：执行插入、更新或删除（DML）操作。

* */
@Repository( "accountDaoImpl" )
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private QueryRunner qr;

    //加了@Autowired注解后，set方法就不是必需的了
    public void setQr(QueryRunner qr) {
        this.qr = qr;
    }

    @Override
    public List<Account> findAllAccount() {
        List<Account> list = null;
        try{ //数据库 结果集的赋值，在try...catch中进行！！
            list = qr.query(
                    "select * from account",
                    new BeanListHandler<Account>(Account.class)
            );
            /* QueryRunner.query() 返回值是一个 Object 对象，
            该 Object 对象保存着从数据库获取的数据，
            该 Object 对象的类型 (可显式转换的类型) 是由调用 query方法时的 handler 参数决定的.

            *  */
        }catch( Exception e ){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Account findAccountById(Integer accountId) {
        Account a = null;
        try{
            a = qr.query(
                    "select * from account where id = ? ",
                    new BeanHandler<Account>(Account.class),
                    accountId
            );
        }catch( Exception e ){
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public void saveAccount(Account account) {
        try{
            qr.update(
                    "insert into account(name,money) values(?,?) ",
                    account.getName(), account.getMoney()
            );
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try{
            qr.update(
                    "update account set name=?, money=? where id=? ",
                    account.getName(),  account.getMoney(),  account.getId()
            );
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(Integer accountId) {
        try{
            qr.update(
                    "delete from account where id=? ",
                    accountId
            );
        }catch( Exception e ){
            e.printStackTrace();
        }
    }
}
