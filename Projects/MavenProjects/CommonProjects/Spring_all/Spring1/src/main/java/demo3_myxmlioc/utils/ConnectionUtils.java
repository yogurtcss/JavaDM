package demo3_myxmlioc.utils;

/* 2020-01-21 09:19:28
ThreadLocal 是一个关于创建线程局部变量的类。
//从字面上来理解 ThreadLocal，感觉就是相当于线程本地的

通常情况下，我们创建的变量是可以被任何一个线程访问并修改的。
而使用 ThreadLocal 创建的变量只能被当前线程访问，其他线程则无法访问和修改。

ThreadLocal 提供了线程的局部变量，
每个线程都可以通过 set() 和 get() 来对这个局部变量进行操作，
    //把Connection放进(set进去) ThreadLocal里面
    //这就是 “把变量交给ThreadLocal 来管理”
但不会和其他线程的局部变量进行冲突，实现了线程的数据隔离。

往 ThreadLocal 中填充的变量属于当前线程，该变量对其他线程而言是隔离的

数据库连接池的连接怎么管理呢？？
我们交由 ThreadLocal 来进行管理。

为什么交给ThreadLocal来管理呢？？
ThreadLocal 能够实现当前线程的操作都是用同一个 Connection，保证了事务！


ThreadLocal 原理总结
1.每个 Thread 维护着一个 ThreadLocalMap 的引用
2.ThreadLocalMap 是 ThreadLocal 的内部类，用 Entry 来进行存储
3.调用 ThreadLocal 的 set () 方法时，实际上就是往 ThreadLocalMap 设置值，key 是 ThreadLocal 对象，值是传递进来的对象
4.调用 ThreadLocal 的 get () 方法时，实际上就是往 ThreadLocalMap 获取值，key 是 ThreadLocal 对象
ThreadLocal 本身并不存储值，它只是作为一个 key 来让线程从 ThreadLocalMap 获取 value。

* */

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {
    /* 注意：ThreadLocal只能 “隔离/守护” 一个局部变量 或一个对象
    * 在创建ThreadLocal实例对象时，传入你要 隔离/守护 的那个局部变量 泛型嗷
    * ThreadLocal<要守护的那个变量> tl = new ThreadLoacal<>();
    *  */
    private ThreadLocal<Connection> tl = new ThreadLocal<>();
    private DataSource ds;

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    //获取当前线程上的连接对象
    public Connection getThreadConnection(){
        Connection conn = null;

        try{
            conn = tl.get();
            if( conn==null ){
                conn = ds.getConnection();
                /* 把Connection放进(set进去) ThreadLocal里面
                * 这就是 “把变量交给ThreadLocal 来管理”
                *  */
                tl.set( conn );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //把连接和线程接绑
    public void removeConnection(){
        tl.remove();
    }


}
