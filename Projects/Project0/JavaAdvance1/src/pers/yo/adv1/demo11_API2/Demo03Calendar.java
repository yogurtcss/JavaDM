package pers.yo.adv1.demo11_API2;

/* java.util.Calendar 日历类
*
* Calendar类是一个抽象类，里面提供了许多操作日历字段的方法
* Calendar无法直接new Calendar()创建实例！！
* 【正确】使用Calendar的静态方法 Calendar.getInstance()，返回Calendar类的一个实例对象。格式：
* Calendar c = Calendar.getInstance(); //多态。为什么？
* 注意看 左父右子：左边Calendar是父类，右边的 Calendar.getInstance()是实例化的子类对象，上转型。
*
* 同时，Calendar.getInstance() 使用默认时区和语言环境获得一个日历
*  */

import java.util.Calendar;


public class Demo03Calendar {
    public static void main(String[] args) {
        //观察Calendar类创建实例对象的过程：左父右子，上转型，即多态
        Calendar c = Calendar.getInstance(); //使用默认时区和语言环境获得一个日历
        System.out.println( c );//打印结果不是地址值，说明Calendar类重写了toString方法。
        //打印的结果是 默认时区和语言环境的一个日历
        System.out.println( "------------------------------" );

        /* Calendar实例.get( field传入指定的日历字段-全大写-如Calendar.YEAR, Calendar.MONTH )
        * 返回日历字段代表的具体的值
        *
        * Calendar实例.set( field传入指定的日历字段, 具体的值value )
        * field传入指定的日历字段-如Calendar.YEAR, Calendar.MONTH
        * 具体的值value：如2019、2020
        *
        * Calendar实例.set( 年，月，日 )
        *  */

        c.set( Calendar.YEAR, 2009 ); //设置年为2009
        c.set( Calendar.MONTH, 1 ); //设置月为1月
        c.set( Calendar.DATE, 2 ); //设置日为2日

        c.set( 2020,12,13 );

        c.add( Calendar.YEAR, 2 ); //把c的“年字段”增加2年

        System.out.println( c.get(Calendar.YEAR) );
        System.out.println( c.get(Calendar.MONTH) );
        System.out.println( c.get(Calendar.DATE) );




    }
}
