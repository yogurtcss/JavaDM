package pers.yo.adv1.demo14_Map;

import java.util.Objects;

public class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() { //使用alt+insert快捷键，添加重写的toString方法，以字符串输出此对象的形式
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    /* 使用alt+insert快捷键 快速重写hashCode()和equals()方法，一路默认点下一步即可 */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

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



}
