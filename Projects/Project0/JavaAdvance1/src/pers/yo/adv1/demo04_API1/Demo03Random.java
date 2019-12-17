package pers.yo.adv1.demo04_API1;

import java.util.Random; //1.导包

public class Demo03Random {
    public static void main(String[] args) {
        Random r = new Random(); //2.创建Random类的实例对象r
        //3.调用实例对象r的方法
        int num = r.nextInt(); //获取一个随机的int数字
        int num1 = r.nextInt(3); //传入一个参数x，表示随机的范围是 [0,x) 的左闭右开区间
        System.out.println( num );
    }
}
