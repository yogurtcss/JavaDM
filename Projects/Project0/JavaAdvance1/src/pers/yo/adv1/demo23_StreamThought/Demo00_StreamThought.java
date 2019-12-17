package pers.yo.adv1.demo23_StreamThought;

/* Stream Thought 流式思想
得益于lambda表达式带来的函数式编程，“流”的思想得到扩展：
“流”不仅仅指的是 IO流了

流是以声明的形式操作集合，它就像 SQL 语句，
我们只需告诉流需要对集合进行什么操作，它就会自动进行操作，并将执行结果交给你，无需我们自己手写代码。

----------------------------------------------------------
Java 8 API 添加了一种新的机制 ——Stream（流）。Stream 和 IO 流不是一回事。
* 流式思想：像生产流水线一样，一个操作接一个操作。
* 使用Stream流的步骤： 数据源 → 【转换成流】 → 操作1 → 操作2→……
* 数据源（source）：可以是集合、数组等。

Stream 操作有两个基础特征：
* Pipelining（流水线）：流操作会返回流对象（新的对象），以便后续继续进行流操作；
    只能被消费(使用)一次，当第一个Stream流调用完毕时，数据就会跑到下一个Stream中；
    第一个Stream流已经使用完毕，就会关闭了；

* 内部迭代：不需要像 for 循环或 Iterator 一样进行显式的迭代。

-----------------------------------------------------------
java.util.stream.Stream<T> “流”的接口
Stream流 表示：应用在一组元素上 某一次的 【执行的操作序列】

注意，流的数据类型是泛型！！

Stream操作分为中间操作或者最终操作两种：
* 中间操作返回Stream本身；这样我们就可以将多个操作依次串起来【以下详解】；
* 最终操作返回一特定类型的计算结果；
当最终操作发生时，则这整个操作序列结束。

Stream 的创建需要指定一个数据源，比如 java.util.Collection 的子类，List或者Set。
Stream 的操作可以串行执行或者并行执行。

-----------------------------------------------------------
一、Stream操作中的 【将数据源转换成“流”的方法】 ▲ 注意，流的数据类型是泛型！！分类讨论如下：
1.【实例对象】 stream()方法
* 集合类的实例对象.stream() 获取集合的实例对象的流对象
List<Person> list = new ArrayList<Person>();
Stream<Person> stream = list.stream();

* 工具类Arrays.stream(数组arr) 获取数组arr的流对象
String[] names = {"chaimm","peter","john"};
Stream<String> stream = Arrays.stream(names);

2.【基本数据类型的值】：Stream类的静态方法  Stream.of(值1, 值2,...)  直接将几个值变成流对象
Stream<String> stream = Stream.of("chaimm","peter","john");

-----------------------------------------------------------
▲ 将“流”的实例对象 转换为 其它数据结构
1. “流” 转换为原本的集合Collection
需要导包java.util.stream.Collectors

List<String> list1 = stream流实例对象.collect( Collectors.toList() );
List<String> list2 = stream流实例对象.collect( Collectors.toCollection(ArrayList::new) );
Set set1 = stream流实例对象.collect( Collectors.toSet() );
Stack stack1 = stream流实例对象.collect( Collectors.toCollection(Stack::new) );

2. “流” 转换为原本的数组Array
String[] strArray1 = stream流实例对象.toArray(String[]::new);

3. “流” 转换为 【原本的数据类型 所对应的字符串】String
String str = stream流实例对象.collect(Collectors.joining()).toString();

----------------------------------------------
二、Stream操作中的【中间操作的方法】——衔接操作返回数据流
1.Stream<T> filter(Predicate<? super T> predicate)
过滤：通过一个 predicate接口 来过滤并只保留符合条件的元素，返回值为流对象Stream<T>

2.Stream<T> sorted (Comparator<? super T> comparator)
排序：通过 一个 Comparator接口 对一组数据进行排序

3.<R> Stream<R> map(Function<? super T, ? extends R> mapper)
映射：按照所给的 Function接口 依次将本流中的元素变换为另一种类型 (将A流中的元素，映射到B流中)
返回值为 流对象<R> Stream<R>

4.Stream<T> limit(long maxSize)
截取：截取本“流”中 (按顺序的)前maxSize个元素；
如果元素的个数小于 maxSize，那就获取所有元素(我只有2个元素你要截断4个？相当于没截断)。
返回值为 截断后的元素组成的新的 “流”对象Stream<T>

5.Stream<T> skip(long n)
跳过：如果此“流”的当前长度大于n，则跳过前n个元素，返回 【跳过前n个元素】 构成的流对象；
否则将会得到一个长度为0的空的流对象。

6. Stream类中的静态方法：Stream.concat
static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)
组合：把流A和流B组合为一个新的流，返回组合后的流对象


三、Stream操作中的【最终操作的方法】——终止操作无返回值，或者返回一个不是流的结果
1.void forEach(Consumer<? super T> action)：接收一个Consumer函数式接口con1，将 “流” 中每一个元素交给con1进行“消费”(处理)

2.Match匹配：判断 Stream 中的数据是否满足 Predicate接口指定的规则。有如下3个匹配方法：
* anyMatch() 任意匹配：     只要  Stream中有一个元素满足要求    就算匹配成功
* allMatch() 完全匹配：     只有  Stream中的全部元素满足要求    才算匹配成功
* noneMatch() 完全不匹配：  当Stream中没有一个元素满足要求      是匹配成功

3.long count() 计数：返回 Stream 中经过多个中间操作后【剩余的元素的个数】，返回值为 long长整型

4.reduce() 规约：将集合中的所有元素经过指定运算，折叠成一个元素输出，
如：求最值、平均数等，这些操作都是将一个集合的元素折叠成一个元素输出。

reduce()方法接收两个参数：
(1)初始值；
(2)进行归约操作的Lambda表达式

-----------------------------------------------------------

*  */

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* List 接口的主要实现类有：
1.java.util.ArrayList
2.java.util.LinkedList  //包含了许多操作首位元素的方法：增删改查
*  */

public class Demo00_StreamThought {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(); //上转型
        list.add( "小明" );
        list.add( "大师兄" );
        list.add( "佛了" );
        list.add( "哈哈哈" );
        list.add( "嘻嘻嘻" );


        //将数据源转换为 流对象
        Stream<String> listStream = list.stream();
        /* 对listA集合进行过滤,只取出姓名长度为3的人,存储到一个新集合list2中；
        * listStream流对象.filter( s->s.length()==3 )：流对象中筛选出长度==3的元素，数据类型仍为流对象；
        * 紧接着又把 长度==3的元素的流对象  转为集合List：
        * 集合List中仅有 筛选通过的长度==3的元素
        *  */
        List<String> list2 =  listStream.filter( s->s.length()==3 ).collect( Collectors.toList() );
        for( String one : list2 ){
            System.out.println( one );
        }
        System.out.println( "-------------【衔接方法】filter方法，返回值为Stream流对象" );

        /* 字符串中，查找以XX开头 或 以XX结尾的方法
        *
        * public boolean startsWith( String prefix前缀-必须  [, int toffset字符串中开始查找的位置，可选的] )
        * 检测字符串是否以指定的前缀开始
        *
        * public boolean endsWith(String suffix后缀)
        * 检测字符串是否以指定的后缀结束
        *
        *  */
        Stream.of( "张三","哈哈","嗯冲","啦啦啦","嗯嗯嗯" ).filter( s->s.startsWith("哈") ).forEach( s -> System.out.println(s) );
        //Stream属于 管道流，只能被消费(使用)一次；相当于“流水线”工作
        System.out.println( "-------------【衔接方法】map方法，返回值为Stream流对象" );
        Stream.of( "0","1","2","3" ).map( s -> Integer.parseInt(s)+10 ).forEach( i -> System.out.println(i) );

        System.out.println(  "-------------【衔接方法】limit方法，返回值为Stream流对象" );
        /* 截取：截取本“流”中 (按顺序的)前maxSize个元素；
        *  如果元素的个数小于 maxSize，那就获取所有元素(我只有2个元素你要截断4个？相当于没截断)。
        *  返回值为 截断后的元素组成的新的 “流”对象Stream<T>
        *  */
        Stream.of( "1","2","3","4","5" ).limit(3).forEach( s -> System.out.println(s) ); //按顺序输出前3个
        //如果元素的个数小于 maxSize，那就获取所有元素(我只有2个元素你要截断4个？相当于没截断)
        Stream.of( "1","2" ).limit(10).forEach( s -> System.out.println(s) );

        System.out.println( "-------------【衔接方法】skip方法，返回值为Stream流对象");
        Stream.of( "1","2","3","4","5" ).skip(3).forEach( s -> System.out.println(s) ); //跳过前3个
        Stream.of( "1","2" ).skip(10).forEach( s -> System.out.println(s+"没有输出") ); //得到一个空的流对象，没有输出

        System.out.println( "-------------【衔接方法】静态方法concat方法，返回值为Stream流对象");
        Stream.concat( Stream.of("哈哈哈"), Stream.of("嘻嘻嘻") ).
                filter( s -> s.length()==3 ).forEach( s-> System.out.println(s) ); //组合两个流

        System.out.println( "-------------【终结方法】count方法，返回值为long长整型" );
        long rst = Stream.of( "张三","哈哈","嗯冲","啦啦啦","嗯嗯哦哦","哦" ).filter( s->s.endsWith("哦") ).count();
        System.out.println( "符合条件的元素有："+rst+" 个嗷！" );

    }
}