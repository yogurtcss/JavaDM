package pers.yo.web0.demo02_XML;

/* Jsoup的功能
1.从字符串，网页，本地文件等方式生成 Document
2.在生成 Document 后，根据 Dom 和 css 或类似 jquery 的 selector 语法获取 Element，
然后再从 Elements 中获取节点属性、文本、html
3.对 Element 的进行操作：包括 HTML 的值、节点内容的值和设置节点属性的值

-------------------------------------------------------------
import org.jsoup.Jsoup 工具类，里面全是静态static方法

1. static Connection connect(String url)：创建并返回 URL 的连接

//需要引入包 org.jsoup.nodes.Document 使用到了 Document对象
2. static Document parse( File文件类型 in, String charsetName)：将【指定的字符集】的(File对象) 文件 解析成 Document文档对象
* 问：形参中的File文件类型 in 怎么来的？
  答：(1)一般，把配置文件xml放入src目录下(即 classpath的子文件夹)，
  (2)通过 当前的java文件的类加载器classLoader.getResource(XXX.xml配置文件) 获取配置文件的URL
      // URL X_url = classLoader.getResource( xxx.配置文件 )
  (3)然后获取此 URL的路径部分 ：
      // X_url.getPath();
     // public String getPath() 返回 URL 路径部分。
  (4)最后，生成形参 new File( 配置文件的path )

-------------------------------------------------------------
▲ import org.jsoup.nodes.Document：

1.【直接】获取元素集合对象Elements：通过 Document实例对象doc.getElementsByXXX()调用；

【直接】获取单个元素对象Element
Element el = doc.getElementById(String id) //注意，这是获取单个具体元素！！ 没有复数s！！

// doc.getElements_By_XXX，注意这是复数，有s的！！
Elements els = doc.getElementsByTag(String tag)
Elements els = doc.getElementsByClass(String className)
Elements els = doc.getElementsByAttribute(String key) (and related methods)

2.【通过“选择器”】获取元素集合对象Elements，或单个元素对象Element
Elements els = doc.select( String selector选择器 ); //获取元素集合对象Elements

//链式调用，先获取到元素集合对象Elements，
//然后追加调用XXX方法 从该集合对象Elements中，获取具体的、单个元素对象Element
Element el = doc.select( String selector选择器 ).XXX方法();


【扩展！】select方法 在 Document、Elements 或 Element 实例对象中都可以使用！
且是上下文相关的，因此可实现指定元素的过滤，或者链式选择访问。

select( String Selector ) 见 P2.1 XML 解析.md文档
A：Selector 选择器概述
B：Selector 选择器组合使用
C：伪选择器 selectors


▲ import org.jsoup.select.Elements;
* 从Document的getElementByXXX获取到的 元素的【集合】对象 ——
  具有同一个Id、Tag、Class的元素，都会被放在在同一个集合中
* Elements对象提供了一系列类似于 DOM 的方法来查找元素，抽取并处理其中的数据


▲ import org.jsoup.nodes.Element; //从某个Elements集合中，再次选出的具体的元素对象
* 如 Element e = 某Elements(集合)实例对象.get(0); //获取此集合中第一个元素

*   */

import org.jsoup.Jsoup; //工具类
import java.io.File;
import java.io.IOException;

import org.jsoup.nodes.Document; //文档对象
import org.jsoup.nodes.Element; //具体的元素对象
import org.jsoup.select.Elements; //选取的元素集合对象
//Elements就是 泛型为Element的 ArrayList 集合

public class Demo01Jsoup {
    public static void main(String[] args) throws IOException {
        /* 获取某类的 类_类型实例对象
        * Class.forName("全类名：即包名.类名")
        * 类名.class属性
        * 任意实例对象.getClass()
        *  */
        String path = Demo01Jsoup.class.getClassLoader().getResource( "student.xml" ).getPath();
        // System.out.println( path ); //返回文件的路径 path
        Document doc = Jsoup.parse( new File(path) , "utf-8"); //直接声明异常，暂时不处理
        Element el = doc.getElementById( "itcast" );
        // System.out.println( el );
        Elements els = doc.getElementsByTag( "name" );
        System.out.println( els.size() );

        Element e1 = els.get(0);
        String name = e1.text(); //获取e1的文本数据
        System.out.println( name );

        System.out.println( "---------------doc文档对象，通过属性attribute、属性值来获取Elements集合对象" );
        // <student number="heima_0001"> <!-- 属性为number，属性值为"heima_0001" -->

        /* 【通过 键 来获取 元素集合对象Elements】
        * doc.getElementsByAttribute( String key ) 返回元素集合对象Elements
        * 获取 属性名为 number的元素；并将获取到的元素放入Elements集合：Elements Hei_Ma中
        *  */
        Elements elsHM = doc.getElementsByAttribute( "number" );
        System.out.println( "elsHM的长度为："+elsHM.size() ); //查看集合elsHM的长度

        /* 【通过 键值对 来 获取元素对象Elements】
        * doc.getElementsByAttributeValue( String key, String value ) 返回元素集合对象Elements
        * 获取 属性名为number，且属性值为"heima_0001" 的元素集合 Elements elsHM0001
        *  */
        Elements elsHM0001 = doc.getElementsByAttributeValue( "number", "heima_0001" );
        System.out.println( "elsHM0001的长度为："+elsHM0001.size() );

        System.out.println( "---------------Element单个元素对象A，获取此元素A的子元素对象" );
        Element elName = doc.getElementById( "itcast" );
        Elements elsXing = elName.getElementsByTag( "xing" ); //通过子元素标签名，获取其子元素对象
        System.out.println( "elsXing的长度为："+elsXing.size() );
        Element elXing = elsXing.get(0);
        System.out.println( elXing.text() );

//        System.out.println( "----" );
//        short a = 1;
//        a = a + 1;
//        System.out.println( a );
    }
}
