package pers.yo.adv1.demo07_Interface;

public interface ITF_default {
    public default void methodDefault(){
        System.out.println( "我是接口的默认方法，通过实现类的实例对象调用的！" );
    }
}
