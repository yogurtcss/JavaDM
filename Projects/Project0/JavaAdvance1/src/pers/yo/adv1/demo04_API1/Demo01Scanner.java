package pers.yo.adv1.demo04_API1;

import java.util.Scanner; //1.导入键盘输入类
/* 只有java.lang包下的包不需使用import语句，
* 其他位置的包则需要import
*
* 获取键盘输入的一个int数字：int num = sc.nextInt(); //调用一次只读取一个int数字嗷！
* 获取键盘输入的一个字符串：String str = sc.next();【较特别】
*
*  */

public class Demo01Scanner {
    public static void main(String[] args) {
        //2.创建Scanner的实例对象sc；System.in表示从键盘进行输入
        Scanner sc = new Scanner( System.in ); //System.in表示从键盘进行输入
        //3.调用实例对象sc的成员方法
        int num = sc.nextInt();
        String str = sc.next();
        System.out.println( num );
        System.out.println( str );
    }
}
