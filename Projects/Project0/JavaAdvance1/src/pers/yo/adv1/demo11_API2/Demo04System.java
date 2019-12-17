package pers.yo.adv1.demo11_API2;

/* System.currentTimeMillis() 返回以毫秒为单位的当前时间
*
* System.arraycopy( src原数组，srcPos原数组的起始下标，dest目标数组，destPos目标数组的起始下标，length要复制的数组元素的长度 )，
* 此方法无返回值
* 从原数组src的起始下标srcPos开始，复制length个原数组元素；
* 在目标数组dest起始下标destPos开始，粘贴这length个原数组元素
*  */

public class Demo04System {
    public static void main(String[] args) {
        System.out.println( System.currentTimeMillis() );
        int[] src = new int[]{ 1,2,3,4,5,6 };
        int[] dest = new int[]{ 100,2333,999,3135,22,111 };
        System.arraycopy( src, 0, dest, 0, 3 ); //复制前三个原数组元素，到目标数组的前三个位置中
        for( int i=0, length=dest.length; i<length; i++ ){
            if( i!=length-1 ){
                System.out.print( dest[i]+", " );
            }
            else{
                System.out.print( dest[i] );
            }
        }
    }
}
