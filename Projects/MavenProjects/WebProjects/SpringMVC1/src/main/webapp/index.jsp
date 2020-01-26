<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- jsp的开头要有上面那句话的配置啊！！我忘记了！ -->
<html>
    <body>
        <h2>Hello World!</h2>
        <h3>入门案例嗷！</h3>
        <!-- $(空格){pageContext页面上下文对象.request属性.contextPath}：动态获取虚拟目录 -->
        <!--
        <a href="$(空格){pageContext.request.contextPath}/hello" >入门案例</a>
        这样加上虚拟目录，报错，找不到这个地址！报错如下
        No mapping for GET /SpringMVC1/$%7BpageContext.request.contextPath%7D/hello

        可见maven会自动把虚拟目录 /SpringMVC1/ 给自动加上的！
        所以，不用写虚拟目录名，开头也不要带斜杠！直接 "hello"即可！

        <a href="/hello" >入门案例</a>      // 在hello的开头带了斜杠：/hello
        跳到的地址是 http://localhost:8080/hello， 没有虚拟目录/SpringMVC1/  啊！！
        开头不要带斜杠啊啊！！
        -->
        <a href="hello" >入门案例</a>
    </body>
</html>
