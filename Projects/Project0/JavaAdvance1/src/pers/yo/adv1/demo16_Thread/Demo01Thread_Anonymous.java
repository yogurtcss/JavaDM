package pers.yo.adv1.demo16_Thread;

/*
匿名内部类格式：本质上就是实例化对象的过程
右边是 new一个匿名内部类，左边就是 实例化的对象

接口名 变量名(实现对象名) = new 接口名() { //此花括号的实现类就是一个匿名内部类
    //覆盖重写此接口的所有抽象方法
   //因为要实现接口，所以匿名内部类不能是抽象类！！(抽象类中：只定义抽象方法而不实现；而实现接口的类 是必须要实现接口的所有抽象方法！)
   //匿名内部类不能定义构造器（构造方法），因为匿名内部类没有类名，所以无法定义构造器(构造方法)，
}

2019-11-20 15:16:20 注：使用匿名内部类方式创建线程

父类名 变量名(实例对象名) = new 父类名(){ //此花括号的实现类就是一个匿名内部类
    //...
}


*  */

public class Demo01Thread_Anonymous {
    public static void main(String[] args) {
        Runnable target1 = new Runnable(){ //匿名内部类的写法，有点忘了？
            public void run(){
                System.out.println( "(●'◡'●)我是使用匿名内部类写法：Runnable接口的实现类的实例对象嗷！" );
            }
        };
        new Thread( target1 ).start(); //这跳步我佛了

        Thread t1 = new Thread(){ //new 父类/接口名， 后面紧跟个花括号，就叫匿名内部类了嗷
            public void run(){
                System.out.println( "O(∩_∩)O我是使用匿名内部类写法：继承父类Thread的子类的实例对象嗷！" );
            }
        };
        t1.start();
    }
}
