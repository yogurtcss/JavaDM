package pers.yo.adv1.demo02_Recursion;

/* 递归要有条件限定，保证递归能停止下来
* 否则将发生栈内存溢出
*  */

public class Demo01Recursion {
    public static int sum( int n ){
        if(n==1){
            return 1;
        };
        return n+sum(n-1 );
    };


    public static void main(String[] args) {
        System.out.println( sum(3) );
    }
}
