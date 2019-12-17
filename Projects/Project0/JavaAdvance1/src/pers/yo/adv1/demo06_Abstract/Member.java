package pers.yo.adv1.demo06_Abstract;

import java.util.ArrayList;
import java.util.Random;

public class Member extends User {
    public Member() {
    }

    public Member(String name, int money) {
        super(name, money);
    }

    public void receive( ArrayList<Integer> list ){
        //从多个红包中随便抓一个，拿给我自己

        /* 复习Random类的使用 1.导包
        * Random r = new Random(); //2.创建Random类的实例对象r
        * 3.调用实例对象r的方法
        * int num = r.nextInt(); //获取一个随机的int数字
        * int num1 = r.nextInt(3); //传入一个参数x，表示随机的范围是 [0,x) 的左闭右开区间
        *
        * new Random().nextInt( list.size() ); //语句连续调用，把我看傻了！
        * 创建Random的实例对象，调用nextInt( 数组列表的元素个数 )
        * 表示获取一个 [0, 数组列表的元素个数 )  左闭右开区间
        * 即 [ 0, 数组列表的元素个数-1 ] 的整型随机数，即任意一个数组的下标！
        *
        *  */
        int index = new Random().nextInt( list.size() );
        //根据index得知某个红包delta 已被领了，从原数组中删除remove它
        int delta = list.remove( index ); //delta 即Δ 变化量
        //当前成员本来有多少钱
        int money = super.getMoney();
        //领取红包后，设置余额
        super.setMoney( money+delta );
    }

}
