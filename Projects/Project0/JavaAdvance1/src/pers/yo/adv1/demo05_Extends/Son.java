package pers.yo.adv1.demo05_Extends;

/* super关键字用来访问父类的内容，其三种用法：
* 1.在子类的成员方法中，访问【父类的成员变量】
* 2.在子类的成员方法中，访问【父类的成员方法】
* 3.在子类的构造方法中，访问【父类的构造方法】
*   对3的注：此super语句必须是子类构造方法中的【第一个语句】！【第一个语句】！【第一个语句】！super只能有一个语句，还必须是第一个语句！！
*  */

/* this关键字用来访问本类(在这里为 子类)的内容，其三种用法：
* 1.在本类的成员方法中，访问【本类的成员变量】 -之前已做过
* 2.在本类的成员方法中，访问【本类的 另一个成员方法】
* 3.在本类的构造方法中，访问【本类的另一个构造方法】
*   对3的注: (1)this(..)调用必须是构造方法的唯一一个this语句！且是第一个语句！
*           (2)super和this，不能同时使用！！
*  */

/* 复盘总结：Java继承的三个特点
* 1.只能单继承：一个类的直接父类只能有唯一一个；
* 2.允许多级 的单继承：爷-父-子-孙-...
* 3.一个父类可拥有多个子类，“儿孙绕膝”：父-子1，子2，子3 ...
*  */

public class Son extends Father { //单继承：一个类的直接父类只能有唯一一个
    public int age = 1; //父类与子类的相同(同名)成员变量不会覆盖，是互相独立的

    public Son(){  //无参构造
        // super(); //调用父类的无参构造方法，即使不写这句话，系统也会在此处默认加这句话
        super(20); //调用父类重载的构造方法
        System.out.println( "子类调用父类重载的构造方法成功啦！" );
    }

    public void showAgeSon(){
        /* 因为本类中有age，所以优先使用本类中的age
        * 若本类中没有age，则向(上一辈，如父亲)上找age
        * 类似于 JavaScript的原型链
        *  */
        System.out.println( "因为本类Son中有age，所以优先使用本类Son中的age！"+age );
        System.out.println( "▲ 注：若本类Son中没有age，则向(上一辈，如父亲)上找age！" );

        System.out.println( "-------------------------------------------" );
        System.out.println( "子类成员方法，调用父类的成员变量："+super.age );
        System.out.println( "子类成员方法，调用父类的成员方法，👇" );
        super.call();
    }

    //Double Name 即重名，相同名字
    public void method_Var_DoubleName(){ //这里是 变量名重名 var double name
        int age = 300000; //方法内的局部变量：直接按普通写法来写即可
        System.out.println( "此方法内的局部变量："+age ); //打印的是方法内的局部变量
        System.out.println( "本类(在这里 为子类)的成员变量this.age ："+this.age ); //打印的是本类(在这里 为子类)的成员变量this.age=1
        System.out.println( "父类的成员变量super.age ："+super.age ); //打印的是父类的成员变量super.age=30
    }
    public void method_DoubleName(){ //这里是 方法名 重名！！！method double name
        /* 父类与子类的方法名重名，使用成员方法method的规则：
        *    看看创建此实例对象s时 等号右边是谁(就是看 由谁new出来的！)，实例对象就优先用谁的成员方法method；没有则向(上一辈，如父亲)上找
        *  */
        System.out.println( "我是子类的method_DoubleName！" );
    }

}
