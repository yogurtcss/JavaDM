package pers.yo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//正则表达式匹配工具
public class RegExUtil {
    public static String getPageInfoByRegEx(String content, Pattern pattern, int groupNo){
        //原来传入的正则表达式，是已经编译compile过的了
        Matcher m = pattern.matcher(content);
        String rst = null;
        if( m.find() ){
            rst = m.group(groupNo);
        }
        return rst;
    }
}
