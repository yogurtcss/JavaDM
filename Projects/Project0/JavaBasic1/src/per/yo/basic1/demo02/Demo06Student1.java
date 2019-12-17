package per.yo.basic1.demo02;

import per.yo.basic1.demo02.Student1;

public class Demo06Student1 {
    public static void main( String[] args ){
        Student1 s1 = new Student1();
        s1.setAge( 18 );
        s1.setName( "鱼鱼🐟" );
        s1.setMale( false );

        System.out.println( s1.getAge() );
        System.out.println( s1.getName() );
        System.out.println( s1.isMale() );

        System.out.println( "----------------" );
        s1.show();
    }
}
