package pers.yo.adv1.demo07_Interface;

import pers.yo.adv1.demo07_Interface.ITF_abs;
import pers.yo.adv1.demo07_Interface.ITF_default;
import pers.yo.adv1.demo07_Interface.ITF_static;

public class ImplementClass implements ITF_abs, ITF_default, ITF_static {
    @Override
    public void methodAbs1() {
        System.out.println( "我实现了接口的抽象方法1了！" );
    }
}
