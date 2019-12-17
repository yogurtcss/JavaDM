package pers.yo.web0.demo02_XML;

/* Xpath语法


一、选取节点
/	从根节点选取
//	从匹配选择的当前节点选择文档中的节点，而不考虑它们的位置。
.	选取当前结点
..	选取当前节点的父结点
@	选取属性

二、谓语 通过 [ ... ]添加限定条件
示例：
//title[@lang='eng']
选取所有的 title 元素，且这些元素拥有值为 eng 的 lang 属性。

注：xpath表达式最外层用双引号，里面的@xx = '' 值用单引号括住；
*  */

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Demo03JsoupXpath {
    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
        String path = Demo03JsoupXpath.class.getClassLoader().getResource("student.xml").getPath();
        Document doc = Jsoup.parse( new File(path), "utf-8" );

        JXDocument jxDoc = new JXDocument( doc );
        //注：xpath表达式最外层用双引号，里面的@xx = '' 值用单引号括住；
        List<JXNode> jxNodes4 = jxDoc.selN( "//student/name[@id='itcast']" );
        for( JXNode one: jxNodes4 ){
            System.out.println( one );
        }
    }
}
