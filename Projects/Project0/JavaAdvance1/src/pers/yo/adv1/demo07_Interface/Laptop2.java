package pers.yo.adv1.demo07_Interface;

/* class X extends 抽象类名B implements 接口名A
 * 子类同时继承抽象类和实现接口
 * */

import pers.yo.adv1.demo07_Interface.Mechine;
import pers.yo.adv1.demo07_Interface.USB;
import pers.yo.adv1.demo07_Interface.Mouse;
import pers.yo.adv1.demo07_Interface.Keyboard;

//多态写法
public class Laptop2 extends Mechine implements USB { //笔记本 同时继承抽象类机器mechine和实现接口USB

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

    public void useDevice( USB usb ){ //使用接口 实现类的实例对象 作为形参
        usb.open(); //向上转型后，使用共性方法：打开设备
        /* 以下开始使用具体设备了！！向下转型，使用具体的方法 */
        if( usb instanceof Mouse ){ //先判断
            Mouse ms = (Mouse) usb; //向下转型，还原成为鼠标
            ms.click();
        }
        else if( usb instanceof Keyboard ){ //先判断
            Keyboard kb = (Keyboard) usb;
            kb.tap();
        }

        usb.close(); //向上转型后，使用共性方法：关闭设备
    }



}
