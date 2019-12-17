package pers.yo.adv1.demo22_FuncITF.Demos;

/* 函数式接口作为方法参数
*
* 函数时接口作为方法的返回值类型
*
*  */


/* Comparator接口，比较器
* 用法：定义一个实现类A，去实现 Comparator接口，重写其中的 compare 方法；
* 这样就得到了一个【自定义的比较器】A  (注：A是此比较器的实现类，需创建实例对象才能调用compare方法)
* 然后 使用 【接口的实现类A的实例对象】调用重写的 compare方法即可进行比较
*  */
import java.util.Comparator;
/* Comparator 接口可以实现自定义排序，实现 Comparator 接口时，要重写 compare 方法：
int compare (Object o1, Object o2) 返回一个基本类型的整型
如果要按照升序排序，则 o1小于o2，返回 -1（负数），相等返回0；    o1大于o2返回 1（正数）
如果要按照降序排序，则 o1小于o2，返回 1（正数）， 相等返回0；    o1大于o2返回 -1（负数）
*  */

public class Demo01_FuncITF {
    /* 定义一个静态方法：以 函数式接口FuncITF1 的实现类的实例对象 作为形参
    *  */
    public static void show( FuncITF1 funcITF1 ){ //以 函数式接口FuncITF1 的实现类的实例对象 作为形参
        /* 问：谁调用接口中的方法？
        * 答：接口的实现类的实例对象！(在这里是funcITF1)
        * 所以，传入形参为 接口的实现类的实例对象！！
        *  */
        funcITF1.method(); //接口的实现类的实例对象调用 它实现的接口中的方法
    }

    //方法返回值为 泛型接口时
    //【改进2】：使用匿名内部类
    //【改进3】：使用lambda表达式
    public static Comparator<Person> cmp_Lambda(){
//        return( new Comparator<Person>(){
//            public int compare( Person p1, Person p2 ){
//                /* 按自定义的条件，必须返回-1、0、1三个值！！
//                 * 若只用三目运算符返回了-1、1，却没返回0(相等情况)，则会报错
//                 * 故需特别留意 相等的情况！！
//                 *  */
//                if( p1.getAge()==p2.getAge() ){
//                    return 0;
//                } //不相等，则进入三目运算符喽
//                return( p1.getAge()<p2.getAge()? -1:1 );
//            }
//        } );

        return( (Person p1, Person p2)->{ // lambda表达式写法
            /* 按自定义的条件，必须返回-1、0、1三个值！！
             * 若只用三目运算符返回了-1、1，却没返回0(相等情况)，则会报错
             * 故需特别留意 相等的情况！！
             *  */
            if( p1.getAge()==p2.getAge() ){
                return 0;
            } //不相等，则进入三目运算符喽
            return( p1.getAge()<p2.getAge()? -1:1 );
        } );

    };


    public static void main(String[] args) {
        System.out.println( "函数式接口作为方法参数" );
        //最初形态：传入接口的实现类的实例对象
        show( new Impl_FuncITF1() );
        //改进形态：使用 接口的匿名内部类的实例对象
        show( new FuncITF1(){
            public void method(){
                System.out.println( "改进形态：使用 接口的匿名内部类的实例对象。我重写了接口的method方法！" );
            }
        } );
        //增强形态：使用【标准】lambda表达式。我重写了接口的method方法！
        show( ()->{
            System.out.println("增强形态：使用【标准】lambda表达式。我重写了接口的method方法！");
        } );
        //究极形态：使用【简化】lambda表达式——单语句时省略花括号。我重写了接口的method方法！
        show( ()-> System.out.println("究极形态：使用【简化】lambda表达式。我重写了接口的method方法！") );
        System.out.println( "----------------------" );

        //准备比较的实例对象
        Person p1 = new Person( "小明", 1 );
        Person p2 = new Person( "哈哈", 2333 );
        System.out.println( "函数值接口作为方法返回值" );
        //【最初形态】创建 自定义比较器实现类的 实例对象cmp
        PerAgeCmp cmp = new PerAgeCmp();
        //调用比较器实现类的实例对象cmp中 重写的compare方法
        int rst = cmp.compare( p1,p2 );
        System.out.println( "最初形态。自定义比较器实现类 实例对象cmp，并调用此cmp中 重写的compare方法："+rst );

        /* 当我们创建或声明泛型类实例，
        * 或是创建泛型接口的实现类的实例，
        * 或是声明泛型接口类型的实例时，需要给泛型参数指定一个实际类型的参数，
        *  */

        //【改进形态：直接创建法。没用到“方法返回值为接口”的写法】实现类为匿名内部类，由它创建实例对象cmp_Anonymous
        Comparator<Person> cmp_Anonymous = new Comparator<Person>(){ //这里编译器提示我用lambda表达式了……
            /* 等号左边的 Comparator<Person> 好理解：创建时，必须指定泛型的类型(在这里为Person)；
            * 等号右边的 new Comparator<Person>()，为啥也要 指定泛型的类型为Person？不能留空吗？
            * 答：这种 以匿名内部类为实现类的写法，本质上是：
            * public class 实现类名 implements Comparator<Person> 的写法
            *
            * 最原始、最基本的原则：实现泛型接口时，就要指定泛型的类型！！
            * 所以，当你在实现一个具体的泛型接口时，在写匿名内部类时也必须要 指定泛型的类型！ 确实如此。
            * 表明你这 实现类 是【实现什么类型的】的泛型接口
            * 在这里，我是 实现 【比较Person类型】 的泛型接口
            *  */
            public int compare( Person p1, Person p2 ){
                /* 按自定义的条件，必须返回-1、0、1三个值！！
                * 若只用三目运算符返回了-1、1，却没返回0(相等情况)，则会报错
                * 故需特别留意 相等的情况！！
                *  */
                if( p1.getAge()==p2.getAge() ){
                    return 0;
                } //不相等，则进入三目运算符喽
                return( p1.getAge()<p2.getAge()? -1:1 );
            }
        };
        int rst2 = cmp_Anonymous.compare( p1,p2 );
        System.out.println( "改进形态。【以匿名内部类作为泛型接口的实现类】的写法："+rst2 );
        System.out.println( "记住：最原始、最基本的原则：实现泛型接口时，就要指定泛型的类型！！" );

        int rst3 = cmp_Lambda().compare( p1,p2 );
        System.out.println( "究极形态：匿名内部类写法 或 lambda表达式："+rst3 );


    }


}
