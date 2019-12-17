package pers.yo.adv1.demo11_API2;


/* Object.toString()：返回该对象的字符串表示。
* 通常，toString 方法会返回一个 “以文本方式表示” 此对象的字符串。
* 结果应是一个简明但易于读懂的信息表达式。建议所有子类都重写此方法。
*
*
* -------------------------------------------------------------------------------------------------
* Object类的equals()方法：判断其他某个实例对象是否与此实例对象“相等”(两对象的地址值是否相等)，返回boolean值
* 注意，是已知的调用者 与另一个实例对象比较嗷
*
* 假设已有Object实例对象分别为 obj1、obj2
* obj1.equals(obj2); //判断 我调用者(实例对象obj1) 与 另一个实例对象obj2 与 的地址值是否相等
* 返回判断结果：boolean值
*
* 若obj1、obj2是基本数据类型，则比较“值”
* 若obj1、obj2是引用类型，则比较“地址值”
*
* Object实例对象.equals的源码:(可重写之，以实现自定义的功能：判断 成员变量)
* public boolean equals( Object obj ){
*    return( this==obj ); //this指的是 调用此equals方法的实例对象(在这里为 obj1)；而传入的形参obj指的是 另一个待比较的实例对象obj2嗷
* }
*
*
*  */

import pers.yo.adv1.demo11_API2.Person;

import java.util.Objects; //当需要使用 Objects.静态方法equals() 进行比较对象时，引入此包

public class Demo01Obj {
    public static void main(String[] args) {
        Person obj1 = new Person( "小明明", 10 );
        Person obj2 = new Person( "小明0.0",10 );
        System.out.println( obj1.equals(obj2) ); //Person类 重写了 Object的equals()方法。尤其注意【字符串】的比较！！
        /* 注：在这里使用字符串的equals，不是【递归】，不是【递归】！！【不是递归调用本类重写的equals方法！！】
        * 只是 字符串调用了String重写的Object的equals()方法而已
        *  */

        /* Objects.equals( obj1, obj2 ) 注意，【Objects有复数s！！】
        * 【Objects有复数s！！】【Objects有复数s！！】【Objects有复数s！！】
        * 直接比较两个对象，返回boolean值 【防止空指针异常】
        *  */
        System.out.println( Objects.equals( obj1,obj2 ) );

    }
}
