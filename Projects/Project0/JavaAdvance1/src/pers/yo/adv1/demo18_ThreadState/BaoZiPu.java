package pers.yo.adv1.demo18_ThreadState;

public class BaoZiPu extends Thread {
    private BaoZi bz; //共享资源：包子bun，实例对象为b
    //锁对象必须唯一：在这里以包子对象作为锁对象

    public BaoZiPu( BaoZi bz ){
        this.bz = bz;
    }

    public void run(){
        int count = 0; //生产包子的数量

        while(true){ //让包子铺一直生产包子
            synchronized( bz ){
                if( bz.isCreated()==true ){ //当有包子时，包子铺不生产，就等待 wait
                    try{
                        bz.wait();//当有包子时，包子铺不生产，就等待 wait
                    }catch( InterruptedException e){
                        e.printStackTrace();
                    }
                }
                else{ //当没有包子时，包子铺进入生产
                    if( count%2==0 ){
                        bz.setPi("薄皮");
                        bz.setXian("三鲜馅");
                    } else{
                        bz.setPi("冰皮");
                        bz.setXian("牛肉馅");
                    }
                    count++; //生产的包子数+1
                    System.out.println( "包子铺正在生产："+bz.getPi()+" "+bz.getXian()+" 的包子嗷！" );

                    //生产间隔3s，此线程睡眠3s
                    try{
                        Thread.sleep( 3000 );
                    }catch( InterruptedException e ){
                        e.printStackTrace();
                    }
                    /* 此时包子铺生产好了包子
                     * 修改包子的“生产情况”为true
                     *  */
                    bz.setCreated( true );
                    //通知：wait状态的线程来抢锁
                    bz.notify(); //通知 吃包子
                    System.out.println( "包子铺已经生产好了："+bz.getPi()+" "+bz.getXian()+" 的包子嗷喽！，快来吃！" );
                }
            }
        }
    }

}
