package pers.yo.adv1.demo05_Extends;

/* 面向对象三大特征：封装性、继承性、多态性
* 继承是多态的前提
* 继承主要解决的问题是：共性抽取
*
* 继承的特点：
* 1.子类可以拥有父类的“内容”
* 2.子类还可以拥有专属的“内容”
*
* 注：父类也可以称为基类、超类；
* 子类也可以称为 派生类
*
* 定义父类的格式：只是一个普通的类定义
* public class 父类名{
*   ...
* }
*
* 定义子类的格式
* public class 子类名 extends 父类名{
*   ...
* }
*
* 对于父类与子类具有相同(同名)成员变量时，欲访问成员变量：
* ▲直接通过子类对象(.点号)访问成员变量：
*     看看创建此实例对象s时 等号左边是谁(不能看是由谁new出来的！)，实例对象就优先用谁的成员变量；没有则向(上一辈，如父亲)上找
* 【特别注意！】 Father sX = new Son(); //这时候还是要看 等号左边是谁！(不能看是由谁new出来的！)这里等号左边是Father，实例对象sX用的是Father的成员变量！
*
* ▲间接通过成员方法(method)访问成员变量：
*     看看该方法(method) 属于谁(哪个类)，就优先用谁的成员变量；没有则向(上一辈，如父亲)上找
*
*  */

public class Demo01ExtendsVar {
    public static void main(String[] args) {
        Father f = new Father();
        Son s1 = new Son(); //创建s1实例对象时，等号左边是Son(不能看是由谁new出来的！)，所以用的成员变量是Son专有的
        Father s2 = new Father(); //【迷惑性！】创建s2实例对象时，等号左边是Father，(不能看是由谁new出来的！)，所以用的成员变量是Father专有的！

        s1.call(); //儿可调用爹的call
        System.out.println( "-----------------------------------------" );
        //▲直接通过子类对象(.点号)访问成员变量：
        System.out.println( "我是由Father类 new出来的 实例f嗷！"+f.age ); //30
        System.out.println( "我是由Son类 new出来的 实例s1嗷！"+s1.age );//1。创建s1实例对象时，等号左边是Son(不能看是由谁new出来的！)，所以用的成员变量是Son专有的
        System.out.println( "我是由Father类 new出来的 实例s2嗷！"+s2.age ); //30。【迷惑性！】创建s2实例对象时(不能看是由谁new出来的！)，所以用的成员变量是Father专有的！
        System.out.println( "-----------------------------------------" );


        System.out.println( "▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼" );
        System.out.println( "问：恐怖的东西来了：Father sX = new Son()， 那么实例对象sX调用的是谁的成员变量呢？" );

        Father sX = new Son(); //向上转型，日后研究
        System.out.println( "答：创建实例对象sX时，等号左边是Father，所以调用的还是Father的成员变量！"+sX.age );

        System.out.println( "▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲" );


        System.out.println( "-----------------------------------------" );
        //▲间接通过成员方法访问成员变量：
        f.showAgeFather();
        s1.showAgeSon();
        System.out.println( "-----------------------------------------" );
        s1.showAgeFather(); //此showAgeFather()是在Father类中定义的，所以优先用的是Father的age变量！！

    }
}
