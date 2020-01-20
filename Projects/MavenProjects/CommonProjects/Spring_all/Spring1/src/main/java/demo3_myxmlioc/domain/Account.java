package demo3_myxmlioc.domain;


import java.io.Serializable;

/**
 * 账户的实体类
 */
public class Account implements Serializable {

    private Integer id;
    private String name;
    private Float money;

    /* 如果一个类中没有构造方法，那么编译器会为类加上一个默认的构造方法。
    默认构造方法格式如下：
    public 类名 () {
    }

    如果手动添加了一个构造方法，那么默认的“系统赠送的” 无参构造方法就会消失。
    建议代码中将无参构造方法也顺便写出来！！ 不然 <bean />标签用得着！
    *  */

    //手动提供一个无参构造方法
    public Account() {
    }
    /* 我手动加了这个带参构造方法，那么默认的“系统赠送的” 无参构造方法就会消失。
    * 那么 我需要手动提供一个无参构造方法嗷
    *  */
    public Account(Integer id, String name, Float money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
