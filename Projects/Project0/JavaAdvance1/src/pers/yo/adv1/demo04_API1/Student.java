package pers.yo.adv1.demo04_API1;

public class Student { //整一个标准的 Java Bean
    private String name;
    private int age;
    /* 静态成员变量，该同一类下创建的所有的实例对象 所共享
    * 对静态成员变量 赋值：直接通过 类名.静态成员=XXX 进行赋值
    * 只需 使用该类Student 设置一次room的值即可：类名.room = "北主楼303"，即 Student.room = "北主楼303"；
    *
    * 其实例对象s1、s2、s3、s4等 就共享此room值了，
    * 即 s1、s2、s3、s4的room值都是 "北主楼303"
    *  */
    static String room;

    public Student(){ //无参构造方法

    };
    public Student( String name, int age ){
        this.name = name;
        this.age = age;
    };

    public void setName( String name ){
        this.name = name;
    };
    public String getName(){
        return this.name;
    };

    public void setAge( int age ){
        this.age = age;
    };
    public int getAge( int age ){
        return this.age;
    }
}
