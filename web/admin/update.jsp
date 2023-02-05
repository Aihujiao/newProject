<%--
  Created by IntelliJ IDEA.
  User: 40771
  Date: 2023/1/29
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../base.jsp"%>

<html>
<head>
    <title>更新管理员信息</title>
</head>
<body>
<h2>信息更新</h2>
  <form action="<%=contextPath%>/AdminServer" method="post">
    <input type="hidden" name="op" value="adminUpdate">
    <p><input type="hidden" name="adminId" value="${sessionScope.admin.adminId}"></p>
    <p>昵称：<input type="text" name="newNickName" value="${sessionScope.admin.adminNickName}"></p>
    <p>密码：<input type="text" name="newPassword" value="${sessionScope.admin.adminPassword}"></p>
    <p>个人头像<input type="text" name="newProfile" value="${sessionScope.admin.adminProfile}"></p>
    <p>部门编号:<input type="text" name="newDepartmentId" value="${sessionScope.admin.adminDepartmentId}"></p>
    <p><input type="hidden" name="adminStation" value="${sessionScope.admin.adminStation}"></p>
    <button>更新信息</button>
  </form>
</body>
</html>
