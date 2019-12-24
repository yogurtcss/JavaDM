<%--
  Created by IntelliJ IDEA.
  User: polic
  Date: 2019/12/23
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 以下代码从login.html复制过来的嗷！ --%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>管理员登录</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="{pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="{pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>

    <script type="text/javascript" >
        window.onload = function(){

            //点击时 更改验证码图片
            document.getElementById( "vcode" ).onclick = function(){
                /* 加请求的参数：时间戳  //... ?time= + 时间戳
                * 我忘了加了 ?time= 了
                * */
                this.src = "${pageContext.request.contextPath}/checkCodeServlet?time="+new Date().getTime();
            }
        }

    </script>



</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">管理员登录</h3>

    <%-- 更改2
    <form> 标签用来定义一个表单，所有表单内容放到这个容器元素之中。
    --%>
    <form action="${pageContext.request.contextPath}/loginServlet" method="post">
        <div class="form-group">
            <%-- <label> 标签是一个行内元素，提供控件的文字说明，帮助用户理解控件的目的。
            * <label>标签 类似 <span>标签，直接在标签体内写文字即可

            * <label> 的 for属性关联相对应的控件，for属性的值是对应 【控件的 id属性的值】。
            * 所以，控件最好设置 id属性。
            --%>
            <label for="user">用户名：</label>              <%-- 注意：此处 input标签的 id属性值为 user --%>
            <input type="text" name="username" class="form-control" id="user" placeholder="请输入用户名"/>
            <%-- input标签：必须写上name属性
            * name属性：设置此控件的名称。主要用于向服务器提交数据时，【设置】此input标签 通过post请求提交的键值对的【键名】。
            ▲ name属性 的 【属性值】 就是 此input标签 通过post请求提交的键值对的【键名】

            * 注意，只有设置了 name 属性的控件，才会向服务器提交，不设置就不会提交。
            * 如：某用户在输入框中输入了 zhangsan的值，那么提交给服务器的键值对为：
            * 【name属性的属性值-此控件的名称 就是 ▲包含此input标签的外部大的form表单 发出的post请求 所提交的键值对的“键名”嗷！】

             【name属性的属性值】username = zhangsan！！

            【name属性的属性值-此控件的名称 就是 ▲包含此input标签的外部大的form表单 发出的post请求 所提交的键值对的“键名”嗷！】
            【name属性的属性值-此控件的名称 就是 ▲包含此input标签的外部大的form表单 发出的post请求 所提交的键值对的“键名”嗷！】
            【name属性的属性值-此控件的名称 就是 ▲包含此input标签的外部大的form表单 发出的post请求 所提交的键值对的“键名”嗷！】

            * 这用在 后台的servlet 通过request请求获取参数：
            * request.getParameter( 【post请求所提交过来的 “键值对” 的】"键名" 如username )

            --%>
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
        </div>

        <div class="form-inline">
            <label for="vcode">验证码：</label>
            <%-- 我佛了，原来这里的name="verifyCode"不是用驼峰命名法！
            原本写的是 verifycode， c是小写的！我真的佛了！

            而在 request.getParameter( "verifyCode" ) 我用的是 verifyCode！
            键名verifyCode不正确，以至于获取不到真正的验证码！
            --%>
            <input type="text" name="verifyCode" class="form-control" id="verifycode" placeholder="请输入验证码" style="width: 120px;"/>

            <%-- 改造1
            ${pageContext页面上下文对象.request属性.contextPath}
            每一次都去请求范围找 绝对路径的值，获取当前根目录。

            注：EL表达式中 ${pageContext页面上下文对象.request属性.contextPath} 等价于
            JSP页面的代码 <%= request隐式的实例对象.getContextPath() %>，

            ▲ el表达式实际上是通过"."运算符 调用的是【该类的实例对象中的 getXXX方法】，
            el表达式中的pageContext 对应于 JAVA类中的 public abstract class PageContext

            pageContext页面上下文对象.request属性 等价于：
            JAVA中真正的pageContext实例对象.getRequest()方法  获取请求request的对象

            --%>

            <img src="${pageContext.request.contextPath}/checkCodeServlet" title="看不清点击刷新" id="vcode"/>



        </div>
        <hr/>
        <div class="form-group" style="text-align: center;">
            <input class="btn btn btn-primary" type="submit" value="登录">
        </div>
    </form>

    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span>
        </button>
        <%-- 在request域中的数据login_msg
        在请求request中添加属性的键值对，键为login_msg，值为 "用户名或密码错误！"
        request.setAttribute( "login_msg", "用户名或密码错误！" );
        在JSP页面中，使用 el表达式 应在request域-requestScope中获取此值

        或者我们不指明域在哪，让 JSP自己在四个域找去吧
        ▲ EL 从四个域中获得某个值 ${ key键名 };
        同样是依次从 pageContext 域，request 域，session 域，application 域中 获取属性，
        在某个域中获取后将不在向后寻找
        --%>
        <strong>${login_msg}</strong>
    </div>
</div>
</body>
</html>