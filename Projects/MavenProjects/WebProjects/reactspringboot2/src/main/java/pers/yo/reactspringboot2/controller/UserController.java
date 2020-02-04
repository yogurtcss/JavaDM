package pers.yo.reactspringboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.yo.reactspringboot2.domain.User;
import pers.yo.reactspringboot2.service.UserService;

import java.util.List;

@Controller
@CrossOrigin //允许跨域请求
@RequestMapping( "/user" )
public class UserController {

    @Autowired
    private UserService us;

    @RequestMapping("/homePage")
    public String homePage(){
        return "index";
    }

    @RequestMapping( "/findAll" )
    public @ResponseBody List<User> findAll(){
        List<User> rst = us.findAll();
        System.out.println( rst );
        //获取变量的类型：变量.getClass().toString()
        System.out.println( "后端findAll()返回的数据格式是："+rst.getClass().toString() );
        //后端findAll()返回的数据格式是：class java.util.ArrayList 数组嗷！
        return rst;
        /* @ResponseBody注解 把返回的user对象转为json字符串，
        * 这返回响应的json字符串用于前台ajax请求的 响应成功回调函数success中！
        *
        * 2020-02-04 17:43:31  后端通了！
        * 后端返回的数据格式：class java.util.ArrayList 数组嗷！
        * [ User{id=41, userName='老王', birthday=Wed Feb 28 01:47:08 CST 2018, sex='男', address='北京'},
            User{id=42, userName='小二王', birthday=Fri Mar 02 23:09:37 CST 2018, sex='女', address='北京金燕龙'},
            User{id=43, userName='小二王', birthday=Sun Mar 04 19:34:34 CST 2018, sex='女', address='北京金燕龙'},
            User{id=45, userName='传智播客', birthday=Sun Mar 04 20:04:06 CST 2018, sex='男', address='北京金燕龙'},
            User{id=46, userName='老王', birthday=Thu Mar 08 01:37:26 CST 2018, sex='男', address='北京'}
          ]
         *  */
    }
}
