package per.yo.basic1.demo02;

import per.yo.basic1.demo02.Phone;

public class Demo04PhoneReturn {

    //方法的返回值为 Phone类型的，实际返回的就是 Phone实例对象的地址值
    public static Phone getPhone(){
        Phone p = new Phone();
        p.brand = "xiaomi";
        p.color = "heihei";
        p.price = 111;
        return p; //把 类Phone的实例对象p返回出去；实际返回的是实例对象的地址值
        /* 在外部调用此方法后，取得此实例对象的地址值，然后可根据此地址值在内存中找到相应对象，
        * 然后操作它的成员变量、成员方法
        *  */
    };

    public static void main( String[] args ){
        Phone p1 = getPhone();
        System.out.println( p1.brand );
        System.out.println( p1.color );
        System.out.println( p1.price );
    }
}
