<%--
  Created by IntelliJ IDEA.
  User: polic
  Date: 2020/1/28
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %> <!-- 手动开启EL表达式 -->
<html>
    <head>
        <title>控制器的方法的返回值类型，与response</title>
        <!-- 加上虚拟目录 -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js" ></script>
        <script type="text/javascript">
            $(document).ready( function(){
                $("#btn").click( function(){ //绑定单击响应函数
                    /* 发送异步请求
                    * jQuery.ajax([settings]-可选。用于配置 Ajax请求的键值对集合，即{键:值}。)
                    * 如：$.ajax( { 键1:值1, 键2:值2, ... } )
                    *  */
                    $.ajax( {
                        //url : "/SpringMVC1/user/textAjax", //手动加上虚拟目录名
                        //ajax请求路径：开头带上 /虚拟目录名！
                        url : "/SpringMVC1/user/testAjax", //我佛了，我写错了！
                        contentType : "application/json;charset=UTF-8",
                        /* 写法1   data : {"username":"hehe", "password":"123", "age":30},
                        *      //最外围没有加(单)引号，也必须加单引号，不能加双引号！
                        * 报错：JSON parse error: Unrecognized token 'username': was expecting ('true', 'false' or 'null')
                        *
                        * 说明：data数据传输的是 字符串格式，最外围要加引号。
                        * 但是要加哪个引号呢？必须加单引号，不能加双引号！
                        * 写法2    data : "{ 'username':'hehe', 'password':'123', 'age':30 }", //我佛了，我又写错了！
                        *       //最外围尝试用双引号，里面的键却只能用单引号了！
                        * 报错：JsonParseException: Unexpected character (''' (code 39)): was expecting double-quote to start field name
                        * 说明：data数据传输的是 字符串格式，最外围必需用单引号，键名字符串用双引号啊！！
                        *  */

                        //data : {"username":"hehe","password":"123","age":"30"}, //最外层没加引号
                        //data : "{ 'username':'hehe', 'password':'123', 'age':'30' }", //最外层用双引号。我佛了，我又写错了！
                        data : '{ "username":"hehe", "password":"123", "age":"30" }', //正确写法：最外层用单引号，里面的键名用双引号
                        /* data是传给后台的数据 ——请求体数据
                        * 那么，若后台方法的传入形参要想获得请求体数据data，则需要加注解@RequestBody
                        * 这样后台方法的传入形参就能拿到请求体数据data了！
                        *  */

                        dataType : "json", //设置 【接收到的响应数据的格式】
                        type : "post",

                        /* 响应成功的回调函数
                        * @ResponseBody注解 把返回的user对象转为json字符串，
                        * 这返回响应的json字符串用于前台ajax请求的 响应成功回调函数success中！
                        *  */
                        success: function(res){ //服务器端响应的json的数据res，进行解析
                            console.log( res );
                            console.log( res.username );
                            console.log( res.password );
                            console.log( res.age );
                        }
                    } )
                } )
            } )
        </script>


    </head>
    <body>
        <div>
            <a href="${pageContext.request.contextPath}/user/testVoid" >testVoid</a>
            <br />
            <a href="${pageContext.request.contextPath}/user/testModelAndView" >testModelAndView</a>
            <br />
            <a href="${pageContext.request.contextPath}/user/testForwardOrRedirect" >testForwardOrRedirect</a>
            <br />
            <button id="btn" >发送ajax请求</button>
        </div>

    </body>
</html>