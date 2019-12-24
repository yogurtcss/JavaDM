<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>首页</title>

  <!-- 1. 导入CSS的全局样式 -->
  <link href="../css/bootstrap.min.css" rel="stylesheet">
  <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
  <script src="../js/jquery-2.1.0.min.js"></script>
  <!-- 3. 导入bootstrap的js文件 -->
  <script src="../js/bootstrap.min.js"></script>
  <script type="text/javascript">
  </script>
</head>
<body>

<%--

在当前会话中 添加属性的键值对，键为user，值为 loginUser
session.setAttribute( "user", loginUser );

在JSP页面中，使用 el表达式 应在 session域-sessionScope中获取此值

或者我们不指明域在哪，让 JSP自己在四个域找去吧
▲ EL 从四个域中获得某个值 ${ key键名 };
同样是依次从 pageContext 域，request 域，session 域，application 域中 获取属性，
在某个域中获取后将不在向后寻找

--%>


<div > ${user.name}, 欢迎您！ </div>
<div align="center">
  <a
          href="${pageContext.request.contextPath}/findUserByPageServlet" style="text-decoration:none;font-size:33px">查询所有用户信息
  </a>
</div>






</body>
</html>