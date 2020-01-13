package demo02_MyDesign.mybatis.annotation;

        /* 2020-01-13 10:05:08
         * IDEA如何新建注解？
         * 对着某个包，右键 - 新建Java类 - 选择 Annotation
         *  */

        import java.lang.annotation.ElementType;
        import java.lang.annotation.Retention;
        import java.lang.annotation.RetentionPolicy;
        import java.lang.annotation.Target;

/* (自定义的)注解的 注解，称为元注解

@Retention注解 决定此自定义的注解(@Select)的生命周期
Enum RetentionPolicy 是一个枚举类型，这个枚举决定了 Retention 注解应该如何去保持
- RetentionPolicy.RUNTIME：注解不仅被保存到class文件中， 而且在jvm加载class文件之后，仍然存在；

@Target注解 决定此自定义的注解(@Select)可以加在哪些成分上，
如加在类身上，或者属性身上，或者方法身上等成分

*  */

/* 为注解增加属性：即 public @interface Select { ...//为注解增加属性  } 花括号里的是注解的属性
注解可以看成是一种特殊的类，既然是类，那自然可以为类添加属性

▲ 自定义注解的花括号中，添加属性的语法：
类型 属性名();

其实从代码的写法上来看，注解更像是一种特殊的接口，
注解的属性定义方式就和接口中定义方法的方式一样，
而应用了注解的类可以认为是实现了这个特殊的接口

▲ 添加 value 属性
如果一个注解中有一个名称为 value的属性，【而且】你只想设置一个 value属性  —— 即 【其他属性都采用默认值】 或者 【你只有一个 value属性】，
那么真正使用该自定义注解时，可以省略掉 “value=” 部分。
例如：@SuppressWarnings ("deprecation")
再如，配置servlet映射虚拟路径时的 @WebServlet( "/user/*" );

*  */

@Retention( RetentionPolicy.RUNTIME ) //元注解@Retention：决定此自定义注解的生命周期
@Target( ElementType.METHOD ) //元注解@Target：决定此注解要加在哪个成分上
//ElementType.METHOD 表示此注解要加在 方法 上
public @interface Select { //配置sql语句的注解，花括号里的是注解的属性
    //以下为注解添加属性
    String value(); //只添加value属性，那么真正使用此注解时，可以省略 “value=” 部分。
}