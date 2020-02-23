package pers.yo.utils;

/* 背景：多次匹配的结果拼接在一起，才是我们最后想要的匹配结果
这个工具类 ConcatMatchRstUtil：拼接多次匹配的结果嗷！

//剧集评分
StringBuilder sb = new StringBuilder();

for( int i=1; i<=3; i++ ){ //注意，i下标要从1开始，直到等于3！！
    //groupNo 为0时，表示的是原本的正则表达式嗷！
    sb.append( RegExHtmlUtil.getFieldByRegEx(
        page,  LoadPropertiesUtil.getPropsFromYouKuByKeyName("scoreRegEx"),  i
    ) ); //sb每次的的值：7 . 7
};
String score = new String( sb );
page.setScore( score );

System.out.println( "score："+score );

* */

import pers.yo.entity.Page;

public class ConcatMatchRstUtil {

    //在同一句正则中，有多个(.*?)匹配的地方
    public static String concatMatchRst( Page page, int from, int to, String regExKeyName ){
        //待下载的页面page，传入的正则表达式的键名

        StringBuilder sb = new StringBuilder();
        //for循环起始下标from，循环终止下标to；传入的正则表达式
        for( int i=from; i<=to; i++ ){
            sb.append( RegExHtmlUtil.getFieldByRegEx(
                    page, LoadPropertiesUtil.getPropsFromYouKuByKeyName(regExKeyName), i
            ) );
        }
        String concatRst = new String( sb ); //拼接的结果
        return concatRst;
    }

    //专门为tag标签定制的一个方法！ 添加分隔符split 英文逗号
    public static String concatMatchRst_split( Page page, int from, int to, String regExKeyName ){
        //待下载的页面page，传入的正则表达式的键名

        StringBuilder sb = new StringBuilder();
        //for循环起始下标from，循环终止下标to；传入的正则表达式
        for( int i=from; i<=to; i++ ){
            sb.append( RegExHtmlUtil.getFieldByRegEx(
                    page, LoadPropertiesUtil.getPropsFromYouKuByKeyName(regExKeyName), i
            ) );
            if( i!=to ){ //未到结尾时，就加英文逗号，到结尾就不加英文逗号了嗷！
                sb.append(","); //添加分隔符 英文逗号
            }
        }
        String concatRst = new String( sb ); //拼接的结果
        return concatRst;
    }


}
