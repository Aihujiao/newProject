<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 40771
  Date: 2023/1/29
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/AdminServer" method="post">
        <input type="hidden" name="op" value="adminLogin">
        <p>管理员昵称：<input type="text" name="adminNickName"></p>
        <p>管理员密码：<input type="text" name="adminPassword"></p>
        <button>登录</button>
    </form>
    <c:choose>
        <c:when test="${msg=succeed}">
            <p style="color: red">操作成功</p>
        </c:when>
        <c:when test="${msg=false}">
            <p style="color: red">操作失败</p>
        </c:when>
    </c:choose>
</body>
</html>
