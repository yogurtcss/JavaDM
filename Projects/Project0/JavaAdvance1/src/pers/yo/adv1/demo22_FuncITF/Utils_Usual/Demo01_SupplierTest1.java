package pers.yo.adv1.demo22_FuncITF.Utils_Usual;

import java.util.function.Supplier;

/*
    练习：求数组元素最大值
        使用Supplier接口作为方法参数类型，通过Lambda表达式求出int数组中的最大值。
        提示：接口的泛型请使用java.lang.Integer类。
 */

public class Demo01_SupplierTest1 {
    public static int getMax( Supplier<Integer> sup ){
        return( sup.get() );
    };

    public static void main(String[] args) {
        int[] arr = { 0,0,-50,88,99,33,-30 };
        //我的思路：max作为第三方仲裁者，然后两两比较数组元素
        int max0 = 0;
        for( int i=0, length=arr.length; i<length; i++ ){
            for( int j=i+1; j<length; j++ ){
                if( arr[i]<arr[j] && max0<arr[j] ){ // a_i比a_j小，且上一个max还比a_j小
                    max0 = arr[j];
                }else if( arr[i]>arr[j] && max0<arr[i] ){ // a_i比a_j大，且上一个max还比a_i小
                    max0 = arr[i];
                }
            }
        }
        System.out.println( max0 );

        int maxValue1 = getMax( ()->{ //不习惯这个lambda表达式…
            int max1 = 0;
            for( int i=0, length=arr.length; i<length; i++ ){
                for( int j=i+1; j<length; j++ ){
                    if( arr[i]<arr[j] && max1<arr[j] ){ // a_i比a_j小，且上一个max还比a_j小
                        max1 = arr[j];
                    }else if( arr[i]>arr[j] && max1<arr[i] ){ // a_i比a_j大，且上一个max还比a_i小
                        max1 = arr[i];
                    }
                }
            }
            return max1;
        } );
        System.out.println( maxValue1 );

        int maxValue2 = getMax( ()->{
            int max2 = arr[0]; //【算法改进】将最大元素首先置为 首元素a0 ！！
            for( int one: arr ){ //遍历数组中所有元素
                if( one>max2 ){ //若有比 我上一个max大的，我就让位给你，你做max
                    max2 = one;
                }
            }
            return max2;
        } );
        System.out.println( maxValue2 );

    }
}
