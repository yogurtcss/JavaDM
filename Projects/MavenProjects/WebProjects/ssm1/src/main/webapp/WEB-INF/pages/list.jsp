<%--
  Created by IntelliJ IDEA.
  User: polic
  Date: 2020/1/30
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>list.jsp</title>
    </head>
    <body>
        <h3>查询所有的帐户</h3>
        <!--
        public String findAll( Model model ){
            System.out.println( "表现层：查询所有账户..." );
            //调用service层的方法
            List<Account> list = as.findAll();
            model.addAttribute( "list", list );
            return "list";
            //在findAll()方法中，用model保存了数据： list键名=list对象
            /* 底层会把 键名aaa-值XXX 这个键值对存储到 【目的地jsp页面】的 request域对象requestScope中！
            * 在list.jsp中 用 EL表达式，通过键名aaa可获取到这个值XXX
            *  */
        }

        <(空格)c:forEach
        items=" $(空格){要遍历的容器对象X} "            -- 这是JAVA变量，要用el表达式
        var="每次循环中，容器中元素的临时变量名字one"   -- 不用el表达式
        varStatus="每一次循环状态的对象s，可以任意起名"
            * 循环状态的对象s有两个属性：index、count
            * 循环状态的对象s.index 容器中元素的索引(从0开始)
            * 循环状态的对象s.count 循环次数(从1开始)
        >

        //遍历
       <(空格)/c:forEach>
        -->

        <c:forEach items="${list}" var="account" >
            ${account.name}
        </c:forEach>
    </body>
</html>
