package pers.yo.adv1.demo12_Collection;

import pers.yo.adv1.demo12_Collection.GenericITF;
public class GenericClass2 implements GenericITF<String> {//定义 泛型接口的实现子类 法二，直接在接口名后给出具体的类型
    public void method(){
        System.out.println( "aaa" );
    }
}
