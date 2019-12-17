package per.yo.basic1.demo02;

public class Demo02Phone {
    public static void main( String[] args ){ //总是容易忘记main方法，我佛了
        Phone p1 = new Phone();
        /* 对某变量全部、统一快速重命名
        * 把光标放在此变量上，按下 shift + F6，
        * 然后对其重命名，即可实现 统一快速重命名
        *  */
        p1.brand = "xiaomi";
        p1.price = 2699;
        p1.color = "black";
        p1.call("哈哈");
        p1.sendMsg();
        System.out.println("----------------");

        //CellPhone p2 = new CellPhone();
        Phone p2 = p1; //将cp1当中保存的对象地址值赋值给cp2
        //所以cp2与cp1指向堆中同一个内存地址值，即 指向堆中同一个对象
        p2.brand = "xiaomi";
        p2.price = 2699;
        p2.color = "black";
        p2.call("哈哈");
        p2.sendMsg();
        System.out.println("----------------");

    }
}
