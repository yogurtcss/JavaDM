<%--
  Created by IntelliJ IDEA.
  User: polic
  Date: 2019/12/22
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 导包 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%-- 导入User类 --%>
<%@ page import="pers.yo.study.Demo01_Request.example.domain.User"  %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <%-- 在request域中，存有一个User对象的List集合，
    需要使用 jstl+el 将List集合数据 展示到jsp页面的表格 table中
    --%>
    <%
        List list = new ArrayList();
        list.add( new User(1, "张三","123") );
        list.add( new User(2, "李四","456") );
        list.add( new User(3, "王五","789") );
        list.add( new User(4, "嗯嗯","abc") );

        request.setAttribute( "list",list );
    %>
    <%--
    ▲ HTML 表格的行
    <tr> 标签定义 HTML 表格中的行。
    tr 元素包含一个或多个 th 或 td 元素。

    ▲ HTML 表格有两种单元格类型：
    表头单元格 - 包含头部信息（由 th 元素创建）
    标准单元格 - 包含数据（由 td 元素创建）
    th 元素中的文本呈现为粗体并且居中。
    td 元素中的文本是普通的左对齐文本。
    --%>


    <table border="1" width="500" align="center" >
        <tr>  <%-- 一行 --%>
            <th>遍历次数</th>
            <th>ID</th>
            <th>姓名</th>
            <th>密码</th>
        </tr>
        <%-- 数据行 --%>
        <c:forEach items="${list}" var="one" varStatus="s" >
            <tr>
                <td>${s.count}</td> <%-- 当前遍历的次数(第几次) --%>
                <td>${one.getId()}</td>
                <td>${one.getUsername()}</td>
                <td>${one.getPassword()}</td>
            </tr>
        </c:forEach>



    </table>

</body>
</html>
