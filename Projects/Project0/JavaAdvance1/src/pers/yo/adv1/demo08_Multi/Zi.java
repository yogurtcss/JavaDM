package pers.yo.adv1.demo08_Multi;

import pers.yo.adv1.demo08_Multi.Fu;

public class Zi extends Fu { //子
    /* 上转型对象调用的方法，要么是被子类重写过的方法(在这里是method)，
    * 要么是直接从父类那继承未被重写的方法，不可能是隐藏方法。
    *
    * 上转型对象不能调用子类新增的(子类独有的)方法(在这里是 methodZi)
    *
    * 向下转型，还原回Zi，则可调用 子类新增的(子类独有的)方法(在这里是 methodZi)
    *  */

    public void method(){
        System.out.println( "我是子类方法！" );
    }
    public void methodZi(){
        System.out.println( "我是子类新增的(子类独有的)方法，上转型对象不能调用我嗷！" );
    }
}
