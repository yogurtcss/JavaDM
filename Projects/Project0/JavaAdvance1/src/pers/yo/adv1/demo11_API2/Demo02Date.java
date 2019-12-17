package pers.yo.adv1.demo11_API2;

/* Date类 表示特定的【瞬间】，即【时刻】，精确到 毫秒ms
* 1000毫秒(ms) = 1秒(s)
*
* 毫秒值的作用：可用来 计算时间和日期 (如相隔几天)
* 如： 2099-01-03至2088-01-01 两个日期之间相隔几天？
* 原理：把所有日期统一转换为毫秒 进行运算(加减乘除等)，将所得的结果(毫秒) 又转换回指定的单位(如日期等)
*
*
* 各转换的原理如下：【系统帮我们作的】
* 1.把日期转换为毫秒：即计算 某给定日期 与 具体某时区的“时间原点”(默认的格林尼治时间？中国东八区的时间？) 之间相隔了多少毫秒
*
* 即计算 某给定日期，从1970年1月1日 08:00:00开始走过的毫秒数 (因为我在中国，所以时间原点是 格林尼治时间+8h，因地方的时区而异)
*
* 通常认为 Date类.getTime() 可以得到1970年01月01日0点零分以来的毫秒数，
* 由于我在中国，位于东八区，实际上通过 Date.getTime()得到的是 1970年01月01日8点中以来的毫秒数
*
*   当前给定日期：2088-01-01
*   默认的“时间原点”——英国格林尼治时间：1970年1月1日 00:00:00
*   中国位于东八区，所以中国的“时间原点” 会比 默认的“时间原点”增加 8小时
*   中国的“时间原点”： 1970年1月1日 08:00:00  //会比 默认的“时间原点”增加 8小时
*
*
* ▲ 我们要做的：写代码，调用方法
* 输入日期(字符串)，转化为毫秒数。用DATE方法()
* (1)创建日期格式化规则，然后parse转换：用SimpleDateFormat.parse()方法将日期字符串转化为Date格式
* (2)将格式化后的日期：通过Date.getTime()方法(此方法，见上文的描述)，将其转化为毫秒数。格式如下：
*
*
* DateFormat类是一个抽象类，无法直接new出来使用。因此使用DateFormat的子类SimpleDateFormat
*
* SimpleDateFormat 是一个以国别敏感的方式格式化和分析数据的具体类。
* 导包 import java.text.SimpleDateFormat;
* 构造方法：SimpleDateFormat( String pattern传递指定的模式 )
*
* String pattern传递指定的模式，区分大小写嗷！
* 相当于各日期上的占位符：“年”用“年”的占位符y，“月”用“月”的占位符M
* y 年
* M 月 【大写】
* d 日
* H 时 【大写】
* m 分
* s 秒
*
* 如 2018-01-01 15:05:00，则指定的模式字符串为：
* 模式中的字母不能改，而连接模式的符号可以变
* 如yyyy-MM-dd HH:mm:ss
* 可连接模式的符号可改为：yyyy年MM月dd日 HH时mm分ss秒
*
*
*

        String date = "2001-03-15 15-37-05"; //某给定的日期字符串
        // spDF即 我自定义的 simple Date Format的缩写，原单词真的太长了！！

        // 创建一个格式化规则 的实例化对象oneFormat，等会按照此规则进行日期格式化
        SimpleDateFormat oneFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//24小时制的格式化规则
    //	SimpleDateFormat oneFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");//12小时制的格式化规则

        // SimpleDateFormat类中的静态方法parse()：将字符串类型（java.lang.String）解析为日期类型（java.util.Date）
        // 注意，Date类 原本就 表示特定的【瞬间】，即【时刻】，精确到 毫秒ms
        long time2 = oneFormat.parse(date).getTime(); //紧接着又 getTime()，表示 转换后的日期 从1970-01-01 8:00:00(中国东八区) 走过的毫秒数 【将日期转换为毫秒的关键步骤】
        System.out.println(time2)

*
* 2.把毫秒转换为日期
*   1天 = 24h × 60min × 60s = 86400s = 86400 × 1000ms = 86400 000ms
*
* ▲ 输入毫秒数，转化为日期，用simpleDateFormat.format +  Date方法
* format：传入形参Date类型，将日期类型（Date）数据格式化为字符串（String）
*
* (1)创建格式化规则：SimpleDateFormat的实例对象oneFormat；
* (2)将所得结果rst毫秒 转换为Date类：新建Date实例对象，然后setTime(rst毫秒)
        Date date2=new Date();
        date2.setTime(t1毫秒);

* (3)调用 SimpleDateFormat的实例对象oneFormat.format( 已转换为Date类的实例date ) 【将毫秒转换为日期的关键步骤】
* 注意：Date类 原本就 表示特定的【瞬间】，即【时刻】，精确到 毫秒ms
*
*
*  */


public class Demo02Date {
    public static void main(String[] args) {
        /* System.currentTimeMillis() 返回以毫秒为单位的当前时间，返回的是当前时间与协调世界时1970 年 1 月 1 日午夜之间的时间差
        * 即 current time million百万 is
        *  */
        System.out.println( System.currentTimeMillis() );
    }
}
