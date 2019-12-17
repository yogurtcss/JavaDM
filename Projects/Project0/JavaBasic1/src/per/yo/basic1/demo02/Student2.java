package per.yo.basic1.demo02;

/* 构造方法：专门用来创建实例对象的方法
* 之前所使用通过 “new 类名” 来创建实例对象时，就是在调用构造方法
*
* 构造方法，格式：
* public 类名( 参数类型 参数名 ){
*    方法体
* }
*
* 注意，1.构造方法名必须与所在的类名(大小写)完全一致
* 2.构造方法不要写返回值类型，也不要写void
* 3.构造方法没有return
* 4.当没有自定义构造方法时，系统默认生成一个啥事都不干的构造方法
* 5.[重点！]构造方法可以重载嗷
*  */

/* 一个标准的类——Java Bean(Bean即 豆子)，具有以下4个组成部分
* 1.所有的成员变量都使用private关键字
* 2.为每一个成员变量编写一对Getter/Setter方法
* 3.编写一个无参数的构造方法
* 4.编写一个全参数(即有参数)的构造方法
*
*  */


public class Student2 {
    private String name;
    private int age;

    public Student2(){ //构造方法：此方法名与类名完全一致，且不写返回值类型
        System.out.println( "无参构造方法执行啦！" );
    }
    public Student2( String name, int age ){
        System.out.println( "有参构造方法执行啦！" );
        this.name = name;
        this.age = age;
        //有大二内味儿了
    }

    public void setName( String name ){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

}
