package pers.yo.utils;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import pers.yo.entity.Page;

import java.util.Arrays;

public class ParseSearchRstUtil {
    //解析“搜索结果页”，
    /* 2020-02-22 21:50:46
     * 最新问题。JSON数据中：
     * videoLink值那些带有中文名的url，baseUrlDetail与videoId拼串后，并不是它的详情页，而是404！
     *
     * 需要先 urlEncode之后，再进去url编码后的“搜索结果页”，再次爬取真正的详情页url！我佛了！
     *  */
    public static String parseSearchRst( Page page ){
        String regEx = "<a .* data-spm=\"dselectbutton\" target=\"_blank\" href=(.*?)>.*</a>";
        String rst = RegExHtmlUtil.getFieldByRegEx(
                page,  regEx, 1
        );
        return rst;

        //返回真正的详情页url
    }
}
