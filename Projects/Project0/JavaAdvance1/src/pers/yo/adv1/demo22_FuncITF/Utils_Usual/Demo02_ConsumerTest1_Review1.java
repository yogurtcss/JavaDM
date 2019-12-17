package pers.yo.adv1.demo22_FuncITF.Utils_Usual;

/* 复习Demo02_ConsumerTest1，少注释的版本
*  */
import java.util.function.Consumer;

public class Demo02_ConsumerTest1_Review1 {
    public static void con1_con2( String[] arr, Consumer<String> con1, Consumer<String> con2 ){
        for( String one: arr ){
//            con1.accept(one);
//            con2.accept(one);

            con1.andThen(con2).accept(one);
        }
    }

    public static void main( String[] args ){
        String[] info = { "小明,1", "哈哈,2", "大师兄,3" };
        con1_con2(
                info,
                s -> System.out.print( s.split(",")[0]+"：" ),
                s -> System.out.println( s.split(",")[1]+"。" )
        );
    }
}
