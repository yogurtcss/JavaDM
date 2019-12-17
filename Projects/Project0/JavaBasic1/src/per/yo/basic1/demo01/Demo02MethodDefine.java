package per.yo.basic1.demo01;

/* 定义方法的完整格式——在此class内定义的
* 修饰符 返回值类型 方法名称( 参数1，参数2... ){
*     方法体
*     return 返回值；
* }
*
* 修饰符：现阶段的固定写法为public static，暂时记住它
* 方法名：小驼峰式——第一个单词首字母小写，后面其他单词首字母大写。
* return的作用 (1)停止当前方法；(2)将方法所得返回值 还给调用处
*
* 扩展：大驼峰式——每个单词的第一个字母都大写
*
*
* 方法的三种调用格式
* 1.单独调用：方法名(参数);
* 2.打印调用
* 3.赋值调用
*
* 注意，此前返回值类型void的方法，不能使用打印调用或赋值调用，只能 单独调用
* 因为此void方法没有返回值
*  */

public class Demo02MethodDefine {
    public static int sumInt( int a, int b ){
        return a+b;
    }

    public static void main( String[] args ){
        sumInt( 1,2 ); //单独调用
        int ans = sumInt(1,2); //赋值调用
        System.out.println( ans );
        System.out.println( "我是打印调用嗷："+sumInt(1,2) ); //打印调用
    }
}
