package pers.yo.service.impl;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import pers.yo.entity.Page;
import pers.yo.service.ProcessService;
import pers.yo.utils.RegExHtmlUtil;
import pers.yo.utils.RegExUtil;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YOUKUProcessServiceImpl implements ProcessService { //解析页面，并将解析结果封装进对象中


    @Override
    public void process(Page page) { //爬虫爬到的页面对象Page：里面混杂一堆HTML标签
        String content = page.getContent(); //获取页面对象的正文内容：一堆HTML标签，需要用正则表达式整活
        //System.out.println( content );
        System.out.println( "----------" );
        HtmlCleaner cleaner = new HtmlCleaner();
        TagNode rootNode = cleaner.clean(content); //获取根节点：html
        //System.out.println( "根节点是："+rootNode ); //查看一下clean后的结果：根节点html

        //获取剧集别名。有些属性(列名)在2020年已经找不到数据了！
        try {
            // 取出Xpath表达式匹配到的内容 Xpath_rst，返回值为 Object[]
            Object[] xpRst = rootNode.evaluateXPath("//li[@class='p-alias']");
            if( xpRst.length>0 ){
                System.out.println( "Xpath表达式的结果："+Arrays.toString(xpRst) );
                TagNode node = (TagNode) xpRst[0]; //向下转型
                System.out.println( node.getText().toString() ); //返回该节点的文本：剧集的别名
            }else{
                System.out.println( "Xpath表达式匹配不到结果！" );
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        //正则表达式的做法：我佛了
        // [^>]+ 原子表。^> 表示原子表中 非右尖括号> +至少出现1次
        String allAliasRegEx = "<li class=\"p-alias\"[^>]+>(.*?)<i";
        Pattern aliasPtn = Pattern.compile( allAliasRegEx ); //编译正则表达式
        Matcher m_alias = aliasPtn.matcher( content ); //使用正则表达式匹配某文本
        //我佛了，我还以为是我傻了！
        if( m_alias.find() ){ //在调用 m.group()方法 之前，一定要先调用 m.find()方法。不然会产生编译错误！
            /* 在正则表达式中，用括号括起来的算作一组，
             * group(0) 等价于 group()，表示整个正则表达式的匹配字符串，
             * group(1) 等价于 第一个括号内的表达式 返回的字符串
             *  */
            System.out.println( "整个正则表达式的匹配字符串："+m_alias.group(0) ); //原本的 整个正则表达式的匹配字符串
            System.out.println( "匹配的第一个括号处："+m_alias.group(1) ); //匹配的第一个括号处
            page.setAlias( m_alias.group(1) ); //并将解析结果封装进对象中
        }else{
            System.out.println( "正则表达式匹配不到结果！" );
        }


        //获取上映时间
        try{
            Object[] xpRst = rootNode.evaluateXPath( "//span[@class='pub']" );
            if( xpRst.length>0 ){
                //System.out.println( Arrays.toString(xpRst) );
                TagNode targetNode = (TagNode)xpRst[0];
                /* 如果直接 targetNode.getText().toString()，结果是   上映：2016-08-22
                * 加个split("：")方法，以中文冒号为分隔符 [ "上映", "2016-08-22" ]
                * 则数组下标为1 的元素就是我们想要的结果！
                *  */
                //以中文冒号为分隔符
                System.out.println( "上映时间是："+targetNode.getText().toString().split("：")[1] );
            }
        }catch( XPatherException e ){
            e.printStackTrace();
        }
        /* 正则表达式的做法：书写一时爽，过后……   //断句就好看了
        * >(.*?) 匹配时间前的一个右尖括号，精准一点嗷
        * 正则表达式中 中的 \\ 转义为 \
        * <span class="pub">  [^(\\d)]+  >(.*?)</span>
        *  */
        String showTimeRegEx = "<span class=\"pub\">[^(\\d)]+>(.*?)</span>";
        Pattern showTimePtn = Pattern.compile( showTimeRegEx ); //编译正则表达式
        Matcher m_showTime = showTimePtn.matcher( content ); //匹配全文
        if( m_showTime.find() ){ //一定要先find()，然后才group()啊！
            System.out.println( "正则表达式。上映时间为："+m_showTime.group(1) );
            page.setShowTime( m_showTime.group(1) ); //并将解析结果封装进对象中
        }

        //获取其他的属性：主演。再做一个，我就不做了！
        try{
            Object[] xpRst = rootNode.evaluateXPath( "//li[@class='p-performer']" );
            TagNode targetNode = (TagNode)xpRst[0]; //取出第0个元素
            System.out.println( "Xpath表达式。主演是："+targetNode.getText().toString().split("：")[1] );
        }catch( XPatherException e ){
            e.printStackTrace();
        }
        //正则表达式的做法
        String mainActorRegEx = "<li class=\"p-performer\" title=\"(.*?)\">"; //这样直接拿“主演”名字，注意要排除双引号，更粗暴嗷！
        Pattern mainActorPtn = Pattern.compile( mainActorRegEx ); //编译的正则表达式
        String rst = RegExUtil.getPageInfoByRegEx( content,mainActorPtn,1 );
//        if( m_mainActor.find() ){
//            System.out.println( "正则表达式。主演是："+m_mainActor.group(1) );
//        }
        System.out.println( "RegExUtils。主演是："+rst );
        System.out.println( "-----------------" );
        System.out.println( "使用RegExHtmlUtil页面解析工具类嗷！："+RegExHtmlUtil.getFieldByRegEx(page, mainActorRegEx,1) );
        //并将解析结果封装进对象中，我不做了！
    }


}
