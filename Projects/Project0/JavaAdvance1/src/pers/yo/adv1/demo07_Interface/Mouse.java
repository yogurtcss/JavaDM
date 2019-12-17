package pers.yo.adv1.demo07_Interface;

import pers.yo.adv1.demo07_Interface.USB;

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