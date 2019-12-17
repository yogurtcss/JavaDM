package per.yo.basic1.demo02;

public class Student {
    /* 面向过程：当需要实现一个功能时，每一个具体的步骤都要亲力亲为，详细处理每一个细节
    * 面向对象：当需要实现一个功能时 ，不关心具体步骤，而是找一个具有该功能的人，来帮我做事
    *
    * 类与对象的关系
    * 类是对一类事物的描述，是抽象的；
    * 对象是一类事物的实例，是具体的
    *
    * 类是对象的模板，对象是类的实例
    *
    * 类的定义格式
    * public class 类名{
    *    //成员变量 -属性，“是什么”
    *    //成员方法(没有static关键字) -行为，“能做什么”
    * }
    *
    * 注意，普通的方法(非成员方法)具有 static关键字
    *
    *  */

    //成员变量——直接定义在类中、且在成员方法外
    String name; //姓名
    int age; //年龄

    public void eat(){ //成员方法，不加static关键字
        System.out.println( "吃饭饭！" );
    };
    public void sleep(){
        System.out.println( "睡觉觉！" );
    };
    public void study(){
        System.out.println( "学xi!" );
    };
}
