package pers.yo.adv1.demo07_Interface;

/* 1.类与类之间是单继承的，直接父类只能存在一个.
* 2.类与接口之间是多实现的，一个类可以实现多个接口。
* 3.接口与接口之间是多继承的。
*
*  */

import pers.yo.adv1.demo07_Interface.ImplementClassFour;

public class Demo02Relations  {
    public static void main(String[] args) {
        ImplementClassFour impC4 = new ImplementClassFour();
        impC4.methodAbs1();
        impC4.method();
        impC4.methodDefault();
    }
}
