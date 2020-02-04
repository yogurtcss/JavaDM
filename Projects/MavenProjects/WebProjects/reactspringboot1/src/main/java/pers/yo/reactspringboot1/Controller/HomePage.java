package pers.yo.reactspringboot1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class HomePage {
    @RequestMapping("/aa")
    public String homePage(){
        return "index";
    }
}
