package demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //控制器
public class HelloController {
    /* 2020-01-26 21:27:05   在springmvc.xml中：
    <!-- 配置视图解析器 -->
    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver" >
        <!-- 2020-01-26 21:14:07
        value="/WEB-INF/pages/" 加斜杠，表示在项目根目录的 WEB-INF/pages/ 这个路径。而：
        value="WEB-INF/pages/" 不加斜杠，就代表在当前路径去找对应的文件，

        当不加斜杠时：
        (1)如果类前加了一个 @RequestMapping(path) 注解的时候，就相当于多了一层路径，所以报404的错，
        (2)而如果类前没有加 @RequestMapping(path = "/test") 注解，则表示方法的注解都在项目路径下去找，
        -->
        <property name="prefix" value="/WEB-INF/pages/" /> <!-- 注意 value="/WEB-INF/pages/" 斜杠的坑嗷 -->
        <property name="suffix" value=".jsp" />
    </bean>

    *  */

    @RequestMapping( path="/hello" )
    public String sayHello(){
        System.out.println( "Hello SpringMVC啊啊啊！" );
        //在WEB-INF目录下创建pages文件夹，编写success.jsp的成功页面
        return "success";
    }
}
