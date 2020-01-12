package pers.yo.mybatis1.domain;

import java.io.Serializable;
import java.util.Date;

/* 2020-01-12 09:35:02
序列化
序列化是 Java 中的一个非常重要的特性，通过序列化机制，我们可以将 Java 的对象变成流，或者存储在硬盘中，或者通过网络传输给网络的其他用户。
而序列化在 RMI，EJB 中都有应用，可以说是构成 J2EE 的一个重要的技术。

一、Serializable 接口
如果想让一个类可被序列化，那么这个类必须实现 Serializable 接口。
这个接口本身没有任何方法和属性，它的作用只是为了标示一个类可以被序列化，这一个特性好像在 Java 里用的比较多，比如克隆也是采用了相同的机制。

实现Serializable 接口的实体类中，可选的成员变量（属性） serialVersionUID*
这个属性是一个私有的静态 final 属性，一般刚接触序列化的人会忽略这个属性，因为这个属性不是必须的。
这个属性主要是为了保证一个可序列化类在不同的 Java 编译器中，能保证相同的版本号，
这样保证当这个类在不同的 JVM 中进行序列化与反序列化时不会出现 InvalidClassException 异常。

*  */


public class User implements Serializable {
    //要求：实体类中的属性(成员变量)，与数据库db1中的表user的列名保持一致
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
