package per.yo.basic1.demo01;

/* 方法——在此class内定义的
* 定义格式
* public static void 方法名称() { //static 表示 “全局” 或者 “静态” 的意思；即可 全局调用
*   方法体...
*  }
*
* 调用格式
* 方法名();
*
* 在外部调用静态方法时，可以使用"类名.方法名"的方式，也可以使用"对象名.方法名"的方式；
* 而实例方法只能使用"对象名.方法名"的方式；
* 也就是说，调用静态方法可以无需创建类的实例对象。
*
* 注意：
* 1.方法定义的先后顺序无所谓
* 2.不能在一个方法的内部定义另外一个方法
* 3.方法定义之后，自己不会执行；需要我们手动调用方法
*
*  */


public class Demo01Method {
    public static void printMethod(){
        for( int i=0; i<5; i++ ){
            for( int j=0; j<20; j++ ){
                System.out.print( "*" );
            }
            System.out.println(); //换一次行
        }
    }

    public static void main( String[] args ){
        printMethod(); //在main方法中调用自定义的方法
    }
}
