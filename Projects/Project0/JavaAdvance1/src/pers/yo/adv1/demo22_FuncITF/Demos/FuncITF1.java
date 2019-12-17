package pers.yo.adv1.demo22_FuncITF.Demos;

/* 早在lambda表达式中出现过的
▲ 函数式接口：只包含一个抽象方法声明的接口；
根据定义，函数式接口只能有一个抽象方法！！不能存在多于一个的抽象方法！！
接口中可以包含其他【非抽象】的方法( 默认default、静态static、私有private )

函数式接口：一般可以作为方法的参数、返回值类型


*  */


public interface FuncITF1 {
    public abstract void method(); //定义一个抽象方法
}
