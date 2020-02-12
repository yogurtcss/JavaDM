package pers.yo.bwcar1.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( ElementType.METHOD )
//@Target注解：决定自定义注解可以加在哪些成分上，
@Retention( RetentionPolicy.RUNTIME )
//@Retention注解：让自定义注解的生命周期一直程序运行时都存在
public @interface MyLog {
    String value() default "用户操作";
}
