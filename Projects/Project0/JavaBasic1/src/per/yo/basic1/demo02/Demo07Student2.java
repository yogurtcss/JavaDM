package per.yo.basic1.demo02;

import per.yo.basic1.demo02.Student2;

public class Demo07Student2 {
    public static void main( String[] args ){
        Student2 s1 = new Student2();
        Student2 s2 = new Student2( "小明明", 10 );
        System.out.println( s2.getName() );
    }
}
