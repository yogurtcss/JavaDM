package pers.yo.adv1.demo05_Extends;

public class Father {
    public int age = 30; //父类与子类的相同(同名)成员变量不会覆盖，是互相独立的

    public Father(){//无参构造方法
        System.out.println( "父类无参构造方法。" );
    }
    public Father( int num ){
        System.out.println( "父类有参构造方法，传入一个num："+num );
    }

    public void call(){
        System.out.println( "我是Father的call！" );
    }

    public void showAgeFather(){
        //此age变量使用的是本类中的age，不会向下找子类的！！
        System.out.println( "此age变量是Father的，不会向下找子类的！！"+age );
        System.out.println( "♥ 此showAgeFather()是在Father类中定义的，所以优先用的是Father的age变量！！"+age );
    }

    public void method_DoubleName(){ //这里是 方法名 重名！！！method double name
        /* 父类与子类的方法名重名，使用成员方法method的规则：
        *    看看创建此实例对象s时 等号右边是谁(就是看 由谁new出来的！)，实例对象就优先用谁的成员方法method；没有则向(上一辈，如父亲)上找
        *  */
        System.out.println( "我是父类的method_DoubleName！" );
    }

}
