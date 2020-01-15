package demo05_Query.domain;

public class Account {
    private Integer id;
    private Integer uid;
    private Double money;

    /* 从表实体应该包含一个主表实体的对象引用
    * 一对一：一个对应一个喽！
    *  */
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
