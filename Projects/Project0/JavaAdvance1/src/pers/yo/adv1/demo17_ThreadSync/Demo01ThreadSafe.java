package pers.yo.adv1.demo17_ThreadSync;

/* 线程同步 Thread synchronization  (这名字太长了8)

一、线程安全：指某个函数、函数库在多线程环境中被调用时，能够【正确地处理多个线程之间的共享变量】，使程序功能正确完成。
▲ 单线程程序不会出现 线程安全 的问题；
▲ 若多线程程序没有访问共享变量，也不会出现 线程安全 的问题；
▲ 若多线程程序【访问共享变量】，则可能出现 线程安全 的问题

正确的做法为：
某个线程a正修改共享资源时，令：其他线程不能修改该共享资源，进入“等待”状态；
而当线程a修改完毕、同步之后，其他线程才能取抢夺CPU资源，完成对应操作

------------------------------------------------------------
Java中3种线程同步机制：
1.同步代码块
2.同步方法
3.锁机制

------------------------------------------------------------
1.同步代码块：synchronize   /ˈsɪŋ.krə.naɪz/
synchronized关键字可用于方法中的某个区块中，表示只对这个区块的资源进行 【互斥访问】，格式为：

synchronized(同步锁){
    //需要同步操作的代码
}

同步锁：
对象的同步锁只是一个概念，可以想象为 在对象上标记了一个锁
1.锁对象 可以是任意类型的；
但必须在 Runnable接口实现类中 且 在重写的run()方法外 创建锁对象Object
这样确保此锁对象是 【全局】的，使得 多个线程对象使用的是同一把锁；格式如下：
Object obj = new Object(); //这么简单！！

▲ 若在重写的run()方法中创建锁对象Object，则每个不同的线程都会创建不同的锁对象！！
如此，这堆线程对象就不是使用同一把锁了！！

2.多个线程对象 必须使用同一把锁
注：在任意时刻，最多允许一个线程拥有同步锁：
谁(哪个线程)拿到同步锁，就进入谁(哪个线程)的代码块；而其他没拿到同步锁的线程只能在外等着(BLOCKED)

------------------------------------------------------------
2.同步方法：使用synchronized修饰的方法。
作用：当A线程执行该方法时，其它线程只能在方法外等着。格式如下：
public synchronized void method(){ //无返回值嗷
    //可能产生线程安全的代码
}
▲ 这时的同步锁是谁？
对于非static(全局)的方法：同步锁就是this(即实现类的实例对象)
对于static(全局)的方法，同步锁就是 当前方法所在类的字节码对象(.class)
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

import pers.yo.adv1.demo17_ThreadSync.RunImp1;

public class Demo01ThreadSafe {
    public static void main(String[] args) {
        //卖票的死循环操作
        RunImp1 target = new RunImp1();
        //new Thread(target) 直接创建一个线程实例对象
        new Thread(target).start();
        new Thread(target).start();
        new Thread(target).start();
    }
}
