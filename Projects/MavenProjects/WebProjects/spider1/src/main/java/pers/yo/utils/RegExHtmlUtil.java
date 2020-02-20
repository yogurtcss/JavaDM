package pers.yo.utils;


import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import pers.yo.entity.Page;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//页面解析工具类
public class RegExHtmlUtil {
    public static String getFieldByRegEx( Page page, String regEx, int groupNo ){
        String content = page.getContent(); //获取正文内容，混杂一堆HTML标签

        //我不使用Xpath表达式，我不用这个HtmlCLeaner呀！
        //TagNode rootNode = new HtmlCleaner().clean(content); //得到根标签

        Pattern pattern = Pattern.compile( regEx ); //编译正则表达式
        // Matcher m = pattern.matcher(content); //匹配正文内容content
        String rst = RegExUtil.getPageInfoByRegEx( content, pattern, groupNo ); //调用自定义工具类RegExUtil
        return rst; //返回结果
    }
}
