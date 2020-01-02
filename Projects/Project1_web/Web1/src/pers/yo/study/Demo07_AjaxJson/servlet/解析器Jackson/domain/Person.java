package pers.yo.study.Demo07_AjaxJson.servlet.解析器Jackson.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Person {
    private String name;
    private int age;
    private String gender;

    /* @JsonIgnore：排除属性。
    *  json 序列化时将 java bean 中的一些属性忽略掉，序列化和反序列化都受影响
    *  */

    /* 对属性值进行格式化
    * 此注解用于属性或者方法上（最好是属性上），
    * 可以方便的把 Date 类型直接转化为我们想要的模式，
    * 比如 @JsonFormat (pattern = “yyyy-MM-dd HH-mm-ss”)
    * @JsonFormat(pattern = "yyyy-MM-dd")
    * MM月份 和 HH小时 要大写，其余小写嗷！
    *
    *
    * 若直接这么使用(我们没有手动指定时区timezone)，
    * 所得结果与当前北京时间，会相差8个小时，因为我们处于是东八区（北京时间）。
    * 所以我们在格式化的时候要指定时区 timezone="GMT+8" 表示在东八区
    * */
    //所得结果与当前北京时间，会相差8个小时，因为我们处于是东八区（北京时间）
    //@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @JsonFormat( pattern="yyyy-MM-dd HH-mm-ss",timezone="GMT+8" ) //所得结果是东八区时间
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
