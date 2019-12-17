package pers.yo.adv1.demo11_API2;

import java.util.Objects;

public class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean equals( Object obj ){
        if( obj==this ){ //若传入的是自己，则直接返回true
            return true;
        }

        if( obj==null ){ //若传入的是null，则直接返回false
            return false;
        }

        if( obj instanceof Person ){ //如果实例对象obj 是Person类 的实例对象，那么就可以进行向下转型为Person类
            Person p = (Person)obj; //将obj向下转型为Person类，并实例化为p，这样就拥有成员变量age和name，就可以比较了
            /* 基本数据类型，如int，则直接用==比较
            *
            * 引用类型，最经典的就是比较字符串String型！！ 【重点，我的疑惑之处：在这儿 字符串比较 又用了equals，是否“递归调用”？】
            *
            * 因为String已经重写了Object的equals()方法，直接就是比较字符串的内容了，
            * 所以，比较字符串String型，直接 字符串1.equals(字符串2)，即可，【不用==号！！】
            *
            * 注：在这里使用字符串的equals，不是【递归】，不是【递归】！！【不是递归调用本类重写的equals方法！！】
            * 只是 字符串调用了String重写的Object的equals()方法而已
            *
            * (this.name字符串).equals.(p.name字符串) //这是调用了 字符串调用了String重写的Object的equals()方法而已！！不是【递归】！！
            *
            *  */
            boolean b = (this.age==p.age)&&( this.name.equals(p.name) ); //这是调用了 字符串调用了String重写的Object的equals()方法而已！！不是【递归】！！
            return b;
        }
        else{ //如果obj不是Person类的实例对象，则实际返回 false
            return false;
        }

    };


}
