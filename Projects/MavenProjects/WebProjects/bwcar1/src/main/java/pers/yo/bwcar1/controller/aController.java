package pers.yo.bwcar1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class aController {
    @RequestMapping("/aaa") //当浏览器在localhost:8080后面输入 /aaa时
    public String aaa(){
        return "aaa";
        //return "bbb";
        /* 注：classpath路径是项目的resources文件夹
        *
        * 当配置了Thymeleaf插件后，按照Thymeleaf插件的默认配置：
        * 这个方法返回的字符串 "aaa" 被 Thymeleaf 插件处理：“拼接前后缀的路径”
        * 拼接路径为： classpath:/templates/aaa.html；
        *
        * 同时，classpath:/templates文件夹下也要【创建一个aaa.html文件】，
        * 这样 classpath:/templates文件夹下的aaa.html 才能正确被访问到；
        *
        * ---------------------------------------------
        * 执行的逻辑：
        * 创建一个控制器aController，加注解 @RequestMapping("/aaa")，匹配请求路径为自定义的 "/aaa"；
        *
        * 这样，当浏览器在localhost:8080后面输入 /aaa时：
        *              ——【/aaa路径与 aController的aaa()方法上的注解@RequestMapping("/aaa")匹配成功，从而调用aaa()方法】
        * aaa()方法被调用后，方法的返回值aaa 被Thymeleaf 插件处理：“拼接前后缀的路径”为 classpath:/templates/aaa.html
        * 浏览器跳转到的路径为 classpath:/templates/aaa.html；
        * 若能在classpath:/templates/文件夹下找到aaa.html，则正确访问；否则无法访问。
        *  */
    }

    @RequestMapping("/bbb")
    public String bbb(){
        return "bbb";
        /* 注：classpath路径是项目的resources文件夹
         * 这个返回的字符串 "bbb"
         * 被 Thymeleaf 插件处理后，“拼接前后缀的路径”
         * 拼接路径为： classpath:/templates/aaa.html
         *
         * 所以，当浏览器在localhost:8080后面输入 /bbb时
         * 跳转到的路径为 classpath:/templates/bbb.html
         *  */
    }
}
