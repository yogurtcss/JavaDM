package per.yo.basic1.demo02;

import per.yo.basic1.demo02.Student; //1导包

/* 根据类，创建对象来使用
* 1.导包(导入此类)
*   import 包名称.类名称;
*   如import per.yo.basic1.demo02.Student
*   若 当前文件与 需导入的包(类) 处于同一个包下，可则以省略导包语句不写
*
* 2.创建实例对象
*   类名 对象名 = new 类名();
*
* 3.使用，使用 .号 调用
* 使用成员变量：对象名.它的成员变量名
* 使用成员方法：对象名.它的成员方法名(参数)；务必加上小括号，表示调用之
*  */

public class Demo01Student {
    public static void main( String[] args ){
        Student stu = new Student();
        System.out.println( stu.age ); //0，因为age为整型int
        System.out.println( stu.name ); //null，因为name为String型，即引用型

        /* 变量初始化时，系统自动赋予的默认值
        * int型：默认为0
        * float型：默认为0.0
        * 字符型：默认为'\u0000'
        * 布尔型：默认为 false
        * 引用类型(如String、数组等)：默认为null
        *
        * 注：String是char类型的数组，而数组是对象，所以String也是对象
        * 所以String是引用型的
        *  */
        stu.name = "小明明👴";
        stu.age = 188;
        System.out.println( stu.name );
        System.out.println( stu.age );
        stu.eat();
        stu.sleep();
        stu.study();
    }
}
