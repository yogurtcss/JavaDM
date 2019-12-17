package pers.yo.adv1.demo12_Collection;

/* 泛型，Generic
* 泛型：此集合中的所有元素，全是统一的XX引用类型；
* 注意，泛型只能是引用类型，不能是 基本数据类型！！
*
* 数组的长度是不可变的，但ArrayList集合的长度是可以任意变化的
* 对于ArrayList来说，它(旁边)有一个尖括号<E> 表示泛型
* 若希望向ArrayList中存储 基本数据类型的数据，
* 则此时的泛型必须为 基本数据类型所对应的“包装类”
*
* 若集合定义时不使用泛型，则默认赋予的泛型为Object型，可存储任意类型的数据；但这不安全，会引发异常
*
* ----------------------------------------------------------------------------
* 定义含有泛型的方法：泛型定义在方法的 【修饰符】与【返回值类型】 之间！！格式如下
* 修饰符 <泛型M> 返回值类型 方法名( 泛型M 形参m1， 泛型M 形参m2， ... ){
*     //...
* }
*
* 含有泛型的方法method，在它被调用时，确定此泛型的数据类型：
* 传入什么类型的数据，泛型就是什么类型
*
* ----------------------------------------------------------------------------
* 含有泛型的接口，即格式：
* 修饰符 interface 接口名 <声明自定义泛型T> { //在接口上定义泛型
*    //...
*    //如 public abstract T getVar() ;   //定义抽象方法，抽象方法的【返回值类型】就是泛型类型 【注意，是返回值类型！！没有尖括号！！】
* }
*
* 泛型接口定义完成之后，就要定义此接口的实现子类；
* 【定义泛型接口的实现子类时】，有两种方法：
* 法1：定义时，在实现子类的【类名后】声明【与此接口中相同的泛型标识 T】，格式为
*     public  class  类名<泛型T>   implements  (泛型的)接口 <泛型T> {
*         //...
*     }
*
* 法2：定义时，【在子类实现的接口名后】 用具体的类型 代替 原本泛型T ； //即直接给出具体的类型
*    public  class  类名  implements   (泛型的)接口 <在此给出具体的类型String> { //在原本泛型T处，给出具体的类型，如String型
*         //..
*    }
*
* ----------------------------------------------------------------------------
* 泛型通配符 <?>
* 当使用泛型类或泛型接口时，待传递数据的 泛型类型不确定，可用 <?>表示
* 若使用了泛型通配符 <?>，则只能使用Object类中的共性方法，具体的类的方法无法使用；
*
* 泛型的上限限定： ? extends E  表示使用的泛型只能是E类型子类或本身
* 泛型的下限限定： ? super E    表示使用的泛型只能是E类型父类或本身
*  */



import pers.yo.adv1.demo12_Collection.GenericClass;
import pers.yo.adv1.demo12_Collection.GenericMethod;
import pers.yo.adv1.demo12_Collection.GenericClass1; //泛型接口的实现类


public class Demo03Generic {
    public static void main(String[] args) {
        //创建GenericClass实例对象，泛型使用Integer类型
        GenericClass<Integer> gc = new GenericClass<>(); //右边尖括号内可以为空
        gc.setName(1);
        Integer in1 = gc.getName();
        System.out.println( in1 );
        System.out.println( "-----------------------" );

        GenericMethod gm = new GenericMethod();
        gm.method( 1 ); //有泛型的方法method，在它被调用时，确定此泛型的数据类型：传入什么类型的数据，泛型就是什么类型
        gm.method( "字符串！" );
        System.out.println( "-----------------------" );

        GenericClass1<String> gc1 = new GenericClass1<>(); //定义实现子类时，给出具体的泛型类型，在这里为String
        String s1 = gc1.methodT( "哈哈哈" );
        gc1.method();


    }
}
