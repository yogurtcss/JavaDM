package per.yo.basic1.demo02;

import per.yo.basic1.demo02.Person;

public class Demo05Person {
    public static void main( String[] args ){
        Person p1 = new Person();
        p1.name = "小米粥OvO";
//        p1.age = 18;
//        p1.show();
        p1.setName("大米粥OvO");
        p1.setAge( 8 );
        p1.show();

    }
}
