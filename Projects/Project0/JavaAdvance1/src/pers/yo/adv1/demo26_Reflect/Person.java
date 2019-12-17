package pers.yo.adv1.demo26_Reflect;

public class Person {
    private String name;
    private int age;
    public String pub1;  //public 1
    public String pub2;  //public 2

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void print(){
        System.out.println( "俺是无形参输入的public成员方法！" );
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
