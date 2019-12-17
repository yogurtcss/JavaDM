package pers.yo.adv1.demo07_Interface;

import pers.yo.adv1.demo07_Interface.ITF_extendsFour;

public class ImplementClassFour implements ITF_extendsFour {
    public void methodAbs1(){
        System.out.println( "我实现了抽象方法1了！" );
    }

    @Override
    public void method() {
        System.out.println( "我实现了接口Four的抽象方法了！" );
    }
}
