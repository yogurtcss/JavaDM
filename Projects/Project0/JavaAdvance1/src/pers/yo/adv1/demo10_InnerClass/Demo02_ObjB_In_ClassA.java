package pers.yo.adv1.demo10_InnerClass;

/* 一个类B的实例对象，作为另一个类A的成员变量，怎摸用？
* 就把此实例对象 当作 普通成员变量来使用即可
* 即：创建一个B实例对象b，通过setB设置为成员变量
*
* 举例：Weapon武器类的实例对象w，作为另一个类Hero的成员变量
*
*  */

import pers.yo.adv1.demo10_InnerClass.Hero;

public class Demo02_ObjB_In_ClassA {
    public static void main(String[] args) {
        Hero h = new Hero( "北洛", 233 );
        //创建一个Weapon实例对象wp，设置为成员变量
        Weapon wp = new Weapon( "太岁" );
        h.setWp( wp );//设置武器wp为h的成员变量
        h.attack();
    }
}
