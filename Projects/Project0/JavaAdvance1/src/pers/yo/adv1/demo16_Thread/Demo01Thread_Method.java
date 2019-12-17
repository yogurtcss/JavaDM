package pers.yo.adv1.demo16_Thread;

/* Thread类 【注释格式2.0】
* 先删去*号形成一个空行，然后ctrl+Enter生成无*号的空行；
* 按键盘 ↓ 键切换至下一行(不要按 Enter键，默认生成*号)


一、构造方法
public Thread()：分配一个新的线程对象

【 (让父类Thread)设置(其子类的)线程名 】public Thread( String name )：分配一个 指定名字的 新的线程对象
* 在创建继承Thread类的子类时，若要设置子类线程名，则可用public 当前类型( String name )的构造方法，
* 在此构造方法中调用super( 传入待设置的线程名 )，让父类Thread设置当前子类的线程名。 格式如下：
* public MyThread( String name ){
*    super( name ); //把线程名传给父类，让父类Thread设置当前子类的线程名
* }


public Thread( Runnable target )：分配一个 带有指定目标的 新的线程对象
public Thread( Runnable target, String name )：分配一个 带有执行目标 的新的线程对象 并指定此线程的名字

二、常用方法1
public String getName()：获取当前线程名
public void start()：手动启动此线程；Java虚拟机会自动调用此线程的run方法
public void run()：在 继承Thread的子类中的 重写(Thread类的)run方法，表示此线程要执行的任务

▲ 以下的静态方法，通过 类型.静态方法名 来调用：Thread.XXX()
静态方法 public static void sleep( long millis )：使当前正在执行的线程 以指定的毫秒数mill_is 暂停执行
* 毫秒数结束后，线程继续执行嗷

静态方法 public static Thread currentThread()：返回 当前正在执行的线程实例对象
如：Thread.currentThread();

-------------------------------------------------
1.设置线程名(见上部)：子类使用public 当前类型( String name )的构造方法，在里面调用super(name)，把线程名传给父类，让父类Thread设置当前子类的线程名
2.修改线程名：继承Thread类的子类的实例对象mt .setName( 此线程mt的新名字name )：将当前线程对象mt的名字修改为 name


*  */


import pers.yo.adv1.demo16_Thread.MyThread;

public class Demo01Thread_Method {
    public static void main(String[] args) {
//        MyThread mt1 = new MyThread();
//        mt1.start();
//        new MyThread().start();//创建新线程，并启动之
//        System.out.println( "---------" );
//        System.out.println( Thread.currentThread() );
//        System.out.println( Thread.currentThread().getName() ); //正在执行的线程的名字
//        System.out.println( "---------" );

        for( int i=1; i<=60; i++ ){//模拟秒表
            System.out.println( i );

            try{ //Thread.sleep(1000); 让程序睡眠1秒；毫秒数结束后，线程继续执行嗷
                Thread.sleep( 1000 );
            }
            catch( InterruptedException e ){ //中断异常
                e.printStackTrace(); //在命令行打印异常信息在程序中出错的位置及原因
            }
        };



    }
}
