package pers.yo.adv1.demo15_Exception;

/* java异常处理的5个关键字：
* throw、throws
* try、catch、finally
*
* -------------------------
* throw关键字：【抛出异常】
* 在指定的方法内部，抛出指定的异常
* 格式：throw new xxxException( "产生异常的原因" );
* 1.【throw关键字 必须写在方法的内部】
* 2.throw或throws关键字 后 new的对象 必须是Exception 或Exception的子类对象
* 3.throw或throws关键字 抛出的异常对象 必须要 “被处理” 嗷！！
*
*
* 当 throw 语句执行时，它后面的语句将不执行，
* 此时程序转向调用者程序，寻找与之(此异常对象)相匹配的 catch语句，执行相应的异常处理程序。
* 如果没有找到相匹配的 catch 语句，则再转向上一层的调用程序。
* 这样逐层向上，直到最外层的异常处理程序终止程序并打印出调用栈情况。
*
*
* --------------------------
* throws关键字：【声明异常，而不处理异常】
* 在方法头声明，表示：此方法不处理异常，而提醒该方法的调用者来处理异常；格式为：
* 修饰符 返回值类型 方法名(参数) throws 异常类名1, 异常类名2, ...{
*     //...
* }
* throws的注意事项见上
* 1.【throws关键字 必须写在方法声明处】
* 2.throw或throws关键字 后 new的对象 必须是Exception 或Exception的子类对象
* 3.throw或throws关键字 抛出的异常对象 必须要 “被处理” 嗷！！
*
* --------------------------
* throw 与 throws 的比较
1、throws 出现在方法函数头；而 throw 出现在函数体。
2、throws 表示出现异常的一种可能性，并不一定会发生这些异常；throw 则是抛出了异常，执行 throw 则一定抛出了某种异常对象。
*
* --------------------------
* 捕获异常 try...catch...finally
* try{
*    编写可能会出现异常的代码
* }
* catch( 异常类型 e ){ //当产生异常时，执行catch中的异常处理逻辑
*    处理异常的代码
*     //记录日志、打印异常信息、继续抛出异常
* }
* finally{ //无论异常是否发生，finally语句块中的内容都一定被执行
*    //什么代码必须最终执行? 释放资源
*    如：在try语句块中打开了一些物理资源(磁盘文件/网络连接/数据库连接等)，
*    当我们使用完后，最终需要在finally块中 关闭(释放)资源
* }
*
* 注：1.finally不能单独使用
* 2.【正常规范】 finally中是不应该有返回值，也不应该有return/throw关键字
*    如果在finally中使用return，则会吃掉catch中抛出的异常(即不会抛出异常)，且永远返回finally中的结果


在 try-catch-finally 语句块中，finally 语句块中的 return / 抛出异常（立即结束语句）的优先级最高，
程序会优先返回 finally 语句块中的立即结束语句的结果，
此时 try-catch 语句块中的 return / 抛出异常（立即结束语句）的结果就会被丢弃掉。


* --------------------------
* 多个异常，如何捕获？
* 1.多个异常分别处理
* 2.多个异常 一次捕获(try)，多次处理(try后紧接着多个catch) 【常用】
* 3.多个异常 一次捕获，一次处理
*
*
* 编写多重 catch 语句块注意事项：
* 顺序问题：先小后大，即先子类异常后父类异常
* 否则，若 “先大后小”，则会发生 “异常屏蔽” 现象
*
* 尽量将捕获底层异常类的 catch 子句放在前面，
* 同时尽量将捕获相对高层的异常类的 catch 子句放在后面。
* 否则，捕获底层异常类的 catch 子句将可能会被屏蔽。
*
* 【异常屏蔽】这就好比你用两个盆子接水，大盆子(父类异常)在上，小盆子(子类异常)在下，那小盆子自然就一滴水也接不到。
*
*
* 多重 catch 语句块，格式：
* try{
* }
* catch( 异常类型A e ){ //孙类异常
* }
* catch( 异常类型B e ){ //子类异常
* }
* catch( 异常类型C e ){ //父类异常
* }
* finally{
* }
*
*
* -------------------------
* 子类、父类的异常
1.子类在继承【父类】时，如果父类的方法抛出异常，那么子类的覆盖方法只能抛出父类的异常或者该异常的子类。
2.如果【父类方法】抛出多个异常，子类在覆盖该方法时，只能抛出父类异常的子类。
3.如果父类或者接口的方法中没有异常抛出，子类在覆盖方法时，也不可以抛出异常。
如果子类方法发生了异常，就必须要进行 try 处理，绝对不能抛。

-------------------------
自定义异常类：
* java提供的异常类不满足我们使用，需自定义一些异常类
* 格式：
public class xxxException extends Exception 或 RuntimeException{
    添加一个空参数的构造方法
    添加一个【带异常信息】的构造方法
}

* 若自定义异常类X继承Exception，则此X是一个编译期异常；
若方法内部抛出了编译期异常，则必须处理这个异常：要么在方法头声明throws(声明异常)，要么try...catch...(手动解决异常)
* 若自定义异常类X继承RuntimeException，则此X是一个运行期异常；调用者无需处理此【运行期异常】

▲ 凡是 RuntimeException 及其子类的异常都不处理 (不抛出也不捕获)，
如果真的知道运行时会有异常抛出的话，，那么直接检查修改程序的逻辑就 OK！！！

*
*  */

/*
* 假设已有Object实例对象分别为 obj1、obj2
* obj1.equals(obj2); //判断 我调用者(实例对象obj1) 与 另一个实例对象obj2 与 的地址值是否相等
* 返回判断结果：boolean值
*
* 若obj1、obj2是基本数据类型，则比较“值”
* 若obj1、obj2是引用类型，则比较“地址值”
*
*  */

import pers.yo.adv1.demo15_Exception.RegisterException;
import java.util.Scanner;

public class Demo01Exception {
    //有static修饰的是静态方法(可全局调用)，而不是成员方法
    public static void checkUserName( String[] db, String oneUser ) throws RegisterException { //声明可能抛出的异常
        for( String oneData : db ){
            if( oneData.equals(oneUser) ){ //if语句中的条件：oneData==oneUser 或 oneData.equals(oneUser)
                throw( new RegisterException("您好，当前用户名已被注册，请宁换另一个用户名嗷~") ); //抛出异常
            }
        }
        System.out.println( "此用户名妹有被占用，可注册嗷！" );
    }

    public static void main(String[] args) throws RegisterException {
        String[] db = new String[]{ "小明","小花","啊啊" };
        Scanner sc = new Scanner( System.in );
        System.out.println( "请输入您的用户名嗷！" );
        String oneUser = sc.next();
        checkUserName( db, oneUser );
    }
}
