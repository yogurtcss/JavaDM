<%--
  Created by IntelliJ IDEA.
  User: polic
  Date: 2019/12/26
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%-- 2019-12-26 11:06:29
type属性决定了 <input> 的形式。
type="hidden" 是一个不显示在页面的控件，用户无法输入它的值，
主要用来向服务器传递一些隐藏信息。比如，CSRF 攻击会伪造表单数据，
那么使用这个控件，可以为每个表单生成一个独一无二的隐藏编号，防止伪造表单提交。
* type="hidden"：隐藏表单域，value属性的值 表示该隐藏表单域的值。
--%>

<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>

    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <%-- 好吧，新建一个jsp页面时，若使用到样式
    必需要引入的样式前加入 项目的虚拟目录！${pageContext.request.contextPath}
    --%>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>

    <%-- 改进：提交表单至/updateUserServlet  --%>
    <form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
        <%-- 2019-12-26 11:06:29
        type属性决定了 <input> 的形式。
        type="hidden" 是一个不显示在页面的控件，用户无法输入它的值，
        主要用来向服务器传递一些隐藏信息。比如，CSRF 攻击会伪造表单数据，
        那么使用这个控件，可以为每个表单生成一个独一无二的隐藏编号，防止伪造表单提交。
        * type="hidden"：隐藏表单域，value属性的值 表示该隐藏表单域的值。

        ▲ 注意：一定要把 隐藏域 放在 post请求的表单form中(放在表单中的任意位置，并不影响布局)！
        让隐藏域中的数据跟随post请求发给服务器！
        --%>
        <%-- 隐藏域：提交用户id --%>
        <input type="hidden" name="id" value="${requestScope.user.id}"  />


        <div class="form-group">
            <label for="name">姓名：</label>
            <%-- ---------------------------
            1.user数据在request域中储存的
            2.value属性为(第四声) <input />元素设定值；对于不同的输入类型，value属性的用法、表达的意义也不同。
              * type="text"时，( <input />标签默认type为"text" )，value表示文本框里的值( 或 显示在文本框中的值 )
              * type="submit"，即 input标签变为 提交按钮，value表示按钮上的文字
            --------------------------- --%>
            <input type="text" value="${requestScope.user.name}"
                   class="form-control" id="name" name="name"  readonly="readonly" placeholder="请输入姓名" />
        </div>

        <div class="form-group">
            <label>性别：</label>

            <%-- 将原本 name="sex" 改为 name="gender"，与数据库中user表列名gender对应
            * 当input标签的 type属性为"checkbox"或"radio"时，此时input标签是一个复选框
            checked 属性表示 选中此 “复选框”
            --%>
            <c:if test="${requestScope.user.gender=='男'}">
                <input type="radio" name="gender" value="男" checked />男 <%-- checked表示选中此复选框 --%>
                <input type="radio" name="gender" value="女"  />女
            </c:if>
            <c:if test="${requestScope.user.gender=='女'}"  >
                <input type="radio" name="gender" value="男" />男
                <input type="radio" name="gender" value="女" checked  />女 <%-- checked表示选中此复选框 --%>
            </c:if>


        </div>

        <div class="form-group">
            <label for="age">年龄：</label>

            <%-- 手动地、动态显示用户的数据 age --%>
            <input type="text" value="${requestScope.user.age}"
                   class="form-control" id="age"  name="age" placeholder="请输入年龄" />
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>

            <%-- 因为这里的多选菜单只有3个，可以做3个if判断 --%>
            <c:if test="${requestScope.user.address=='陕西'}">
                <select name="address" id="address" class="form-control" >
                    <option value="陕西" selected >陕西</option>  <%-- selected表示 在多选菜单中 此选项被选中 --%>
                    <option value="北京">北京</option>
                    <option value="上海">上海</option>
                </select>
            </c:if>

            <c:if test="${requestScope.user.address=='北京'}">
                <select name="address" id="address" class="form-control" >
                    <option value="陕西">陕西</option>
                    <option value="北京" selected>北京</option> <%-- selected表示 在多选菜单中 此选项被选中 --%>
                    <option value="上海">上海</option>
                </select>
            </c:if>

            <c:if test="${requestScope.user.address=='上海'}">
                <select name="address" id="address" class="form-control" >
                    <option value="陕西">陕西</option>
                    <option value="北京">北京</option>
                    <option value="上海" selected>上海</option> <%-- selected表示 在多选菜单中 此选项被选中 --%>
                </select>
            </c:if>


        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>

            <%-- 手动地、动态显示用户的数据：QQ --%>
            <input type="text" value="${requestScope.user.qq}"
                   id="qq" class="form-control" name="qq" placeholder="请输入QQ号码"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>

            <%-- 手动地、动态显示用户的数据 email --%>
            <input type="text" value="${requestScope.user.email}"
                   id="email" class="form-control" name="email" placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-group" style="text-align: center">

            <%-- * type="submit"，即 input标签变为 提交按钮，value表示按钮上的文字 --%>
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>
