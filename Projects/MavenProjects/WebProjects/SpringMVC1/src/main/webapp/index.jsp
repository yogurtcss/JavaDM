<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- jsp的开头要有上面那句话的配置啊！！我忘记了！ -->
<%@ page isELIgnored="false" %> <%-- JSP1.2版本，EL表达式默认关闭，必需手动开启EL表达式 --%>

<!-- 复习JSP的指令
指令
* 作用：用于配置JSP页面，导入资源文件
* 格式：<(空格)%@ 指令名称 属性名1=属性值1 属性名2=属性值2 ... %>
-->

<html>
    <body>
        <h2>Hello World!</h2>
        <h3>入门案例嗷！</h3>
        <!-- $(空格){pageContext页面上下文对象.request属性.contextPath}：动态获取虚拟目录 -->
        <!--
        <a href="$(空格){pageContext.request.contextPath}/hello" >入门案例</a>
        这样加上虚拟目录，报错，找不到这个地址！报错如下
        No mapping for GET /SpringMVC1/$%7BpageContext.request.contextPath%7D/hello

        //▲ 已解决：原因是这个web工程使用的是JSP1.2版本 ——而JSP1.2版本是默认关闭EL表达式的，必需手动开启LE表达式！
		//  见下文！

        可见maven会自动把虚拟目录 /SpringMVC1/ 给自动加上的！
        所以，不用写虚拟目录名，开头也不要带斜杠！直接 "hello"即可！

        <a href="/hello" >入门案例</a>      // 在hello的开头带了斜杠：/hello
        跳到的地址是 http://localhost:8080/hello， 没有虚拟目录/SpringMVC1/  啊！！
        开头不要带斜杠啊啊！！
        -->
        <a href="hello" >入门案例</a>

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
    </body>
</html>
