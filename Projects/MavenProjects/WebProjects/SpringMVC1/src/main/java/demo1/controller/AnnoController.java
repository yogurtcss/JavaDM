package demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping( "/anno" )
public class AnnoController {

    /* 2020-01-27 20:39:17
    <a href="${pageContext.request.contextPath}/anno/testRequestParam?name=哈哈哈啊" >RequestParam</a>
    前台传递参数键名为name，后台方法中的形参名为username

    当 后台方法中的形参名 要与 传递参数的键名 不一致时：
    把注解 @RequestParam( name="前台传递参数键名" ) 加在后台方法的形参名上
    *  */
    @RequestMapping( "/testRequestParam" )
    public String testRequestParam( @RequestParam(name="name") String username ){
        System.out.println( "执行了..." );
        System.out.println( username );
        return "success";
    }

    /* 注解@RequestBody 加在后台方法的传入形参上：
    * 获取请求体的内容
    *  */
    @RequestMapping( "/testRequestBody" )
    public String RequestBody( @RequestBody String body ){
        System.out.println( "我是body！："+body );
        return "success";
    }

    /* 注解@PathVariable 加在后台方法的传入形参上：
    通过占位符获取参数
    后台代码：@RequestMapping( "/testPathVariable/ {此处的占位符-由@PathVariable(name="XXX")来指定！}" )
    public String testPathVariable( @PathVariable(name="占位符XXX") String id ) { ... }

    前台代码：直接传 斜杠/请求参数【值】即可，如  /100
    <a href="${pageContext.request.contextPath}/anno/testPathVariable/100" >
       通过占位符获取请求参数。注意，我这里直接传了一个数字100：斜杠/100
    </a>
    *  */
    @RequestMapping( "/testPathVariable/{sid}" ) //@PathVariable(name="指定占位符！")
    public String testPathVariable( @PathVariable(name="sid") String XXXXXXXYYYYYYYYYYid ){ //后台方法的形参名可任意写！
        System.out.println( "通过占位符获取参数sid：(后台方法的形参名可任意写！)"+XXXXXXXYYYYYYYYYYid );
        return "success";
    }

}
