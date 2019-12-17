package per.yo.basic1.demo02;

import per.yo.basic1.demo02.Phone;

public class Demo03PhoneParam {
    //普通方法，非成员方法，故此时要加static
    public static void method( Phone p ){ //任何数据类型都能作为形参，在这里以对象作为形参
        /* 传递给method的，其实就是(对象的)地址值
        * 方法利用此地址值，到堆中找到此对象，然后读取对象的成员变量、成员方法
        *  */
        System.out.println( p.brand );
        System.out.println( p.price );
        System.out.println( p.color );
    }

    public static void main( String[] args ){
        Phone p1 = new Phone();
        p1.brand = "xiaomi";
        p1.price = 1688;
        p1.color = "绿绿";
        method( p1 );
    }
}
