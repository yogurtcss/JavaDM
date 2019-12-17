package pers.yo.adv1.demo12_Collection;

import pers.yo.adv1.demo12_Collection.GenericITF;
public class GenericClass1 <T> implements GenericITF<T> { //定义 泛型接口的实现子类，法一
    public T methodT( T t ){
        System.out.println( "我是methodT！返回泛型T的实例对象！"+t );
        return t;
    }
    public void method(){
        System.out.println( "妹有！" );
    }
}
