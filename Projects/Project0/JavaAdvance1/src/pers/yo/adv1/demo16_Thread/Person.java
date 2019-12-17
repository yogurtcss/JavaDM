package pers.yo.adv1.demo16_Thread;

import java.lang.Thread; //lang包下的子包，不需import导入嗷

public class Person extends Thread {

    public Person(){
    }

    public Person(String name) {
    }

    public void run(){
        for( int i=0; i<20; i++ ){
            System.out.println( "新线程："+i );
        }
    }

}
