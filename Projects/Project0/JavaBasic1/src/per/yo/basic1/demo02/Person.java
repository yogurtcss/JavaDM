package per.yo.basic1.demo02;

public class Person {
    String name;
    private int age; //年龄，私有

    /* 在本类中提供public的成员方法，供类外的朋友们使用，
    * set方法：没有返回值，即void
    * get方法：有返回值，加上具体的返回值类型
    * 以间接读取本类的成员变量
    *  */
    public void setName( String n ){
        name = n;
    }
    public void setAge( int num ){
        age = num;
    }
    public int getAge(){
        return age;
    }

    public void show(){
        System.out.println( "我叫：" + name + "，年龄为：" + age );
    }
}
