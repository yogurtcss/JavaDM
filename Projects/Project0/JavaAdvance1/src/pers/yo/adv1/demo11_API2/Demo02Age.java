package pers.yo.adv1.demo11_API2;

import java.text.ParseException; //使用 SimpleDateFormat的parse()方法时，必须加上的异常处理
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat; //日期格式化


public class Demo02Age {
    public static void main(String[] args) throws ParseException { //使用 SimpleDateFormat的parse()方法时，必须抛出的异常处理
        Scanner sc = new Scanner( System.in ); //获取键盘输入
        System.out.println( "请按 yyyy-MM-dd 的格式输入一个出生日期字符串" );
        String birthStr = sc.next(); //获取出生日期字符串

        SimpleDateFormat oneFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        //将输入的出生日期字符串先转换为Date类型，然后getTime() 转换为 毫秒
        long birthMs = oneFormat.parse( birthStr ).getTime();

        long currTime = System.currentTimeMillis(); //获取当前系统时间(从 1970-01-01 走过的毫秒数)
        long rstMs = currTime - birthMs; //当前日期 与 出生日期 之差


        /* 将rstMs转换为天数： rstMs/1000s/60min/60h/24 天
        *  */
        System.out.println( "您已经出生了："+rstMs/1000/60/60/24+" 天" );

    }
}
