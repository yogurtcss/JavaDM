package pers.yo.adv1.demo26_Reflect;

/* 需要导入包 import java.lang.reflect.Field; 为什么？

因为 Field 并不是 java.lang 包下的直接子类
而是 java.lang.reflect 包下的子类，所以需要导包，
只有 lang 包下的直接子类不需要导包例如：java.lang.String 就不需要导包


 Class对象功能：
 * 获取功能：
 1. 获取成员变量们
 * Field[] getFields()
 * Field getField(String name)

 * Field[] getDeclaredFields()
 * Field getDeclaredField(String name)

 2. 获取构造方法们： 一般的构造方法，不写泛型，直接省略
 * Constructor<?>[] getConstructors()
 * Constructor<T> getConstructor(类<?>... parameterTypes)

 * Constructor<T> getDeclaredConstructor(类<?>... parameterTypes)
 * Constructor<?>[] getDeclaredConstructors()

 3. 获取成员方法们：
 * Method[] getMethods()
 * Method getMethod(String name, 类<?>... parameterTypes)

 * Method[] getDeclaredMethods()
 * Method getDeclaredMethod(String name, 类<?>... parameterTypes)

 4. 获取类名
 * String getName()

 */

import pers.yo.adv1.demo26_Reflect.Person;

import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Demo02Field_Cons_Method {
    // 可能会声明许多异常：如 throws NoSuchFieldException, IllegalAccessException
    public static void main(String[] args) throws Exception { //可能会声明许多小的异常，这里利用“异常屏蔽”，直接声明 大异常
        Class perClass = Person.class; //获取Person类的 Class类对象
        Field[] allPublic = perClass.getFields(); //所有公共的成员变量，放在Field数组中
        System.out.println( allPublic[0] ); //输出 pub1变量
        //完整的输出：public java.lang.String pers.yo.adv1.demo26_Reflect.Person.pub1

        System.out.println( "----------------------------------Field测试" );
        Field pub1_temp = perClass.getField( "pub1" ); // temp即 中间实例，仅代表这个变量
        System.out.println( pub1_temp ); //输出的是 代表pub1变量的 Field实例对象 pub1_temp，可以理解为“中间实例”
        Person p1 = new Person();
        pub1_temp.set( p1, "public公有变量pub1" );
        System.out.println( pub1_temp.get(p1) );

        Field name_temp = perClass.getDeclaredField("name"); //不考虑修饰符，获取private或protected的成员变量
        name_temp.setAccessible( true ); //开启暴力反射
        name_temp.set( p1, "快，对我name“暴力反射”！！" ); //当需要访问/修改 private或protected的成员变量之前，需要开启暴力反射
        System.out.println( name_temp.get(p1) );

        System.out.println( "----------------------------------Constructor测试" );
        Constructor consVoid_temp = perClass.getConstructor(); //获取空参构造方法的 “中间实例”consVoid_temp
        System.out.println( consVoid_temp );
        Object oVoid1 = consVoid_temp.newInstance(); //利用空参构造方法，创建实例对象o_void1，可考虑向下转型为 Person类
        System.out.println( oVoid1 instanceof Person ); //oVoid是不是Person类的 实例对象呀？ true
        //利用空参构造方法，创建实例对象的简化写法
        Object oVoid2 = perClass.newInstance(); //Person类Class对象.newInstance(空参)

        //获取的是 public Person(String name, int age) 构造方法 对应的 中间实例cons1
        Constructor cons1_temp = perClass.getConstructor( String.class, int.class );
        Object o1 = cons1_temp.newInstance( "小王", 23 ); //传入真正的参数
        Person o1_per = (Person)o1; //向下转型为 Person类
        System.out.println( o1_per.getName() );

        System.out.println( "----------------------------------Method测试" );
        //注意，方法名只是名字，没有小括号！！没有小括号！！
        Method md1_temp = perClass.getMethod("print"); //原方法没有传入形参
        Person p2 = new Person(); //随便新建一个实例对象
        md1_temp.invoke( p2 ); //调用 md1_temp实例对象所表示的(print)方法，传入形参为 此方法所属的真正的实例对象p2

        Method md2_temp = perClass.getMethod( "getName" ); // 让 md2_temp 代表成员方法getName，原方法没有传入形参
        System.out.println( md2_temp.invoke(p1) ); //调用p1实例对象的 getName方法

        //通过方法名和形参列表，获得特定的成员方法
        Method md3_temp = perClass.getMethod( "setName", String.class ); //原setName方法需要传入形参 String name
        md3_temp.invoke( p2, "我是md3_temp的invoke方法设置进来的嗷！" ); //调用p2的setName方法
        //已知 md2_temp 代表成员方法getName，原方法没有传入形参
        System.out.println( md2_temp.invoke(p2) ); //调用p2的getName方法

        System.out.println( "----------------------------------获取类名的测试" );
        String className = perClass.getName(); // 用法：类对象.getName();
        System.out.println( className );
    }
}
