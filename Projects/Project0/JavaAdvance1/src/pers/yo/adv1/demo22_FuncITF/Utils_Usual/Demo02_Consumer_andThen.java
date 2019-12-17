package pers.yo.adv1.demo22_FuncITF.Utils_Usual;

/*
   Consumer接口的默认方法 void accept(T t);
   作用:需要两个Consumer接口,可以把两个Consumer接口组合到一起,在对数据进行消费
   因为没有返回值，所以：当多个Consumer接口实现类的实例对象con1、con2先后消费同一变量时，
   con1、con2消费的结果 互相独立、互不影响

   例如:
    Consumer<String> con1
    Consumer<String> con2
    String s = "hello";
    con1.accept(s);
    con2.accept(s);
    连接两个Consumer接口  再进行消费
    con1.andThen(con2).accept(s); 谁写前边谁先消费
    因为没有返回值，所以：当多个Consumer接口实现类的实例对象con1、con2先后消费同一变量时，
    con1、con2消费的结果 互相独立、互不影响
*/
import java.util.function.Consumer;

public class Demo02_Consumer_andThen {
    //传入了两个函数式接口，则对应地需传入两个lambda表达式
    public static void method( String s, Consumer<String> con1, Consumer<String> con2 ){
//        con1.accept( s );
//        con2.accept( s );
        //逻辑：con1消费完s后，紧接着con2消费s
        con1.andThen(con2).accept(s);
    }

    public static void main(String[] args) {
        method(
                "HellO！",
                //con1的消费方式：转换为大写
                s -> System.out.println( s.toUpperCase()+"+后缀CON1，"+"con1消费的结果(result)，不会传给con2进行消费！" ),
                //con2的消费方式：转换为小写。注：两者消费的结果【互相独立，互不影响】
                s -> System.out.println( s.toLowerCase()+"con2消费的还是传入的形参s，而不是 con1消费的结果嗷！" )
        );
    }
}
