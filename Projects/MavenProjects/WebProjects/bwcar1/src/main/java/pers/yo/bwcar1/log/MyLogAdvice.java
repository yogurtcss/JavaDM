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

@Aspect
@Component
public class MyLogAdvice { //自定义日志类，以获取用户的操作记录：可用于推荐算法！

    //注意，这个Logger类是来自 org.apache.log4j.Logger 这个包啊！
    private Logger logger = Logger.getLogger( MyLogAdvice.class );

    @Pointcut( "@annotation(pers.yo.bwcar1.log.MyLog)" )
    public void myPointcut(){

    }

    //开发通知
    @AfterReturning( pointcut="myPointcut()" )
    public void myAfterRet( JoinPoint joinPoint ){
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        MyLog annotation = method.getAnnotation( MyLog.class );
        String operation = null; //自定义注解@MyLog的值，就是 用户的操作
        if( annotation!=null ){
            operation = annotation.value();
        }
        //获取用户的IP
        String ip = IPUtils.getIpAddr( HttpContextUtils.getHttpServletRequest() );
        Object[] args = joinPoint.getArgs();
        String toJSONString = JSON.toJSONString(args); //将args转换为JSON字符串
        String methodName = joinPoint.getTarget().getClass().getName()+"."+method.getName();
        logger.info( new Date()+"|"+operation+"|"+ip+"|"+toJSONString+"|"+methodName );
    }
}
