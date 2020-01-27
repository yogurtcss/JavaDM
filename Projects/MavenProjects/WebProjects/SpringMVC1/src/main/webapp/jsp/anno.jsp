<%--
  Created by IntelliJ IDEA.
  User: polic
  Date: 2020/1/27
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %> <!-- 手动开启el表达式 -->

<!-- 复习JSP的指令
指令
* 作用：用于配置JSP页面，导入资源文件
* 格式：<(空格)%@ 指令名称 属性名1=属性值1 属性名2=属性值2 ... %>
-->
<html>
    <head>
        <title>常用的注解anno</title>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/anno/testRequestParam?name=哈哈哈啊" >RequestParam</a>
        <br>

        <a href="${pageContext.request.contextPath}/anno/testPathVariable/100" >
            通过占位符获取请求参数。注意，我这里直接传了一个数字100：斜杠/100
        </a>
        <br>

        <form action="${pageContext.request.contextPath}/anno/testRequestBody" method="post">
            用户姓名：<input type="text" name="username" /><br/>
            用户年龄：<input type="text" name="age" /><br/>
            <input type="submit" value="提交" />
        </form>

    </body>
</html>
