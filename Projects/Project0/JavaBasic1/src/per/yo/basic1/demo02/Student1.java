package per.yo.basic1.demo02;

public class Student1 {
    private String name;
    private int age;
    private boolean male; //是不是爷们儿

    public void setMale( boolean b ){
        male = b;
    }
    public boolean isMale(){ //若get方法的返回值为boolean型，则此get方法名必须写为 isXXX
        return male;
    }
    /* 当方法传入的局部变量与类中的成员变量重名时，根据“就近原则” ，方法会优先使用局部变量
    * 此时，若想继续使用成员变量，则使用this关键字：this.成员变量名 即可
    *
    * 通过谁调用的方法，谁就是 this
    *  */
    public void setAge( int age ){
        this.age = age; //this.age是 成员变量
    }
    public int getAge(){
        return age;
    }

    public void setName( String n ){
        name = n;
    }
    public String getName(){
        return name;
    }

    public void show(){
        System.out.println( "我是：" + name + "，年龄为：" + age + "，是不是爷们儿？ " + male );
    }

}
