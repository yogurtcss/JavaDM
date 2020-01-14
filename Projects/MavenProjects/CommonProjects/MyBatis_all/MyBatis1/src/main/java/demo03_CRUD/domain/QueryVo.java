package demo03_CRUD.domain;

/* 2020-01-14 19:51:52
QueryVo.java中：

ViewObject 表现层对象，简称 VO对象

POJO普通对象中的属性(成员变量)一般为 基本数据类型，
而VO表现层对象中的属性(成员变量) 可以是引用类型( --成员变量是 某个实例对象)

▲ 当查询条件中有多个固定数量的查询条件，可以通过传入一个VO对象来进行操作，
写法如：parameterType="cn.it.pojo.QueryVo--VO的全类名"

▲ sql语句中的传参：来自 VO属性(-这是一个对象) 的属性(-这终于是基本数据类型了)
写法如：#{user.sex}
--第一层 #{user}      表示getUser ——在VO对象中，get到User这个属性(-这是一个对象)；
--第二层 #{user.sex}  表示取到User对象后，继续getSex ——在User对象中，get到Sex这个属性(-这终于是基本数据类型了)

#{user.username}也是同理分析的

*  */

public class QueryVo {
    /* POJO普通对象中的属性(成员变量)一般为 基本数据类型，
    而VO表现层对象中的属性(成员变量) 可以是引用类型( --成员变量是 某个实例对象)
    *  */

    private User user; //VO表现层对象中的属性(成员变量) 可以是引用类型( --成员变量是 某个实例对象)

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
