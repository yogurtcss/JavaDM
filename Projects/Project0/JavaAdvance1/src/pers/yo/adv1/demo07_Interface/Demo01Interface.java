package pers.yo.adv1.demo07_Interface;

/* 接口就是多个类的公共规范
* 接口是一种引用数据类型，最重要的内容就是其中的 抽象方法
*
* 接口格式
* public interface 接口名{
*   //接口内容：可以有常量、抽象方法、默认方法、静态方法；
*   //接口内容中的 私有方法 为Java9新增内容，仅Java9可用
* }
*
* 使用接口：
* 1.接口不能直接 “new 接口名” 使用！！必须有一个 “实现类” 来 “实现”该接口；(与抽象类的使用 同理)
*   格式： public class 实现类的名称 implements 接口名{  //implement 即实现、实行
*               ...//实现的内容
*         }
*
* 2.接口的 “实现类” 必须覆盖重写(即 实现) 接口中的【所有的抽象方法】
* 实现：去掉abstract关键字，加上花括号方法体
*
* 3.创建类的实例对象，进行使用
*
*
*  */

import pers.yo.adv1.demo07_Interface.ImplementClass;
import pers.yo.adv1.demo07_Interface.ITF_static;
import pers.yo.adv1.demo07_Interface.ITF_const;

public class Demo01Interface {
    public static void main(String[] args) {
        ImplementClass impC = new ImplementClass();
        impC.methodAbs1(); //调用 实现的抽象方法
        impC.methodDefault(); //通过实现类的实例对象 调用 原接口已有的默认方法
        ITF_static.methodStatic(); //直接通过 接口名.静态方法名 调用它的 静态方法
        System.out.println( ITF_const.NUM_MY_CLASS ); //直接通过 接口名.常量 调用它的 常量
    }
}
