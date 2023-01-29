<%--
  Created by IntelliJ IDEA.
  User: 40771
  Date: 2023/1/29
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <input type="submit" formaction="<%=request.getContextPath()%>/admin/update.jsp" value="更新个人信息">
    <input type="submit" formaction="/AdminServer" value="注销账户">

    <c:set var="msg" value="${requestScope.msg}"></c:set>
    <c:choose>
        <c:when test="${msg == 'succeed'}">
            <p color="red">更新成功</p>
        </c:when>
        <c:when test="${msg == 'err'}">
            <p color="red">更新失败</p>
        </c:when>
    </c:choose>
</form>
</body>
</html>
