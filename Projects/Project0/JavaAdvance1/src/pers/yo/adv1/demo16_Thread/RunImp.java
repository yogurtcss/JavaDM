package pers.yo.adv1.demo16_Thread;

public class RunImp implements Runnable { //RunImp全称 Runnable Implements
    public void run(){
        for( int i=0; i<20; i++ ){
            //Thread.currentThread() 返回当前线程对象；继续 .getName() 可得当前线程对象的名字
            System.out.println( Thread.currentThread().getName()+" --> "+ i );
        }
    }
}
