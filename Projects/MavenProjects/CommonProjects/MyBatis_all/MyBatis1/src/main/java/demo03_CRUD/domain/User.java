package demo03_CRUD.domain;

import java.util.Date;

public class User {
    /* 2020-01-14 09:48:01
    在 映射文件 UserDao.xml中：
    1.当实体类中的字段与数据库表中的字段【完全相同】时，可以将 resultMap 标签中的关联关系忽略不写。
    何为相同？大小写完全相同、下划线完全相同 如下：

    数据库db1的user表中的字段                    User实体类中的属性
    user                                       user
    id                                         id
    user_name                                  user_name


    2.当实体类中的字段与数据库表中的字段不相同时，就需要在 resultMap 标签中
    将实体类字段与数据库字段一一进行关联映射，或者开启驼峰规则，让它自动转换。

    而我的数据库db1中user表的字段为：id、username、address、sex、birthday
    没有前缀 user！！所以要用到 resultMap 标签了！
    *  */

    private Integer userId;
    private String userName;
    private String userAddress;
    private String userSex;
    private Date userBirthday;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userBirthday=" + userBirthday +
                '}';
    }
}
