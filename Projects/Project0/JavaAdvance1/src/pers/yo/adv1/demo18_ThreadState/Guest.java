package pers.yo.adv1.demo18_ThreadState;

import pers.yo.adv1.demo18_ThreadState.BaoZi;

public class Guest extends Thread {
    private BaoZi bz;

    public Guest(BaoZi bz) {
        this.bz = bz;
    }

    public void run(){
        while(true){
            synchronized( bz ){
                if( bz.isCreated()==false ){ //当没有包子时，客人wait
                    try{
                        bz.wait(); //当没有包子时，客人wait
                    }catch( InterruptedException e ){
                        e.printStackTrace();
                    }
                }
                //wait之后的代码：被通知notify后才执行的
                System.out.println( "客人正在吃："+bz.getPi()+bz.getXian()+" 的包子嗷！" );
                bz.setCreated( false ); //修改包子的状态
                bz.notify(); //通知包子铺生产包子
                System.out.println( "客人已经把："+bz.getPi()+bz.getXian()+" 的包子吃完啦！包子铺快进入下一轮生产！" );
                System.out.println( "--------------------------" );
            }
        }
    }



}