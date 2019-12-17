package pers.yo.adv1.demo26_Reflect;

// java.lang.Class 无需import
import pers.yo.adv1.demo26_Reflect.Person;

/* .Class 类 “类型”
1. 在第一阶段——Source源代码阶段：Class.forName("全类名：即包名.类名")
   - Class类中的静态方法forName
   - 手动地 将字节码文件加载进内存，返回class对象
   - 需要处理 ClassNotFoundException；可以不处理然后throws声明一下即可
   - 使用情景：多用于：当配置文件时，将类名定义在配置文件中；读取此配置文件中的“全类名(包名.类名)”，把Class类对象加载进来

   -注：利用反射Class.forName(...)加载一个类 (获得一个 【类_类型即Class类型】 的 实例对象),
        并执行初始化：使得 【类中的静态变量会被初始化，静态代码块也会被执行】。

2. 在第二阶段——Class类对象阶段（此时字节码文件已在内存中了）：类名.class属性
   - 通过类名的属性class获取，返回class对象
   - 使用场景：多用于 参数的传递
3. 在第三阶段——Runtime运行时阶段（此时已经创建某个类的实例对象了）：任意实例对象.getClass()
   - 此getClass() 方法 已在Object类中定义了，任何实例对象（默认继承了Object类）都拥有这个方法
   - 使用场景：当已创建某类的实例对象时，可用此方法来获取字节码文件
*  */


public class Demo01Reflect {
    public static void main(String[] args) throws ClassNotFoundException { //调用了Class.forName()，需要声明的异常
        //一般不需指定 Class对象的泛型
        Class cls1 = Class.forName( "pers.yo.adv1.demo26_Reflect.Person" ); //Class.forName("全类名：包名.类名")
        System.out.println( cls1 );

        Class cls2 = Person.class; //类名.class属性
        System.out.println( cls2 );

        Person p = new Person();
        Class cls3 = p.getClass(); //任意实例对象.getClass()
        System.out.println( cls3 );

        //比较cls1、cls2、cls3这三个实例对象的【内存地址】
        System.out.println( cls1==cls2 );
        System.out.println( cls1==cls3 );
        System.out.println( cls2==cls3 );
        /* 结论
        * 在某一次程序运行过程中，同一个字节码文件（*.class）只会被加载一次，
        * 无论通过（以上三种方式中的）哪一种方式 获取的Class对象，都是相同的。
        * 即：用这三种方法中 其一即可。
        *  */

    }
}
