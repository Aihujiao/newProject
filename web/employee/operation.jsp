<%--
  Created by IntelliJ IDEA.
  User: 40771
  Date: 2023/2/1
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../base.jsp"%>
<html>
<head>
    <title>员工操作页</title>
</head>
<body>
    <h2>职员功能管理</h2>
    <p>尊敬的员工:${sessionScope.employee.employeeName},您好~~</p>
    <form action="">
        <hr>
        更新功能
        <hr>
        <input type="submit" formaction="/update.jsp" value="更新个人信息">
        <hr>
        查询功能
        <hr>
        <input type="submit" formaction="<%=contextPath%>/EmployeeServer?op=getSameDepartmentEmployees&employeeDepartmentId=${sessionScope.employee.employeeDepartmentId}" value="查询本部门员工信息">
        <input type="submit" formaction="<%=contextPath%>/Department?op=getAllDepartments" value="查询部门信息">
        <hr>
        *注销账户*
        <hr>
        <input type="submit" formaction="<%=contextPath%>/EmployeeServer?op=employeeDeleteById">
    </form>
</body>
</html>
