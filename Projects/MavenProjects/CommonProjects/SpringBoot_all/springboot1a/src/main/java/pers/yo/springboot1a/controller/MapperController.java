package pers.yo.springboot1a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.yo.springboot1a.domain.User;
import pers.yo.springboot1a.mapper.UserMapper;

import java.util.List;

@Controller
public class MapperController {

    @Autowired
    private UserMapper userMapper;
    /* 在 UserMapper接口中，我已添加了@Mapper注解
    * @Mapper标记该类是一个mybatis的mapper接口，
    * 可以被spring boot自动扫描到spring上下文中
    *
    * 这里加了@Autowired注解后，虽然变量userMapper飘红，
    * 但实际上是能自动注入成功的，(因为我已经加了@Mapper注解了！) 直接运行即可
    * */

    @RequestMapping("/queryUser")
    @ResponseBody
    public List<User> queryUser(){
        List<User> users = userMapper.queryUserList();
        return users;
    }
}
