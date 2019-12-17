package pers.yo.adv1.demo23_StreamThought;

/*
    练习:集合元素处理（传统方式）
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
import pers.yo.adv1.demo23_StreamThought.Person;

public class Demo01_StreamTest1 {
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

        //开始整活
        ArrayList<String> one1 = new ArrayList<>();
        for( String per : one ){
            if( per.length()==3 ){ //第一个队伍只要名字为3个字的成员姓名；存储到一个新集合中。
                one1.add( per );
            }
        }
        int len1 = 3;
        ArrayList<String> one2 = new ArrayList<>();
        for( int i=0; i<len1; i++ ){ //第一个队伍筛选之后只要前3个人；存储到一个新集合中。
            one2.add( one1.get(i) );
        }

        ArrayList<String> two1 = new ArrayList<>();
        for( String per : two ){
            if( per.startsWith("张") ){ //第二个队伍只要姓张的成员姓名；存储到一个新集合中。
                two1.add( per );
            }
        }
        int start = 2; //跳过前两个人(下标为0、1)，则开始下标从2开始
        ArrayList<String> two2 = new ArrayList<>();
        //集合的方法 public int size():返回集合中元素的个数
        for( int i=start, length=two1.size(); i<length; i++ ){ //第二个队伍筛选之后不要前2个人；存储到一个新集合中。
            two2.add( two1.get(i) );
        }

        ArrayList<String> all = new ArrayList<>(); //合并
        //list实例对象.addAll(指定collection) 用于将 指定collection 中的所有元素 添加到 此list实例对象表示的列表 中
        all.addAll( one2 );
        all.addAll( two2 );

        ArrayList<Person> persons = new ArrayList<>(); //将all集合中的人，以Person类存入新集合persons中
        for( String per : all ){
            persons.add( new Person(per) );
        }
        
        for( Person per : persons ){
            System.out.println( per.getName() );
        }
    }
}
