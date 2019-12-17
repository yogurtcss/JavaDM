package pers.yo.adv1.demo19_ThreadPool;

/* 线程池：容纳多个线程的容器。
好处：1.降低资源消耗。减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务
2.提高响应速度。当任务到达到时，任务可以不需要等到线程创建就可以立即执行。
3.提高线程的可管理性。

--------------------------------------------
java中，线程池的顶级接口是java.util.concurrent.Executor；
真正的线程池接口是java.util.concurrent.ExecutorService

配置一个线程池的操作较复杂；官方建议使用Executor工程类来创建线程池对象

Executors工具类中【注意，这是复数形式！！】：
静态方法 public static ExecutorService newFixedThreadPool( int nThreads即指定线程池中可容纳的最大数量 )：创建(并返回)线程池对象

Future接口：用来记录线程任务执行完毕后 产生点击的结果
public Future<泛型通配符?>  submit( Runnable task任务 )：向线程池中，提交一个 Runable接口 的对象(提交任务，等待线程池中的线程执行)

------------------------------------------------
使用线程池中线程对象的步骤：
1.创建线程池对象(上转型写法 左ExecutorService接口，右Executors工具类的实例对象)
2.创建 Runnable接口的子类的 实例对象，设置线程任务
3.提交 Runnable接口的子类的 实例对象
4.关闭线程池 //不建议执行


*  */

import java.util.concurrent.Executors; //工具类
import java.util.concurrent.ExecutorService; //接口

public class Demo01ThreadPool {
    public static void main(String[] args) {
        //创建线程池对象
        ExecutorService es = Executors.newFixedThreadPool( 2 ); //上转型写法

        Runnable target1 = new Runnable(){ //匿名内部类写法
            public void run(){
                System.out.println( Thread.currentThread().getName()+" 创建了一个线程，等待执行" );
            }
        };
        //线程池会一直开启。当线程被使用完毕时，线程将自动(被)归还回线程池——线程还可以继续使用
        es.submit( target1 );
        es.submit( target1 );
        //销毁线程池
        es.shutdown();


    }
}
