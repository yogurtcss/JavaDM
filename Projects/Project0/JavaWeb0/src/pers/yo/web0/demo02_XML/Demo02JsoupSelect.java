package pers.yo.web0.demo02_XML;

import org.jsoup.Jsoup; //工具类，里面全是静态方法
import java.io.File;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

// select(String cssQuery) 方法将返回一个 Elements 集合，并提供一组方法来抽取和处理结果。

public class Demo02JsoupSelect {
    public static void main(String[] args) throws IOException {
        String path = Demo02JsoupSelect.class.getClassLoader().getResource("student.xml").getPath();
        Document doc = Jsoup.parse( new File(path), "utf-8" );
        System.out.println( "▲ 使用select方法，快速获取某元素" );
        // Elements elsName = doc.select( "name" ); //通过标签名查询
        // #id对应的值: 通过id对应的值来查询元素
        Elements elsName = doc.select( "#itcast" ); //查询id值为itcast的元素：#itcast
        // Elements elsName = doc.select("[id]");
        System.out.println( elsName.size() );

        System.out.println( "----------------------" );
        /* 获取student标签且其number属性值为heima_0001
        * 注意 number="heima_0001" 中：
        * 属性值的双引号使用了 转义字符，将 " 写成了 \"
        * 因为 双引号不能嵌套使用
        *  */
        Elements els1 = doc.select( "student[number=\"heima_0001\"]" );
        System.out.println( els1.get(0).text() );


        /* 获取student标签且其number属性值为heima_0002的age子标签
        * 注意 number="heima_0002" 中：
        * 属性值的双引号使用了 转义字符，将 " 写成了 \"
        * 因为 双引号不能嵌套使用
        *  */
        System.out.println( "-----" );
        Elements els2 = doc.select( "student[number=\"heima_0002\"] > age" );
        System.out.println( els2.get(0).text() );
    }
}
