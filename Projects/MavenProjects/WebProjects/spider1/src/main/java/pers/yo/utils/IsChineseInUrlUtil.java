package pers.yo.utils;

//判断URL中是否有中文字符！！
public class IsChineseInUrlUtil {

    //判断一个字符是不是中文
    public static boolean isChinese_char(char c){
        return( c>=0x4E00 && c<=0x9FA5 ); //根据字节码判断
    }

    public static boolean isChinese(String str){
        if( str==null ){
            return false;
        }
        for( char c : str.toCharArray() ){
            if( isChinese_char(c) ){
                return true; //只要有一个中文字符，就返回true
            }
        }
        return false;
    }
}
