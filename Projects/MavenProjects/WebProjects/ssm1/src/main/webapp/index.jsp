<%--
  Created by IntelliJ IDEA.
  User: polic
  Date: 2020/1/30
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %> <!-- 手动开启el表达式 -->
<html>
    <head>
        <title>index.jsp</title>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/account/findAll" >测试查询</a>
        <br />
        <h3>测试包</h3>
        <br />
        <form action="${pageContext.request.contextPath}/account/save" method="post">
            姓名：<input type="text" name="name" /><br/>
            金额：<input type="text" name="money" /><br/>
            <input type="submit" value="保存"/><br/>
        </form>
    </body>
</html>
