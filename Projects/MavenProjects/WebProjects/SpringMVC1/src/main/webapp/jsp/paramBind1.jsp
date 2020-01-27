<%--
  Created by IntelliJ IDEA.
  User: polic
  Date: 2020/1/27
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>  <!-- JSP1.2版本，必需手动打开EL表达式   -->
<!-- 复习JSP的指令
指令
* 作用：用于配置JSP页面，导入资源文件
* 格式：<(空格)%@ 指令名称 属性名1=属性值1 属性名2=属性值2 ... %>
-->
<html>
    <head>
        <title>parameter bind1 参数绑定</title>
    </head>
    <body>

        <!-- 2020-01-27 15:25:51
        关于 SpringMVC中 jsp页面el表达式全不能用的问题如下：
        (1)在 jsp 页面中使用 EL 表达式进行获取得不到数据，而是直接显示表达式的值，如直接显示 $(空格){message}
        (2)使用虚拟目录时 $(空格){pageContext.request.contextPath}，不会跳到虚拟目录，
        跳转的地址中直接显示  $(空格){pageContext.request.contextPath}！

        原因：JSP1.2默认的 EL表达式是关闭的 —— 必须手动打开。<(空格)%@page isELIgnored="false" %>
        JSP2.0 默认的 EL表达式是打开的！

        ▲ 采用 JSP1.2 的 web.xml 配置信息为：
            <!DOCTYPE web-app PUBLIC
             "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
             "http://java.sun.com/dtd/web-app_2_3.dtd" >

        ▲ 在这里，当前这个通过Maven骨架创建的 webapp工程的这个web.xml 采用的是JSP1.2版本！
        此web.xml一开头的约束就是 JSP1.2的约束！我佛了！

        解决：在需要使用EL表达式的那个jsp页面开头，手动打开EL表达式：<(空格)%@page isELIgnored="false" %>
        -->


        <%-- 请求参数绑定：href开头不要带斜杠啊！ --%>
        <a href="${pageContext.request.contextPath}/param/testParam?username=hehe&password=enenen" >请求参数绑定</a>
        <!-- href = param模块名/testParam方法名，指明：当匹配到/
        在href跳转的链接中传递请求参数 username=hehe、password=enenen
        在后台方法testParam中的形参username、password：就会【传入请求的参数】
        public String testParam( String username, String password )
        形参被赋值 username=hehe、password=enenen

        ▲ 注意：后台方法中的形参名 要与 传递参数的键名 一致！！
        -->

        <!-- 把数据封装到Account类中
        2020-01-27 16:54:30 必需手动加上虚拟目录，不以斜杠开头！
        <form action="param/saveAccount" method="post">
        //若action的路径开头没有带 $(空格){pageContext.request.contextPath}
        直接写成 "param/saveAccount" ，那么真正跳转的路径是：
        从当前的路径(虚拟目录/二级目录/三级目录) 开始跳转，而不是跳转到 虚拟目录！

        如 我之前访问的地址为 【http://localhost:8080/SpringMVC1/jsp/】paramBind1.jsp
        当 直接写成 "param/saveAccount" 时，跳转的路径是
        【http://localhost:8080/SpringMVC1/jsp/】param/saveAccount
        //【http://localhost:8080/SpringMVC1/jsp/】就是 当前的路径(虚拟目录/二级目录)！！

        所以，HTML、jsp标签中，必需手动加上虚拟目录，以防此请求跳转到 非虚拟目录！
        (因为 此jsp页面可能在 虚拟目录/二级目录/三级目录 下，需要手动让请求重定向到 虚拟目录！)
        -->

        <!-- 因为跳转前的jsp页面，是在 虚拟目录/二级目录/三级目录 下，
        ( 【http://localhost:8080/SpringMVC1】/jsp/paramBind1.jsp )
        因此 需要手动让请求重定向到 虚拟目录！
        -->
<%--        <form action="${pageContext.request.contextPath}/param/saveAccount" method="post">--%>
<%--            <!-- <label> 标签的 for属性 与 相关元素的 id属性相同。 -->--%>
<%--            <label for="usernameLabel">用户名</label>--%>
<%--            <input type="text" name="username" id="usernameLabel" /> <br/>--%>
<%--            <label for="passwordLabel" >密码</label>--%>
<%--            <input type="password" name="password" id="passwordLabel" /> <br/>--%>
<%--            <label for="moneyLabel" >金额</label>--%>
<%--            <input type="text" name="money" id="moneyLabel" /> <br/>--%>
<%--            <label for="mynameLabel">姓名</label>--%>
<%--            <input type="text" name="myname" id="mynameLabel" /> <br/>--%>
<%--            <label for="ageLabel" >年龄</label>--%>
<%--            <input type="text" name="age" id="ageLabel" /> <br/>--%>
<%--            <input type="submit" value="提交" />--%>
<%--        </form>--%>


        <form action="${pageContext.request.contextPath}/param/saveUser" >
            <label for="unameLabel">用户名</label>
            <input type="text" name="uname" id="unameLabel" />
            <label for="ageLabel">年龄</label>
            <input type="text" name="age" id="ageLabel" />
            <label for="dateLabel">生日</label>
            <input type="text" name="date" id="dateLabel" />

            <input type="submit" value="提交" />
        </form>

    </body>
</html>