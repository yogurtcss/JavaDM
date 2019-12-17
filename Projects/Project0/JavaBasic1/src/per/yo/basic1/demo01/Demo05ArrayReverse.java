package per.yo.basic1.demo01;

public class Demo05ArrayReverse {
    /* 返回值为数组，所以返回值类型要加 int[]；返回的也是数组的地址值
    * 方法：面向过程的写法
    *  */
    public static int[] arrReverse(int[] arr ){ //数组作为传入的形参，传入的是数组的地址值
        /* int[] arr1 = { 1,2,3,4,5 }
        * int[] arr2 = { 1,2,3,4 }
        *  */
        int length = arr.length;
        int[] temp = new int[length]; //借助另一个数组
        for( int i=0; i<length; i++ ){
            temp[i] = arr[ (length-1)-i ];
        };
        return temp; //返回值为数组，所以返回值类型要加 int[]；返回的也是数组的地址值
    };
    public static void showArr( int[] arr ){
        System.out.println( "The array is：" );
        for( int i=0, length=arr.length; i<length; i++ ){
            if( i!=(length-1) ){ //未到达数组最后一个下标时，用逗号连接
                System.out.print( arr[i] + "," );
            }
            else{ //到达数组最后一个下标时，妹有逗号了
                System.out.print( arr[i] );
            }
        }
        System.out.println(); //输出空行
    };

    public static void main( String[] args ){
        int[] arr = new int[]{ 1,2,3,4,5 };
        showArr( arr );
        int[] arrReverse = arrReverse( arr ); //调用：面向对象，即 偷懒
        System.out.print( "After reversing, " ); //反转后
        showArr( arrReverse );
    }


}
