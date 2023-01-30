<%--
  Created by IntelliJ IDEA.
  User: 40771
  Date: 2023/1/29
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>管理员信息</title>
</head>
<body>
<h2>管理员信息</h2>
<form action="" method="post">
    <p>管理员编号:${sessionScope.admin.adminId}</p>
    <p>管理员昵称:${sessionScope.admin.adminNickName}</p>
    <p>管理员密码:${sessionScope.admin.adminPassword}</p>
    <p>管理员头像:${sessionScope.admin.adminProfile}</p>
    <p>所管部门号:${sessionScope.admin.adminDepartmentId}</p>
    <p>管理员状态:${sessionScope.admin.adminStation}</p>
    <input type="submit" formaction="<%=request.getContextPath()%>/admin/update.jsp" value="更新个人信息">

    <c:choose>
        <c:when test="${sessionScope.admin.adminId == 1}">
            <input type="submit" formaction="<%=request.getContextPath()%>/admin/register.jsp" value="注册管理员">
            <input type="submit" formaction="<%=request.getContextPath()%>/admin/getInfo.jsp?type=admins" value="查询所有管理员"><br><br>
            <input type="submit" formaction="<%=request.getContextPath()%>/admin/departmentRegister.jsp" value="添加部门">
            <input type="submit" formaction="<%=request.getContextPath()%>/admin/getInfo.jsp?type=departments" value="查询所有部门">
            <input type="submit" formaction="<%=request.getContextPath()%>/admin/getInfo.jsp?type=employees" value="查询所有员工信息">
        </c:when>
        <c:otherwise>
            <input type="submit" formaction="/AdminServer?op=adminDeleteById" value="注销账户">
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${param.msg == 'succeed'}">
            <p style="color: red">更新成功</p>
        </c:when>
        <c:when test="${param.msg == 'err'}">
            <p style="color: red">更新失败</p>
        </c:when>
        <c:when test="${param.msg == 'registSucceed'}">
            <p color="red">注册成功</p>
        </c:when>
        <c:when test="${param.msg == 'registFalse'}">
            <p color="red">注册失败</p>
        </c:when>
        <c:when test="${param.msg == 'noway'}">
            <p color="red">想都别想！超级管理员删不了~</p>
        </c:when>
    </c:choose>
</form>
</body>
</html>
