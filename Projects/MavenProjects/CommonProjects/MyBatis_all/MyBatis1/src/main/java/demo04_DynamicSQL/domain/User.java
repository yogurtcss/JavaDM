package demo04_DynamicSQL.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * 2020-01-15 09:16:14
 * 我佛了，这里的 User的列名还是 与数据库db1中user表的列名不一致！
 * 要手动在 UserDao.xml 中 写 <resultMap />标签了
 */
public class User implements Serializable {
    /* 2020-01-15 09:49:35
    * 注意，这里的基本数据类型全用 包装类了！
    * 在 UserDao.xml配置文件中，注意 parameterType="包装类全类名"！！
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
