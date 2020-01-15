package demo04_DynamicSQL.domain;

import java.util.List;

public class QueryVo {
    //QueryVo的属性 可以是一个 实体类的实例对象user嗷！
    private User user;
    private List<Integer> id;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }
}
