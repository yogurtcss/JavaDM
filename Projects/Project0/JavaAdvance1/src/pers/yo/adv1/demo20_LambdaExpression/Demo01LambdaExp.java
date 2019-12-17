package pers.yo.adv1.demo20_LambdaExpression;

/* 函数式编程思想：只要能获取到结果，谁去做的、怎么做的都不重要；重视的是结果，不重视过程
面向对象的思想：做一件事，找一个能解决这件事情的对象，调用对象的方法，完成事情

-----------------------------------
Lambda(Lambda是希腊字母λ的英文名称)表达式：可作为回调函数
一、定义：将 【匿名函数复制给变量的简写方式】的函数 称为 lambda 表达式
Lambda 允许把函数作为一个方法的参数（函数作为“参数”传递进方法中）。

注：
1.ES6 箭头函数就是lambda表达式，提供了更简洁的 function 定义方式；
2.Lambda 表达式本身就是一个接口的实现；
3.每个 Lambda 表达式都能隐式地赋值给函数式接口；

二、格式(类似 箭头函数的格式) 两种固定格式：
( 参数类型 参数名1, 参数类型 参数名2, ...,  ) ->  单个表达式expression;  //单个表达式，不用花括号

( 参数类型 参数名1, 参数类型 参数名2, ...,  ) ->  { 代码块：函数式接口抽象方法的实现逻辑 };
//一般的代码块，需要花括号，里面手动return返回值；


简化格式
1.参数类型省略–绝大多数情况，编译器都可以从上下文环境中推断出 lambda 表达式的参数类型

2.单参数语法：当lambda表达式的参数个数只有一个，可以省略形参的小括号
param1 -> {
  statement1;
  statement2;
  //.............
  return statementM;
}

3.单语句写法：当lambda表达式只包含一条语句(如打印输出语句)时，可以省略大括号、return 和语句结尾的分号
param1 -> statement;    //如打印语句


----------------------------------------------------------
▲ 函数式接口：只包含一个抽象方法声明的接口；
根据定义，函数式接口只能有一个抽象方法！！不能存在多于一个的抽象方法！！

二、使用：凡是（java8 以上）函数式接口都可以尽量使用 lambda 表达式，每个 Lambda 表达式都能隐式地赋值给函数式接口
▲ 只要找到 Java 中【函数式接口】的皆可以放出 lambda表达式的大招。

目前已知的最常用的【函数式接口】有：
java.lang.Runnable
java.util.concurrent.Callable

*  */

public class Demo01LambdaExp {

    public static void invokeCook( Cook cook ){ //全局的静态方法
        cook.makeFood(); //调用makeFood()的语句：输出吃饭辣！
    }

    public static void invokeCalc( int a, int b, Calculator calculator ){
        int rst = calculator.calc( a,b );
        System.out.println( "结果是："+ rst );
    }


    public static void main(String[] args) {
        //lambda表达式(箭头函数)，愿称之为绝活！！
//        new Thread( ()-> System.out.println(Thread.currentThread().getName()+" 线程执行辣！我是lambda表达式整出来的嗷！") ).start();

        invokeCook( ()-> System.out.println("吃饭啦！") );


        invokeCalc( 1,1, new Calculator(){
            public int calc( int a, int b ){
                return a+b;
            }
        } );

        invokeCalc( 1,1, (a,b)->a+b );
        //使用方法引用
        invokeCalc( 1,1, Integer::sum );

    }
}
