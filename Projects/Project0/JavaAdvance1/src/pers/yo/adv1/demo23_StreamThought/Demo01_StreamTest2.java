package pers.yo.adv1.demo23_StreamThought;

/*
    练习:集合元素处理（流方式）
        现在有两个ArrayList集合存储队伍当中的多个成员姓名，要求使用传统的for循环（或增强for循环）依次进行以下若干操作步骤：
        1. 第一个队伍只要名字为3个字的成员姓名；存储到一个新集合中。
        2. 第一个队伍筛选之后只要前3个人；存储到一个新集合中。
        3. 第二个队伍只要姓张的成员姓名；存储到一个新集合中。
        4. 第二个队伍筛选之后不要前2个人；存储到一个新集合中。
        5. 将两个队伍合并为一个队伍；存储到一个新集合中。
        6. 根据姓名创建Person对象；存储到一个新集合中，元素的数据类型为Person
        7. 打印整个队伍的Person对象信息。
 */
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Set;

import pers.yo.adv1.demo23_StreamThought.Person;

public class Demo01_StreamTest2 {
    public static void main(String[] args) {
        ArrayList<String> one = new ArrayList<>();
        one.add("迪丽热巴");     one.add("宋远桥");
        one.add("苏星河");       one.add("石破天");
        one.add("石中玉");       one.add("老子");
        one.add("庄子");         one.add("洪七公");

        ArrayList<String> two = new ArrayList<>();
        two.add("古力娜扎");         two.add("张无忌");
        two.add("赵丽颖");           two.add("张三丰");
        two.add("尼古拉斯赵四");      two.add("张天爱");
        two.add("张二狗");

        Stream<String> s1 = one.stream();
        Stream<String> s2 = two.stream();
        // 第一个队伍只要名字为3个字的成员姓名、筛选之后只要前3个人
        Stream<String> s1_temp = s1.filter( s->s.length()==3 ).limit(3);
        // 第二个队伍只要姓张的成员姓名、筛选之后不要前2个人
        Stream<String> s2_temp = s2.filter( s->s.startsWith("张") ).skip(2);
        // 将两个队伍合并为一个队伍；存储到一个新集合中。
        // List<String> list = Stream.concat( s1_temp, s2_temp ).collect( Collectors.toList() );
        // 原本是 List类型的，我直接向下转型为 ArrayList<String>
        ArrayList<String> list = (ArrayList<String>) Stream.concat( s1_temp, s2_temp ).collect( Collectors.toList() );
        ArrayList<Person> persons = new ArrayList<>();
        for( String per : list ){
            persons.add( new Person(per) );
        }
        for( Person per : persons ){
            System.out.println( per.getName() );
        }

    }
}
