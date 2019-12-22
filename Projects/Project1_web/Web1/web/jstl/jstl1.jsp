<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: polic
  Date: 2019/12/22
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- language表明这里写的是java代码嗷！ --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--

<%@ taglib prefix="c" uri="http://...." %>
    这句话将 JSTL core 标签库引入 本jsp文件中。
    1.prefix="c" 是为了使用方便而给该标签库起的一个别名，这样在使用时就不用每次都要把较长的标签库名写出来。
    2.uri 统一资源标识符，是一个用于标识某一互联网资源名称的字符串，表示  JSTL core 标签库的来源
    3. uri="http://java.sun.com/jsp/jstl/core" 这是高版本的 JSTL core 标签库的来源(文件路径中带jsp文件夹的)，用这个！

--%>


<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="true">
        <h1>俺是真嗷！</h1>
    </c:if>

    
    <% //这里写的是java代码嗷！
        List list = new ArrayList();
        list.add( "aaaa" );
        list.add( "bbbb" );
        list.add( "cccc" );
        list.add( "dddd" );
        request.setAttribute( "list", list );

        request.setAttribute( "number",4 );
    %>

    <%-- forEach标签

    for( int i=0; i<=10; i++ ){ //普通for循环
        ...
    }


     <c:forEach
        var="循环的临时变量名字"   -- 不用el表达式
        items=" ${要遍历的对象X} " -- 这是JAVA变量，要用el表达式
        begin="循环从哪儿开始"
        end="循环到哪儿结束"
        step="循环的步长"
        varStatus="每一次循环状态的对象s，可以任意起名"
            * 循环状态的对象s有两个属性：index、count
            * 循环状态的对象s.index 容器中元素的索引(从0开始)
            * 循环状态的对象s.count 循环次数(从1开始)
     >

        循环要输出的东西

    </c:forEach>


    ---------------------------------------

    for( 类型T one : 实例对象X ){ //增强for循环，遍历容器、集合
        ...
    }

    <c:forEach
        items=" ${要遍历的容器对象X} " -- 这是JAVA变量，要用el表达式
        var="每次循环中，容器中元素的临时变量名字one"  -- 不用el表达式
        varStatus="每一次循环状态的对象s，可以任意起名"
            * 循环状态的对象s有两个属性：index、count
            * 循环状态的对象s.index 容器中元素的索引(从0开始)
            * 循环状态的对象s.count 循环次数(从1开始)
    >

        //遍历
    </c:forEach>

    --%>


    <c:if test="${not empty requestScope.list}" >

        使用foreach标签遍历集合嗷！
        <c:forEach items="${list}" var="one" varStatus="s" >
            ${one}  <%-- 每一次遍历的对象one --%>
            ${s.index}  <%-- 每一次遍历的对象one的下标 --%>
            ${s.count}  <%-- 每一次遍历的次数 --%>
        </c:forEach>

    </c:if>
[

    <c:if test="${number%2==0}">
        ${number}是偶数！
    </c:if>

</body>
</html>
