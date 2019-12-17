package pers.yo.adv1.demo06_Abstract;

public class User {

    private String name; //姓名
    private int money; //余额
    /* 关于成员变量的继承，父类的任何成员变量都是会被子类继承下去的，
    * 即：子类对象确实拥有父类对象中所有的属性和方法，
    * 但是父类对象中的私有属性和方法，子类是无法访问到的：只是拥有，但不能直接使用。
    * 就像有些东西你可能拥有，但是你并不能使用。
    *
    * 这些继承下来的私有成员虽对子类来说不可见，
    * 但子类仍然可以用父类的函数(如super.get方法 读取、super.set方法 设置等)操作 继承下来的私有成员
    *  */


    /* 快速生成 无参构造、有参构造、getter和setter的快捷键
    * alt + Insert键，然后选择相应选项即可
    * 快哉！
    *  */

    public User() { //无参构造
    }

    public User(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney(){
        return money;
    }

    public void setMoney( int money ){
        this.money = money;
    }

    public void show(){
        System.out.println( "我叫："+name+"，余额为："+money );
    }

}
