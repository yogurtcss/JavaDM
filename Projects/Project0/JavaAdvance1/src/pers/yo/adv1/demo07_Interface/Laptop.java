package pers.yo.adv1.demo07_Interface;

/* class X extends 抽象类名B implements 接口名A
* 子类同时继承抽象类和实现接口
* */

import pers.yo.adv1.demo07_Interface.Mechine;
import pers.yo.adv1.demo07_Interface.USB;

//内部类写法
public class Laptop extends Mechine implements USB { //笔记本 同时继承抽象类机器mechine和实现接口USB

    public class Mouse implements USB {
        public void open(){
            System.out.println( "鼠标启动喽！" );
        }
        public void close(){
            System.out.println( "鼠标关闭喽！" );
        }
        public void click(){ //点击 click，敲击 tap
            System.out.println( "鼠标点击！" );
        }
    }

    public class Keyboard implements USB {
        public void open(){
            System.out.println( "键盘启动喽！" );
        }
        public void close(){
            System.out.println( "键盘关闭喽！" );
        }
        public void tap(){ //点击 click，敲击 tap
            System.out.println( "键盘敲击喽！" );
        }
    }


    //以下重写抽象类 boot和shutDown 方法
    public void boot(){
        System.out.println( "电脑启动喽！boot" );
    }
    public void shutDown(){
        System.out.println( "电脑关闭喽！shut down" );
    }
    //已有接口的默认方法 open和close了

    public void open(){
        System.out.println( "电脑USB设备启动喽！" );
    }
    public void close(){
        System.out.println( "电脑USB设备关闭喽！" );
    }

}
