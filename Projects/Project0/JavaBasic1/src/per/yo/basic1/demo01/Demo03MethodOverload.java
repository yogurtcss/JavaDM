package per.yo.basic1.demo01;

/* 方法重载——多个方法的名称一样，但参数列表不一样
* 使用场景：方法功能类似，仅仅因为参数列表不一样而已
*
* 定义的方法名一致，参数列表不一样(参数类型也可不一样) 即可
* 在调用方法时，传入相对应的参数个数(传入相对应的参数类型)，
* 系统会自动匹配到重载的某个方法，然后执行。没有其他多余的操作。
*
*
* 方法重载与下列因素有关：
* 1.参数个数不同
* 2.参数类型不同
* 3.[重点关注]参数的多类型顺序不同：即 参数有多种类型，这些参数的顺序不同
*
* 方法重载与下列因素无关：
* 1.与参数名无关
* 2.与方法的返回值类型无关
*  */

public class Demo03MethodOverload {
    public static int sum( int a, int b ){
        System.out.println( "两个相加" );
        return a+b;
    }
    public static int sum( int a, int b, int c ){
        System.out.println( "三个相加" );
        return a+b+c;
    }
    public static int sum( int a, int b, int c, int d ){
        System.out.println( "四个相加" );
        return a+b+c+d;
    }

    /* 参数的多类型顺序不同：即 参数有多种类型，这些参数类型的顺序不同 */
    public static int sum( int a, double b ){ //a为int，b为double。参数类型的顺序为：int, double
        System.out.println( "两个相加，a为int型，b为double型" );
        return (int)(a+b);
    }
    public static int sum( double a, int b ){ //a为double，b为int。参数类型的顺序为：double, int
        System.out.println( "两个相加，a为double型，b为int型" );
        return (int)(a+b);
    }

    public static void main( String[] args ){
        /* 在调用方法时，传入相对应的参数个数即可
        * 系统会自动匹配到重载的某个方法，然后执行。没有其他多余的操作。
        *  */
        System.out.println( sum( 1,1,1,1 ) );
        System.out.println( sum( 1,2 ) );
        System.out.println( sum(1,1,1) );

        /* 参数的多类型顺序不同：即 参数有多种类型，这些参数类型的顺序不同 */
        System.out.println( sum(1,2.0) ); //参数类型的顺序为：int, double
        System.out.println( sum(1.0,2) ); //参数类型的顺序为：double, int
    }
}
