package pers.yo.adv1.demo16_Thread;

public class MyThread extends Thread {
    public void run(){
        System.out.println( this.getName() );
    }
}
