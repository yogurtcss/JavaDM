package pers.yo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.yo.domain.Account;
import pers.yo.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping( "/account" )
public class AccountController {

    @Autowired
    private AccountService as;

    @RequestMapping( "/findAll" )
    public String findAll( Model model ){
        System.out.println( "表现层：查询所有账户..." );
        /* 调用service层的方法
        * 若调用service层的方法成功，说明 SpringMVC与Spring 整合成功
        *  */
        List<Account> list = as.findAll();
        model.addAttribute( "list", list );
        return "list";
    }

    @RequestMapping( "/save" )
    public void save(Account account, HttpServletRequest request, HttpServletResponse response) throws IOException {
        as.saveAccount( account );
        //重定向
        response.sendRedirect( request.getContextPath()+"/account/findAll" );
    }
}
