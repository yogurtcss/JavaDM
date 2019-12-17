package pers.yo.adv1.demo10_InnerClass;

/* 新语法：在内部类中访问外部类的(同名)成员变量
* 格式为：外部类.this.外部类的成员变量名
*
* 局部内部类的实例对象A 欲访问的局部变量，必须用final修饰。// ▲最新的注:在Java8中，被局部内部类引用的局部变量，默认添加final，所以不需要添加final关键字
* 理由：假设 已有方法method，此方法中的 局部内部类的实例对象为A，此方法的局部变量为x；
*
*
* 因为当调用这个方法method时：局部变量x如果没有用final修饰，那么x的生命周期和方法method的生命周期是一样的：
* ▲ 局部变量是随着方法的(入栈)调用而(入栈)调用，随着调用完毕而立刻(出栈)消失；
* ▲ 而new出来的、位于堆内存的对象内容 (只要有人还引用该对象) 仍会在堆中持续存在，直到垃圾回收消失；
* ▲ 所以，new出来的对象A，比 此局部变量x  活得久！！
*
* 当方法method弹栈，这个局部变量x也会立刻(弹栈)消失 —— x已死了
* 那么：如果尚存着的局部内部类对象A想用这个局部变量x，(因为x死了)，
* A仍抓着一个不存在的局部变量x！！——这是不允许的。
* 如果用 final 修饰会在类加载的时候进入常量池，即使方法弹栈，常量池的常量还在，也可以继续使用。
*
* 更深的解释：局部变量定义为 final 后，编译器会把匿名内部类对象要访问的所有 final 类型局部变量，都拷贝一份作为该对象的成员变量。
* 这样，即使栈中局部变量已经死亡，匿名内部类对象照样可以拿到该局部变量的值，因为它自己拷贝了一份，且与原局部变量的值始终保持一致
*
*  */

public class Outer {
    public int n = 10; //外部类的成员变量

    public class Inner{
        int n = 20; //内部类的成员变量
        public void methodInner(){
            int n = 30; //内部类方法的局部变量
            System.out.println( n ); //就近原则：打印的是 内部类方法的局部变量
            System.out.println( this.n ); //this关键字，访问类中的变量、方法
            System.out.println( Outer.this.n ); //新语法：在内部类中访问外部类的(同名)成员变量
            // 格式为：外部类.this.外部类的成员变量名
        }
    }

    public void methodOuter(){ //可套娃：里面嵌套调用 局部内部类的方法

        final int x = 1000; //所在方法的局部变量

        class Inner1{ //局部内部类的定义【没有权限修饰符public、private前缀嗷！！】，只能在此所属方法内使用嗷！
            //局部内部类的定义 不能有 public、private、protected 修饰，好理解，因为局部变量也是没有这些修饰符的。
            int n = 1;
            public void methodInner1(){
                int n = 2;
                System.out.println( "套娃：里面嵌套调用 局部内部类的方法喽！" );
                System.out.println( "我要用局部内部类所述方法的 (final)局部变量喽！："+x );
            }
        }
        Inner1 in1 = new Inner1(); //局部内部类，只能在此所属方法内使用嗷！
        in1.methodInner1(); //调用局部内部类的方法
    }

}
