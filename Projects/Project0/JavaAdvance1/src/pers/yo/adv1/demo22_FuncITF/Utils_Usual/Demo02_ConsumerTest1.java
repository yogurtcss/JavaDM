package pers.yo.adv1.demo22_FuncITF.Utils_Usual;

/*
    练习:
        字符串数组当中存有多条信息，请按照格式“姓名：XX。性别：XX。”的格式将信息打印出来。
        要求将打印姓名的动作作为第一个Consumer接口的Lambda实例，
        将打印性别的动作作为第二个Consumer接口的Lambda实例，
        将两个Consumer接口按照顺序“拼接”到一起。
 */

/*
split()方法根据匹配给定的正则表达式来拆分字符串。

语法
public String[] split(String regex, int limit)
参数
regex -- 正则表达式分隔符。
limit -- 分割的份数。

返回值：字符串数组

注意：
(1) . 、 | 和 * 等转义字符，必须得加 \\。
(2)多个分隔符，可以用 | 作为连字符。

*  */


import java.util.function.Consumer;

public class Demo02_ConsumerTest1 {
    public static void con1_con2( String[] arr, Consumer<String> con1, Consumer<String> con2 ){
        for( String one : arr ){
            con1.andThen(con2).accept( one );
        }
    }

    public static void main(String[] args) {
        String[] info = { "小明,1", "哈哈,2", "大师兄,3" };
        con1_con2(
                info,
                /* 注意：s.split(",")的返回值是 分割后元素组成的 字符串数组
                * 如某个返回值为 [ 小明 , 1 ] 这里面的, 逗号 是数组默认的分隔符哈
                * 则 s.split(",")[0] 就是取到了 姓名“小明”
                *   s.split(",")[1] 就是取到了 序号 “1”
                *
                * 这法子很妙，值得记下来
                *  */
                s -> System.out.print( s.split(",")[0]+"：" ), //取到“姓名”，后加自定义的连接符 冒号
                s -> System.out.println( s.split(",")[1]+"。" ) //取到“序号”，后加自定义的连接符 句号
        );
    }
}