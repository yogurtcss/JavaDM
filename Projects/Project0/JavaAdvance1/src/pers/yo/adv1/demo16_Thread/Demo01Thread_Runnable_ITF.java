package pers.yo.adv1.demo16_Thread;

/*
创建线程法2：Runnable接口( Runnable 可运行的、“任务” )，java.lang.Runnable 不需导包

1.定义 Runnable接口 的实现类，并重写该接口的run()方法
   - 该run()方法中的方法体，也是代表了此线程需要完成的任务；——故称run() 方法为线程执行体
2.【创建子任务实例r】创建 Runnable接口 的实现类 的【实例对象】为r(记为 子任务r)
3.【关键：传递子任务r，并生成真正的线程对象】把 上述的子任务r 作为 父类Thread的形参(即target)，以创建 父类Thread的实例对象——真正的线程对象

*  */

import pers.yo.adv1.demo16_Thread.RunImp;

public class Demo01Thread_Runnable_ITF {
    public static void main(String[] args) {
        RunImp target = new RunImp(); //Runnable接口的实现类的实例对象作为  【父类Thread(构造方法中)的形参target】，以创建真正的线程对象嗷
        Thread t = new Thread( target, "【复读姬】这是我的名字！！我是Runnable接口的实现类的实例对象嗷！我作为【父类Thread(构造方法中)的形参target】!" );
        t.start(); //新线程执行辽

        //这是在 主线程执行的 run()方法嗷
        for( int i=0; i<20; i++ ){
            //Thread.currentThread() 返回当前线程对象；继续 .getName() 可得当前线程对象的名字
            System.out.println( Thread.currentThread().getName()+" --> "+ i );
        }
    }
}
