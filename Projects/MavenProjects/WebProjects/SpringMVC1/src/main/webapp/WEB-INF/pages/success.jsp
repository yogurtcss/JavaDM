<%--
  Created by IntelliJ IDEA.
  User: polic
  Date: 2020/1/26
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %> <!-- 手动开启EL表达式 -->
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h3>入门成功辣！！</h3>

        <div>
            <p> demo1_param的内容 </p>
            <p> 在success.jsp中，用EL表达式，在request域中，通过键名aaa可获取到这个值XXX：${requestScope.aaa} </p>
            <p> 在success.jsp中，用EL表达式，在session域中，通过键名aaa可获取到这个值XXX：${requestScope.aaa} </p>
        </div>

    </body>
</html>
