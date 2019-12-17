package pers.yo.adv1.demo04_API1;

/* static方法 称作静态方法，由于静态方法不依赖于任何对象就可以进行访问，因此对于静态方法来说，是没有this的；
因为它不依附于任何对象，既然都没有对象，就谈不上 this 了。
并且由于这个特性，在静态方法中不能访问类的非静态成员变量和非静态成员方法，
因为 非静态成员方法/变量 都是 必须依赖具体的对象才能够被调用。

static变量 称作静态变量，
静态变量和非静态变量的区别是：
1.静态变量 被 该同一类下创建的所有的实例对象 所共享，在内存中只有一个副本，它当且仅当在类初次加载时会被初始化；
对静态成员变量 赋值：直接通过 类名.静态成员=XXX 进行赋值

2.而非静态变量是 该实例对象 所拥有的，在创建对象的时候被初始化，存在多个副本，各个对象拥有的副本互不影响；
非静态变量：必须先创建实例对象，然后通过实例对象取使用它

static代码块 称作静态代码块，可以用来优化程序性能——理由：因为它的特性只会在类加载的时候执行一次。
static代码块可以置于类中的任何地方，类中可以有多个static块。
在类初次被加载的时候，会按照static 块的顺序来执行每个static块，并且只会执行一次。
static块的典型用途：一次性地对静态成员变量(通过 类名.静态成员=XXX )进行赋值

*  */


public class Demo06Static {
    public static void main(String[] args) {
        Student s1 = new Student( "小明", 10 );
        Student s2 = new Student( "👴佛了", 6324 );
        Student s3 = new Student( "嗯嗯", 233 );

        /* 静态成员变量，该同一类下创建的所有的实例对象 所共享
        * 只需 使用该类Student 设置一次room的值即可：类名.room = "北主楼303"，即 Student.room = "北主楼303"；
        *
        * 其实例对象s1、s2、s3、s4等 就共享此room值了，
        * 即 s1、s2、s3、s4的room值都是 "北主楼303"
        *  */
        Student.room = "啦啦啦"; //对静态成员变量 赋值：直接通过 类名.静态成员=XXX 进行赋值
        System.out.println( s1.room );
        System.out.println( s2.room );
        System.out.println( s3.room );
    }
}
