package pers.yo.adv1.demo22_FuncITF.Demos;

import java.util.Comparator; //比较其它类的两个不同对象


/* Comparator 接口可以实现自定义排序，实现 Comparator 接口时，要重写 compare 方法：
int compare (Object o1, Object o2) 返回一个基本类型的整型
如果要按照升序排序，则 o1小于o2，返回 -1（负数），相等返回0；    o1大于o2返回 1（正数）
如果要按照降序排序，则 o1小于o2，返回 1（正数）， 相等返回0；    o1大于o2返回 -1（负数）
*  */

/* 一个标准的 自定义比较器 的写法：
传入Comparator中的泛型为：你想要比较的类
class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
        return a.name.compareToIgnoreCase(b.name);
    }
}
*  */
public class PerAgeCmp implements Comparator<Person> { //最原始、最基本的原则：实现泛型接口时，就要指定泛型的类型！！
    public int compare( Person p1, Person p2 ){
        /* 按自定义的条件，必须返回-1、0、1三个值！！
        * 若只用三目运算符返回了-1、1，却没返回0(相等情况)，则会报错
        * 故需特别留意 相等的情况！！
        *  */
        if( p1.getAge()==p2.getAge() ){ //一来就判断是否相等
            return 0;
        } //不相等，则进入三目运算符喽
        return( p1.getAge()<p2.getAge()? -1:1 );
    }
}
