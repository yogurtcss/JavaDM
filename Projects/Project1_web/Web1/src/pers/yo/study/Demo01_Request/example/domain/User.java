package pers.yo.study.Demo01_Request.example.domain;
/* domain 域
* 这是用户的实体类
* 从数据库db1下的表user取出的数据：
* id、username、password 被封装为User实例对象的成员属性
*
*  */

public class User {
//    private int id;
//    private String username;
//    private String password;

    public int id;
    public String username;
    public String password;

    public User() {
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
