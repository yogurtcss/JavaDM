package pers.yo.adv1.demo17_ThreadSync;

/*
------------------------------------------------------------
1.同步代码块：synchronize   /ˈsɪŋ.krə.naɪz/
synchronized关键字可用于方法中的某个区块中，表示只对这个区块的资源进行 【互斥访问】，格式为：

synchronized(同步锁syncObject){ // 必须获得了syncObjec锁的线程 才能执行synchronized块的代码
    //需要同步操作的代码
}

同步锁(也称 对象锁 或 对象监视器)：
对象的同步锁只是一个概念，可以想象为 在对象上标记了一个锁
1.锁对象 可以是任意的对象；//可以是某个类的实例，也可以是某个类
但必须在 Runnable接口实现类中 且 在重写的run()方法外 创建锁对象Object
这样确保此锁对象是 【全局】的，使得 多个线程对象使用的是同一把锁；格式如下：
Object obj = new Object(); //这么简单！！

▲ 若在重写的run()方法中创建锁对象Object，则每个不同的线程都会创建不同的锁对象！！
如此，这堆线程对象就不是使用同一把锁了！！

2.多个线程对象 必须使用同一把锁
注：在任意时刻，最多允许一个线程拥有同步锁：
谁(哪个线程)拿到同步锁，就进入谁(哪个线程)的代码块；而其他没拿到同步锁的线程只能在外等着(BLOCKED)

▲ 同步中的线程：若未执行完毕则不会释放、归还锁(把锁归还给同步代码块；其余线程继续进同步代码块前线 抢这锁)；
同步外的线程没有锁 进不去同步


------------------------------------------------------------
3.Lock锁(也称) 🔒
java.util.concurrent.locks.Lock接口
加锁和释放锁 各有单独的调用方法
public void lock()：加同步锁
public void unlock()：释放同步锁

使用Lock接口的实现类 ReentrantLock
ReentrantLock 即 re(重,再次) - entrant(进入) - lock(锁)， 即重入锁

ReentrantLock 重入锁：支持重入性，
表示能够对共享资源能够重复加锁，即：若当前线程(在某次)获取该锁并释放锁后，再次获取(即 又一次抢锁时)不会被阻塞。

使用步骤：
1.在成员位置(使用上转型写法，左接口 右实现类)创建一个ReentrantLock对象，
因为第2、3步要调用Lock接口中的方法，所以将此对象上转型为 Lock接口，格式：
Lock l = new ReentrantLock();

2.在可能出现线程安全问题的代码【前】，调用 Lock接口的 lock()方法，获取锁
3.在可能出现线程安全问题的代码【后】，调用Lock接口的 unlock()方法，释放锁


*  */


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunImp1 implements Runnable { //RunImp全称为 Runnable Implements 1
    private int ticket = 100;

    Lock l = new ReentrantLock(); //上转型写法，因为要用到Lock接口中的方法


    // 使用同步锁(对象所)
//    Object syncObj = new Object(); //同步锁
//    public void run(){
//        while(true){ //整个死循环
//            synchronized( syncObj ){ //同步代码块
//                if( ticket>0 ){
//                    try{
//                        Thread.sleep( 10 );
//                    }
//                    catch( InterruptedException e ){
//                        e.printStackTrace();
//                    }
//                    System.out.println( Thread.currentThread().getName()+"：正在卖第 "+ticket+" 张票嗷！" );
//                    ticket--;
//                }
//            }
//        }
//    }

    // 使用Lock锁
    public void run(){

        while(true){ //整个死循环

            l.lock(); //2.在可能出现线程安全问题的代码【前】，调用 Lock接口的 lock()方法，获取锁
            if( ticket>0 ){
                try{
                    Thread.sleep( 10 );
                    System.out.println( Thread.currentThread().getName()+"：正在卖第 "+ticket+" 张票嗷！" );
                    ticket--;
                }
                catch( InterruptedException e ){
                    e.printStackTrace();
                }
                finally{
                    l.unlock(); //3.在可能出现线程安全问题的代码【后】，调用Lock接口的 unlock()方法，释放锁
                    //无论程序是否异常，都会释放锁
                }

            }
        }
    }
}
