package pers.yo.adv1.demo04_API1;

/* 比较两个字符串的内容是否相等
*
* 字符串实例对象str.equals( Object obj )：形参可以是任意对象
* 当且仅当形参为String型且内容、英文字母大小写均相同时，返回true；否则返回false
* 只区分英文字母大小写，不区分汉字大小写嗷
*
* str.equalsIgnoreCase( Object obj )：忽略大小写 进行比较
*
* 若比较双方中：一个常量另一个为变量str，强烈推荐 常量.equals(变量str)
*  */

/* 假设已有字符串实例对象str
* str.length()：获取字符串长度(字符的个数)
* str.concat( String str2 )：将str和str2进行字符串拼接，返回拼接后的串
* str.charAt( int 下标x )：按指定下标x返回str字符串的单个字符
* str.indexOf( String 某个字符串strX )：在原字符串str中查找strX，返回其首次出现处的下标；若没有找到则返回-1
*
* -----------------------------------------------------------------------------------------------------
* 字符串截取。注意，这个substring妹有使用驼峰命名法！！这个substring妹有使用驼峰命名法！！
* 这个substring妹有使用驼峰命名法！！这个substring妹有使用驼峰命名法！！
* 这个substring妹有使用驼峰命名法！！这个substring妹有使用驼峰命名法！！
*
* str.substring( int 下标x )：截取 从下标x开始 直到字符串末尾 的字符串，返回此截取后的字符串
* str.substring( int 下标begin, int 下标end )：截取区间：[ begin, end ) 左闭右开区间
* 截取 以下标begin开始(包含begin)，以下标end结尾(不包含end) 的字符串，返回此截取后的字符串
*
* -----------------------------------------------------------------------------------------------------
* str.toCharArray()：将当前的str字符串(拆分)转换为字符数组 并返回
* str.getBytes()：将当前的str字符串转换为其(各字符一一)对应 字节数组 并返回
* str.replace( 旧字符串x, 新字符串y )：将原字符串str中出现的旧字符串x，替换为新字符串y，返回替换后的结果
*
* -----------------------------------------------------------------------------------------------------
* 字符串分割
* str.split( String regex字符串型的正则表达式 )：按所给的 字符串型的正则表达式，将原字符串str分割为若干部分，
* 返回值为：分割完毕的各部分字符串 组成的 字符串数组array
* 如：String str2 = "XXX,YYY,ZZZ";
* String[] arr = str2.split(",");  //返回值为：分割完毕的各部分字符串 组成的 字符串数组array。在这里结果为 [XXX,YYY,ZZZ]
*
*  */


public class Demo05StringMethod {
    public static void main(String[] args) {
        String str1 = "hello";
        System.out.println( "haha".equals(str1) ); //若比较双方中：一个常量另一个为变量str，强烈建议 常量.equals(变量str)

        String strNull = null; //当此变量str为null时：
        System.out.println( "haha".equals(strNull) ); //常量.equals(变量str) 推荐：产生false
//        System.out.println( strNull.equals("hahha") ); //变量str.equals(常量) 不推荐：报错，空指针异常NullPointerException

        System.out.println( "HelloWorld".substring(0,5) ); //截取字符串，截取区间为左闭右开区间嗷： [0,5) 即截取到[0,4]，可得Hello

        String str2 = "XXX YYY ZZZ";
        String[] arr2 = str2.split(" ");
        for( int i=0, length=arr2.length; i<length; i++ ){
            System.out.println( arr2[i] );
        }

    }
}
