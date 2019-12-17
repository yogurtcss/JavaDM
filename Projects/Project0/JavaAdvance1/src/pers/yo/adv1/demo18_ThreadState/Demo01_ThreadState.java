package pers.yo.adv1.demo18_ThreadState;

/*
6 个状态定义: java.lang.Thread.State

New: 尚未启动的线程的线程状态。
Runnable: 可运行线程的线程状态，等待 CPU 调度。
Blocked: 线程阻塞等待监视器锁定的线程状态。处于 synchronized 同步代码块或方法中被阻塞。
Waiting: 等待线程的线程状态。下 列不带超时的方式:Object.wait、Thread.join、 LockSupport.park
Timed Waiting: 具有指定等待时间的等待线程的线程状态。下 列带超时的方式:Thread.sleep、0bject.wait、 Thread.join、 LockSupport.parkNanos、 LockSupport.parkUntil
Terminated: 终止线程的线程状态。线程正常完成执行或者出现异常。

------------------------------------------
等待唤醒案例：线程之间的通信：
顾客线程：告知老板要的包子种类和数量，【锁对象syncObj】调用wait方法，放弃CPU的执行，进入waiting状态(无限等待)
老板线程：花5s做包子；做好包子后，【锁对象syncObj】调用notify方法，唤醒顾客吃包子

注：只有锁对象syncObj才能调用wait和notify方法

Object类中已有的方法：
void wait()：
在 其他线程调用此对象(或 当前的锁对象)的notify方法 或 notifyall()方法前，导致当前线程等待
▲ 当线程执行wait()时，会把当前的锁释放，然后让出CPU，进入等待状态。(等什么？等待的是被 notify 或 notifyAll，而不是锁！！下面有详解)
▲ 当处于wait状态的某线程 被通知notify后，立即跳入【锁池】抢锁；
抢锁成功后继续执行 【原线程调用wait()方法之后】的代码！！

void wait( long m指定的毫秒数 )：
若经过指定的毫秒数m后，此线程还没有被notify通知到，则此线程(直接不等了)，自动跳入锁池抢锁


void notify()：通知那些 可能【等待该对象的对象锁】 的其他线程
▲ 如果有多个线程等待，则线程规划器任意挑选出其中一个 wait()状态的线程来发出通知，并使它等待获取该对象的对象锁；

当前线程 notify 后，当前线程不会马上释放该对象锁， wait所在的线程并不能马上获取该对象锁；
而是：要等到程序退出 synchronized 代码块后，当前(调用notify的)线程才会释放锁， wait所在的线程也才可以获取该对象锁，
但不惊动其他同样在等待被该对象 notify 的线程们。

当第一个获得了该对象锁的 wait 线程运行完毕以后，它会释放掉该对象锁，
此时如果该对象没有再次使用 notify 语句，则即便该对象已经空闲，
其他 wait 状态等待的线程由于没有得到该对象的通知，会继续阻塞在 wait 状态，
直到这个对象发出一个 notify 或 notifyAll。这里需要注意：它们等待的是被 notify 或 notifyAll，而不是锁。
//抢锁成功后继续执行 【原线程调用wait()方法之后】的代码！！

void notifyAll()
该方法与 notify () 方法的工作方式相同，重要的一点差异是：

notifyAll 使所有原来在该对象上 wait 的线程统统退出 wait 的状态
即全部被唤醒，【不再等待 notify 或 notifyAll】，但由于此时还没有获取到该对象锁，因此还不能继续往下执行，变成 【等待获取该对象上的锁】，
一旦该对象锁被释放（notifyAll 线程退出调用了 notifyAll 的 synchronized 代码块的时候），他们就会去竞争。
如果其中一个线程获得了该对象锁，它就会继续往下执行，// 抢锁成功后继续执行 【原线程调用wait()方法之后】的代码！！
在它退出 synchronized 代码块，释放锁后，其他的已经被唤醒的线程将会继续竞争获取该锁，一直进行下去，直到所有被唤醒的线程都执行完毕。


wait、notify 以及 notifyAll都是 Object 对象的方法，
他们必须在被 synchronized 同步的方法或代码块中调用，否则会报错。


wait 和 sleep 的区别，这也是面试经常面到的问题。
1.sleep 是 Thread 类的方法而 wait 是 Object 类的方法。
2.sleep 不会立马释放对象锁，而 wait 会释放。

------------------------------------------------------
深入理解 —— 需要【锁池】、【等待池】的前置知识，再往下看
1。如果线程调用了对象的 wait()方法，那么线程便会处于该对象的【等待池】中，等待池中的线程不会去竞争该对象的锁。
2.当有线程调用了对象的 notifyAll ()方法(唤醒所有wait状态的线程)，或notify()方法(只随机唤醒一个wait状态线程)，
被唤醒的的线程便会进入该对象的【锁池】中，锁池中的线程会去竞争该对象锁。
抢锁成功后继续执行 【原线程调用wait()方法之后】的代码！！

优先级高的线程竞争到对象锁的概率大，假若某线程没有竞争到该对象锁，它还会留在锁池中，唯有线程再次调用wait()方法，它才会重新回到等待池中。
而竞争到对象锁的线程则继续往下执行，直到执行完了 synchronized 代码块，它会释放掉该对象锁，这时锁池中的线程会继续竞争该对象锁。

------------------------------------------------------
【锁池】、【等待池】的前置知识

1.锁池：假设线程A已经拥有了某个对象（不是类）的锁，而其它线程 B、C 想要调用这个对象的某个synchronized方法（或者块），
由于这些B、C线程在进入对象的 synchronized 方法（或者块）之前【必须先获得该对象锁的拥有权】，
而恰巧该对象的锁目前正被线程 A 所拥有，所以这些 B、C 线程就进入了该对象的锁池，这就是锁池。

2.等待池：假设线程A调用了某个对象的 wait () 方法，
线程A就会释放该对象的锁 —— 因wait()方法必须在synchronized中使用，所以执行wait()方法前 线程A已经持有了该对象的锁
同时线程A就进入到了该对象的等待池中；
如果此时线 B调用了相同对象的 notifyAll () 方法，则处于该对象等待池中的线程就会【全部进入该对象的锁池中】去准备争夺锁的拥有权； //抢锁成功后继续执行 【原线程调用wait()方法之后】的代码！！
而如果此时线程B调用的是相同对象的 notify () 方法，则仅仅会有一个处于该对象等待池中的线程（随机）进入【该对象的锁池】中去准备争夺锁的拥有权。//抢锁成功后继续执行 【原线程调用wait()方法之后】的代码！！

------------------------------------------------------
锁池：当前没拿到锁的线程们，快活地在一个池子【即 该公共全局的锁对象 的池子】中抢锁；(抢到锁后就开始整活)
等待池：某线程主动调用(公共、全局的)锁对象.wait()后，(不情愿地)立即放弃该锁，而主动跳入等待池中，等待notify(通知)；
当此线程被通知后，跳入锁池抢锁；
抢锁成功后继续执行 【原线程调用wait()方法之后】的代码！！

*  */

//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//java.lang.Runnable
//java.lang.Thread

public class Demo01_ThreadState {
    public static void main(String[] args) {
        Object syncObj = new Object(); //全局、公共的锁对象

        Runnable guestTarget = new Runnable(){ //匿名内部类作为 Runnable接口的实现类，创建实例对象嗷
            public void run(){
                synchronized( syncObj ){ //抢同一个锁
                    System.out.println( "我要吃包子嗷！(告知老板要的包子种类和数量)" );
                    try{
                        syncObj.wait(); //无限等待状态
                    }catch( InterruptedException e ){
                        e.printStackTrace();
                    }
                    //被通知后(如果存在多个处于wait状态的线程，这是任意的“被通知”嗷)，跳入锁池抢锁；抢锁成功后继续执行 【原线程调用wait()方法之后】的代码！！
                    System.out.println( "我醒了，吃喽！" );
                }
            }
        };
        Thread guest = new Thread( guestTarget );

        Runnable bossTarget = new Runnable(){
            public void run(){
                try{
                    Thread.sleep( 5000 ); //线程睡眠5s：即5s做包子
                }catch( InterruptedException e ){
                    e.printStackTrace();
                }
                synchronized( syncObj ){ //抢同一个锁
                    System.out.println( "5s后做好包子啦！" );
                    //通知 等待池中的【某个线程】：准备跳入锁池抢锁喽
                    syncObj.notify(); //如果存在多个处于wait状态的线程，这是任意的“被通知”嗷：随机通知一个处于wait状态的线程
                }
            }
        };
        Thread boss = new Thread( bossTarget );

        guest.start();
        boss.start();

    }
}
