package pers.yo.adv1.demo07_Interface;

import pers.yo.adv1.demo07_Interface.USB;

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
