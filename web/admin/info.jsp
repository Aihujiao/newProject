<%--
  Created by IntelliJ IDEA.
  User: 40771
  Date: 2023/1/29
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员信息</title>
</head>
<body>
<form action="" method="post">
    <p>管理员编号:${sessionScope.admin.adminId}</p>
    <p>管理员昵称:${sessionScope.admin.adminNickName}</p>
    <p>管理员密码:${sessionScope.admin.adminPassword}</p>
    <p>管理员头像:${sessionScope.admin.adminProfile}</p>
    <p>所管部门号:${sessionScope.admin.adminDepartmentId}</p>
    <p>管理员状态:${sessionScope.admin.adminStation}</p>
    <input type="button" formaction="update.jsp">
    <input type="button" formaction="/AdminServer&logout">
</form>
</body>
</html>
