<%--
  Created by IntelliJ IDEA.
  User: 40771
  Date: 2023/1/29
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../base.jsp"%>

<html>
<head>
    <title>操作功能</title>
</head>
<body>
<h2>管理员功能</h2>
<form action="" method="post">
    <p>你好管理员<b>${sessionScope.admin.adminNickName}</b>,请选择一下的功能进行操作</p>
    <hr>
    <input type="submit" formaction="<%=contantPath%>/admin/update.jsp" value="更新个人信息">

    <c:choose>
        <c:when test="${sessionScope.admin.adminId == 1}">
            <hr>
            注册功能
            <hr>
            <input type="submit" formaction="<%=contantPath%>/admin/register.jsp" value="注册管理员">
            <input type="submit" formaction="<%=contantPath%>/admin/departmentRegister.jsp" value="注册部门">
            <hr>
            查询功能
            <hr>
            <input type="submit" formaction="<%=contantPath%>/AdminServer?op=getAllAdmins" value="查询管理员">
            <input type="submit" formaction="<%=contantPath%>/DepartmentServer?op=getAllDepartments" value="查询部门">
            <input type="submit" formaction="<%=contantPath%>/AdminServer?op=getAllEmployees" value="查询员工信息">
        </c:when>
        <c:otherwise>
            <input type="submit" formaction="/AdminServer?op=adminDeleteById" value="注销账户">
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${param.msg == 'succeed'}">
            <p style="color: red">操作成功</p>
        </c:when>
        <c:when test="${param.msg == 'err'}">
            <p style="color: red">操作失败</p>
        </c:when>
        <c:when test="${param.msg == 'registSucceed'}">
            <p style="color: red">注册成功</p>
        </c:when>
        <c:when test="${param.msg == 'registFalse'}">
            <p style="color: red">注册失败</p>
        </c:when>
        <c:when test="${param.msg == 'noway'}">
            <p style="color: red">想都别想！无法删除最高级数据！</p>
        </c:when>
    </c:choose>
</form>
</body>
</html>
