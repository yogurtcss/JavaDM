package pers.yo.adv1.demo03_Stream;

/*
    可以改变输出语句的目的地(打印流的流向)
    输出语句,默认在控制台输出
    使用System.setOut方法改变输出语句的目的地改为参数中传递的打印流的目的地
        static void setOut(PrintStream out)
          重新分配“标准”输出流。
 */
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.File;

public class Demo10PrintStream1 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println( "我在控制台中输出嗷！" );

        PrintStream ps = new PrintStream( new File("E:\\Data\\目的地是打印流.txt") );
        System.setOut( ps ); //把输出语句的目的地，修改为 打印流的目的地
        System.out.println( "我在打印流的目的地中输出啦！" );

        ps.close();
    }
}
