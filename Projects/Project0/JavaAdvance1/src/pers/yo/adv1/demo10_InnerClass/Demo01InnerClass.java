package pers.yo.adv1.demo10_InnerClass;

/* 若一个事物的内部包含另一个事物，那么这就是一个类内部包含另一个类
* 如：身体和心脏的关系；汽车与发动机的关系
*
* 分类：
* 1.成员内部类——类中 定义的类。格式：
* 修饰符 class 外部类名{
*      修饰符 class 内部类名{
*           ...
*      }
* }
*
* 注意，“内”用“外”，随意访问；
* “外”用“内”，先套娃(外部类方法中嵌套 调用内部类实例对象的方法)，后调用(外部类实例对象的方法)
* 即：在外部类方法中：(1)创建内部类实例对象inner，然后调用实例对象inner的方法(即 在外部类中 调用“内”实例对象的方法)
* (2)然后在 外部(如main方法中)，新建外部类的实例outer，调用外部类实例outer的方法(这样间接 调用“内”实例对象的方法，即套娃 )
*
* 如何使用成员内部类？
* 1.间接方式：在外部类的方法A当中，使用内部类；然后main方法只是调用外部类的方法A
* 2.直接方式，利用公式：
* 大前提公式：【类名】 对象名 = 【 new 类名()】
* 要用到内部类，首先必须先出现外部类嗷！(先有身体后装着心脏) 具体公式：
* 【外部类.内部类】 对象名  = 【new 外部类名(). new 内部类名()】
* 外.内 = new 外(). new 内()
*
*
* 2.局部内部类——方法中 定义的类
* 局部，只有当前所属的方法才能使用此“局部内部类”，在此方法外则不能用了；格式如下
*
* 修饰符 class 外部类名{
*     修饰符 返回值类型 外部类的方法名(参数列表){
*          class 局部内部类名{ //局部内部类的定义【没有权限修饰符public、private前缀嗷！！】
*           ... //局部内部类的定义 不能有 public、private、protected 修饰，好理解，因为局部变量也是没有这些修饰符的。
*           }
*
*     }
* }
*
* ▲ 匿名内部类：没有名字的(特殊的)局部内部类。
* 正因为没有名字，所以匿名内部类只能使用一次，
* 它通常用来简化代码编写。但使用匿名内部类还有前提条件：
* 必须继承一个父类或实现一个接口，但最多只能继承一个父类，或最多只能实现一个接口。
*
* 好处：只调用一次 某接口/某抽象类 的方法，而不用特意创建一个新的实现类/或实现子类
*
* 匿名内部类格式：本质上就是实例化对象的过程
* 右边是 new一个匿名内部类，左边就是 实例化的对象
*
* 接口名 变量名(实现对象名) = new 接口名() { //此花括号的实现类就是一个匿名内部类
*    //覆盖重写此接口的所有抽象方法
*   //因为要实现接口，所以匿名内部类不能是抽象类！！(抽象类中：只定义抽象方法而不实现；而实现接口的类 是必须要实现接口的所有抽象方法！)
*   //匿名内部类不能定义构造器（构造方法），因为匿名内部类没有类名，所以无法定义构造器(构造方法)，
* }
*
* 2019-11-20 15:16:20 注：使用匿名内部类方式创建线程
*
* 父类名 变量名(实例对象名) = new 父类名(){ //此花括号的实现类就是一个匿名内部类
*    //...
* }
*
*  */


import pers.yo.adv1.demo10_InnerClass.Body;
import pers.yo.adv1.demo10_InnerClass.Outer;
import pers.yo.adv1.demo10_InnerClass.ITF_anonymous;
import pers.yo.adv1.demo10_InnerClass.Abs_anonymous;


public class Demo01InnerClass {
    public static void main(String[] args) {
//        Body b = new Body();
//        b.setName( "身体" );
//        b.methodBody();
        Outer.Inner oi = new Outer().new Inner(); //利用公式： 外.内 = new 外(). new 内()
        oi.methodInner();
        System.out.println( "--------------------" );

        Outer o = new Outer();
        o.methodOuter(); //可套娃：里面嵌套调用 局部内部类的方法
        System.out.println( "---------------------" );

        //匿名 anonymous，我自定义缩写为anyms
        ITF_anonymous itf_anyms = new ITF_anonymous(){
            public void show(){
                System.out.println( "我是此接口ITF_anonymous的匿名内部类，我实现此接口的抽象方法show()啦!" );
            }
        };
        itf_anyms.show();

        Abs_anonymous abs = new Abs_anonymous(){
            public void methodAbs(){
                System.out.println( "我是抽象方法Abs_anonymous的匿名内部类，我实现此抽象类的抽象方法show()啦!" );
            }
        };
        abs.methodAbs();
    }

}
