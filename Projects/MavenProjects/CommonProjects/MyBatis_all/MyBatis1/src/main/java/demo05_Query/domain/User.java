package demo05_Query.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
    /* 属性名终于和数据库db1中user表的列名相同了
    * 不用在配置文件xml中写 <resultMap />标签了
    *  */
    private Integer id;
    private String username;
    private String address;
    private String sex;
    private Date birthday;

    /* 一对多关系映射：主表实体应该包含从表实体的【集合】引用
    * 简单理解：一对多；“多者” 就放进集合中 与 “一” 者对应
    *  */
    private List<Account> accounts;

    //多对多的关系映射：一个用户可以具备多个角色
    private List<Role> roles;


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }



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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", accounts=" + accounts +
                ", roles=" + roles +
                '}';
    }
}
