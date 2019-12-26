<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

    <script>
        <%-- 逮到你了：自定义的deleteUser函数，传入形参为 用户的id
        --%>
        function deleteUser(id){
            //用户安全提示
            if(confirm("您确定要删除吗？")){
                //访问路径
                <%-- window.location对象 ——在window全局中的location对象
                window.location.href="某新的url"  ——类似点击另一个a标签的链接。跳转到指定的新的url
                --%>
                window.location.href="${pageContext.request.contextPath}/delUserServlet?id="+id;
                <%-- ?问号后的是 post请求中传递参数，这里传递的参数名为"id"
                在delUserServlet中获取被删除用户的id 语句为 request.getParameter("id"); --%>
            }
        }

        window.onload = function(){
            //给删除选中按钮添加单击事件
            document.getElementById("delSelected").onclick = function(){
                if(confirm("您确定要删除选中条目吗？")){

                   var flag = false;
                    //判断是否有选中条目
                    var cbs = document.getElementsByName("uid");
                    for (var i = 0; i < cbs.length; i++) {
                        if(cbs[i].checked){
                            //有一个条目选中了
                            flag = true;
                            break;
                        }
                    }

                    if(flag){//有条目被选中
                        //表单提交
                        document.getElementById("form").submit();
                    }
                }

            };
            //1.获取第一个cb
            document.getElementById("firstCb").onclick = function(){
                //2.获取下边列表中所有的cb
                var cbs = document.getElementsByName("uid");
                //3.遍历
                for (var i = 0; i < cbs.length; i++) {
                    //4.设置这些cbs[i]的checked状态 = firstCb.checked
                    cbs[i].checked = this.checked;

                }

            }
        }


    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>

    <div style="float: left;">

        <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" name="name" value="${condition.name[0]}" class="form-control" id="exampleInputName2" >
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <input type="text" name="address" value="${condition.address[0]}" class="form-control" id="exampleInputName3" >
            </div>

            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="text" name="email" value="${condition.email[0]}" class="form-control" id="exampleInputEmail2"  >
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>

    <div style="float: right;margin: 5px;">

        <a class="btn btn-primary" href="${pageContext.request.contextPath}/jsp/add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>

    </div>
    <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="firstCb"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${pb.list}" var="user" varStatus="s">
            <tr>
                <td><input type="checkbox" name="uid" value="${user.id}"></td>
                <td>${s.count}</td>
                <td>${user.name}</td>
                <td>${user.gender}</td>
                <td>${user.age}</td>
                <td>${user.address}</td>
                <td>${user.qq}</td>
                <td>${user.email}</td>
                <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>&nbsp;
                    <%-- 逮到你了：自定义的deleteUser函数，
                    传入形参为 用户的id
                    --%>
                    <%-- 第一种写法，我还真没想到！ --%>
                    <%--
                    <a id="wantToDelete"
                       class="btn btn-default btn-sm"
                       href="javascript:deleteUser(${user.id});">删除</a></td>  --%>

                    <%-- 第二种写法，看下面嗷！ --%>
                    <a id="wantToDelete"
                       class="btn btn-default btn-sm"
                       href="javascript:void(0);"
                       <%-- ------------------------------------
                       1.onclick=""，双引号中的既不是字符串，也不是表达式，而是【语句】。所以要加上分号
                       2.onclick="return false;" 会阻止默认行为，如阻止 form表单中的 submit按钮和 reset 钮，以及 阻止<a>标签跳转
                       3.关于 javascript:前缀 该写在哪里
                       一个是 onclick="javascript:"，另一个就是 <a href="javascript:">，
                       (1)对于onclick， 可以省略javascript前缀；

                       (2)但是 <a>标签写和不写就代表两种意思了：
                          写了前缀，那么 href的作用就和 onclick 一样了：执行一段javascript代码
                          不写前缀，href 就是一个字符串 URL，会进行跳转。
                       ------------------------------------ --%>
                       onclick="javascript:deleteUser(${user.id});return false;">删除</a>
                        <%-- 这里的 javascript: 可以省略--%>
                </td>

                    <%-- ------------------------------------
                    一般想在 A 标签上使用 js 代码 有三种情况：
                        <a href="javascript: 自定义函数test(${el表达式-传入形参})"> 标签 1</a>
                        <a onclick="自定义函数test( ${el表达式-传入形参} )" href="#"> 标签 2</a>
                        <a onclick="自定义函数test( ${el表达式-传入形参} )" href="javascript:void(0)"> 标签 3</a>
                    ------------------------------------ --%>


                    <%-- ------------------------------------
                    ▲ <a> 标签的 href 属性用于指定超链接目标的 URL，
                    href 属性的值可以是任何有效文档的相对或绝对 URL，包括片段标识符和 【JavaScript 代码段】。

                    ▲ javascript: 自定义函数function(传入形参)    //表示在点击<a>标签时，执行一段 JavaScript 代码；
                      - 【javascript:】是伪协议，它可以让我们通过一个链接来调用 javascript 函数。
                    而 javascript:; 表示什么都不执行，这样点击 <a> 时就没有任何反应。

                    ▲ javascript:; 表示这是一个空连接。点击之后没任何反应。
                    类似的是 #，但是一个 #点击之后页面很长的情况下会会滚到顶部；而 javascript:; 没这样的问题；
                    当然 ### 这样的效果就跟 javascript:; 一样了

                    【关于 javascript:前缀 该写在哪里】
                     一个是 onclick="javascript:"，另一个就是 <a href="javascript:">，
                       (1)对于onclick， 可以省略javascript前缀；

                       (2)但是 <a>标签写和不写就代表两种意思了：
                          写了前缀，那么 href的作用就和 onclick 一样了：执行一段javascript代码
                          不写前缀，href 就是一个字符串 URL，会进行跳转。
                    ------------------------------------ --%>


                    <%-- ------------------------------------
                    a标签的 onclick 和 href 同时存在的写法：
                    a标签的href跳转会执行在 onClick 之前。所以解决方法是在 href 里面添加 javascript:void(0)
                      注：javascript:void(0)仅仅表示一个死链接，没有任何信息。

                        <a href="javascript:void(0)"
                           οnclick="自定义函数-doSomething( ${el表达式-传入形参} );return false;" >test
                        </a>

                    ------------------------------------ --%>
            </tr>
        </c:forEach>


    </table>
    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pb.currentPage == 1}">
                    <li class="disabled">
                </c:if>

                <c:if test="${pb.currentPage != 1}">
                    <li>
                </c:if>


                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage - 1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>


                <c:forEach begin="1" end="${pb.totalPage}" var="i" >


                    <c:if test="${pb.currentPage == i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
                    </c:if>
                    <c:if test="${pb.currentPage != i}">
                        <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
                    </c:if>

                </c:forEach>


                <li>
                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage + 1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 5px;">
                    共${pb.totalCount}条记录，共${pb.totalPage}页
                </span>

            </ul>
        </nav>


    </div>


</div>


</body>
</html>
