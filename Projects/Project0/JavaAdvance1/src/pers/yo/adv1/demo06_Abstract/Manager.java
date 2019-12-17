package pers.yo.adv1.demo06_Abstract;

import pers.yo.adv1.demo06_Abstract.User;
import java.util.ArrayList;

public class Manager extends User {
//    父类中：private String name; //姓名
//    父类中：private int money; //余额
    /* 关于成员变量的继承，父类的任何成员变量都是会被子类继承下去的，
     * 即：子类对象确实拥有父类对象中所有的属性和方法，
     * 但是父类对象中的私有属性和方法，子类是无法访问到的：只是拥有，但不能直接使用。
     * 就像有些东西你可能拥有，但是你并不能使用。
     *
     * 这些继承下来的私有成员虽对子类来说不可见，
     * 但子类仍然可以用父类的函数(如super.get方法 读取、super.set方法 设置等)操作 继承下来的私有成员
     *
     * 子类通过  super.父类的get方法 来读取：子类的 继承下来的私有变量、方法
     * 子类通过  super.父类的set方法 来设置：子类的 继承下来的私有变量、方法
     *
     *  */


    public Manager() {
    }

    public Manager(String name, int money) {
        super(name, money);
    }

    public ArrayList<Integer> send( int totalMoney, int count ){
        /* 存储若干红包的金额的 列表，等待成员来拿红包
        * 其中的元素为 红包的金额
        *  */
        ArrayList<Integer> redList = new ArrayList<>();

        int leftMoney = super.getMoney(); //获取子类中private money的值，即群主当前余额
        if( leftMoney<totalMoney ){
            System.out.println( "余额不足喽" );
            return redList;
        }

        super.setMoney( leftMoney-totalMoney ); //扣钱，修改余额

        //发红包 需要平均拆分成为count份
        int avg = totalMoney / count;
        int mod = totalMoney % count; //余数，余下的零头

        for( int i=0; i<count-1; i++ ){ //把avg钱塞进了 count-1个红包中，因为最后把零头mod塞进最后一个红包中
            redList.add( avg ); //包红包的过程
        };

        int last = avg + mod; //最后一个红包的钱数
        redList.add( last ); //包最后一个红包

        return redList;
    }
}
