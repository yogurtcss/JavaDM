package pers.yo.bwcar1.log;


import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import pers.yo.bwcar1.utils.HttpContextUtils;
import pers.yo.bwcar1.utils.IPUtils;

import java.lang.reflect.Method;
import java.util.Date;

/* 2020-02-10 21:17:44
* Spring Aop 实现用户操作日志记录
* 太难了，暂且放下！
*  */

//定义一个切面类，加上 @Aspect、@Component 这两个注解
@Aspect //把当前类标识为一个切面类(或称 方面Bean)
//使用 @Aspect 注解的类， Spring将会把它当作一个特殊的Bean（一个切面），也就是 不对这个类本身进行动态代理
@Component //让此切面类成为 Spring容器 管理的Bean
public class MyLogAdvice { //自定义日志类，以获取用户的操作记录：可用于推荐算法！

    //注意，这个Logger类是来自 org.apache.log4j.Logger 这个包啊！
    //Logger 类不允许初始化一个新的实例，但提供了静态方法用来获取Logger对象
    private Logger logger = Logger.getLogger( MyLogAdvice.class ); //获取记录日志的工具类实例对象logger

    /* @Pointcut( "以切点指示符开始的切入点表达式" ) 定义切点 【或自定义切点】
    * 以告诉 AOP 什么时候启动拦截并织入对应流程
    *
    * 切点指示符@annotation( ……指定注解所在的全限定类名-包名.类名……)：匹配那些具有指定注解的连接点
    *  */
    @Pointcut( "@annotation(pers.yo.bwcar1.log.MyLog)" ) //匹配具有【指定的自定义注解MyLog】的连接点
    //切点指示符@annotation( ……指定注解所在的全限定类名-包名.类名……)
    public void myPointcut(){ //使用注解方式：自定义的切点 = @pointcut(切点表达式) + 空方法-方法名是切点的名
    }
    /* 之后的流程：
    * 具有自定义注解@MyLog的方法X(连接点X) 被当做一个切点，
    * 当程序执行(上面所说)这个方法X 的时候对它进行拦截，
    * 这样就能按照 AOP 通知的规则把这个方法X进行“织入”
    *
    * ▲ 切面Aspect = 通知Advice + 切点Pointcut
    * ▲ 织入Weaving：把切面Aspect应用到目标对象，来创建新的代理对象的过程
    *  */

    /* 通知 Advice：拦截到 连接点JoinPoint之后(或之前) 所要做的事情就是通知
    * 返回后通知@AfterReturning( pointcut="某个切入点表达式pt1()" )，
    *   - 指定被拦截的连接点(切入点表达式)，然后写通知的方法(拦截后要做什么)
    *  */
    //返回后通知@AfterReturning：在某连接点join point 正常执行后的通知。例如，一个方法没有抛出任何异常，正常返回
    @AfterReturning( pointcut="myPointcut()" ) //注解配置AOP中：引用切点时要加上小括号！——因为此时的切点相当于一个(空)方法
    public void myAfterRet( JoinPoint joinPoint ){
        /* signature 签名；而 methodSignature就是方法签名
        *
        *  */
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature(); //获取连接点的方法签名对象
        //System.out.println( "方法签名对象是什么？→"+methodSignature );
        /* 方法签名对象是什么？→  DataGridResult pers.yo.bwcar1.controller.MenuController.findMenu(QueryDTO)
        * 其中，方法签名就是这一段 【全限定类名.方法(形参)】：pers.yo.bwcar1.controller.MenuController.findMenu(QueryDTO)
        *  */
        //获取方法签名对象中的 方法对象method (在这里是获取 【名为findMenu的】方法对象method)
        Method method = methodSignature.getMethod();
        //从方法对象method中，获取【加在该方法上的】指定注解对象 annotation
        MyLog annotation = method.getAnnotation( MyLog.class ); //传入形参为 指定注解.class 类_类型
        String operation = null;
        if( annotation!=null ){
            operation = annotation.value(); //自定义注解@MyLog的值-value属性，就是 用户的操作
        }
        //获取用户的IP
        String ip = IPUtils.getIpAddr( HttpContextUtils.getHttpServletRequest() );
        Object[] args = joinPoint.getArgs(); //Object[] 连接点对象.getArgs()  返回【被拦截进行通知】的方法的参数列表
        String toJSONString = JSON.toJSONString(args); //将args转换为JSON字符串

        /* 2020-02-11 15:54:28   链式调用，真的佛了
        * 连接点(即 方法)所在的目标对象X = joinPoint.getTarget(); //获取 连接点(即 方法)所在的目标对象X
        * 连接点(即 方法)所在的目标对象X.getClass();  //获取 ...目标对象X的class类_类型实例对象
        * 连接点(即 方法)所在的目标对象X.getClass().getName(); //从 ...目标对象X的class类_类型实例对象中，获取此class对应的【原本类 的名字】
        *  */

        //获取 执行此连接点(即方法)的【类的名字】
        String methodName = joinPoint.getTarget().getClass().getName()+"."+method.getName();
        /* public void info(Object message) ：Logger类实例对象.info(Object message)
        * 使用【Level.INFO 级别】写日志(输出到本地文件.log中)。
        *
        * 通过 log4j.properties 配置文件，可设置 将日志输出到本地文件XXX.log中
        *  */
        logger.info( new Date()+"|"+operation+"|"+ip+"|"+toJSONString+"|"+methodName );
        //2020|菜单列表|0:0:0:0:0:0:0:1|[{"limit":10,"offset":0,"order":"asc"}]|pers.yo.bwcar1.controller.MenuController.findMenu
    }
}
