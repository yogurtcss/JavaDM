package pers.yo.adv1.demo05_Extends;

/* 继承关系中，父、子类构造方法的访问 特点：
* 1.子类构造方法中 默认隐含super()的调用，所以 一定是先调用【无参】父类构造，然后执行子类构造
* 2.可在子类构造方法中使用 super 调用父类【重载的】构造方法；
*   如果子类构造方法体为空，则默认使用super()调用父类的无参构造方法 -2019-11-16 11:07:50 订正错误
* 3.当子类使用 super()来调用父类构造时：此super语句必须是子类构造方法中的【第一个语句】！【第一个语句】！【第一个语句】！
* 同一个子类构造，不能多次使用super()来调用父类构造！！(super只能有一个语句，还必须是第一个语句！！)
*  */


public class Demo01Constructor {
    public static void main(String[] args) {
        Father f = new Father();
        Son s1 = new Son();

        s1.showAgeSon();
    }
}
