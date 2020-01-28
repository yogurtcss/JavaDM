package demo1_param.controller;

import demo1_param.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping( "/anno" )
public class AnnoController {

    /* 2020-01-27 20:39:17
    <a href="${pageContext.request.contextPath}/anno/testRequestParam?name=哈哈哈啊" >RequestParam</a>
    前台传递参数键名为name，后台方法中的形参名为username

    当 后台方法中的形参名 要与 传递参数的键名 不一致时：
    把注解 @RequestParam( name="前台传递参数键名" ) 加在后台方法的形参名上
    注意：@RequestParam 的name属性等价于value属性，而只写value属性时，可以省略 value= ，直接写 "前台传递参数键名" 即可
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

    @ModelAttribute //当已知返回值类型时，@ModelAttribute注解加在 有返回值类型的方法上
    //注意：@RequestParam 的name属性等价于value属性，而只写value属性时，可以省略 value= ，直接写 "前台传递参数键名" 即可
    public User populateUser( @RequestParam(name="username") String username ){ //populate 填充
        //模拟：根据前台传参键名username，从数据库中查询出完整的user对象
        User user = new User();
        user.setUname("哈哈");
        user.setAge(20);
        //---以上是用户提交的数据！
        user.setDate( new Date() ); //填充populate、补全 这个缺失的date数据
        return user;
    }

//    @RequestMapping( "/testModelAttribute" )
//    //这里的user不用加@RequestParam注解，因为前台传参键名没有传user对象过来啊！
//    public String testModelAttribute( User user ){ //等待 @ModelAttribute注解预处理后的数据，填进来user
//        System.out.println( user );
//        return "success";
//    }


    @ModelAttribute ////当未知返回值类型时，@ModelAttribute注解加在 无返回值类型的方法上
    public void populateUser2( @RequestParam(name="username")String username, Map<String,User> map ){
        //模拟：根据前台传参键名username，从数据库中查询出完整的user对象
        User user = new User();
        //---假设用户在前台提交的数据 用户名是哈哈，年龄20！
        user.setUname("哈哈");
        user.setAge(20);
        //---以上是用户前台提交的数据！
        user.setDate( new Date() ); //填充populate、补全 这个缺失的date数据

        //--关键：在这个公共map中，提供已知键名"abc"，存入这个完整的user
        map.put( "abc", user );
        //无返回值
    }

    @RequestMapping( "/testModelAttribute" )
    //这里的user不用加@RequestParam注解，因为前台传参键名没有传user对象过来啊！
    //注意：@ModelAttribute 的name属性等价于value属性，而只写value属性时，可以省略 value= ，直接写 "前台传递参数键名" 即可
    public String testModelAttribute( @ModelAttribute(name="abc") User user ){ //等待 @ModelAttribute注解预处理后的数据，填进来user
        System.out.println( user );
        return "success";
    }

}
