<%--
  Created by IntelliJ IDEA.
  User: 40771
  Date: 2023/2/2
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../base.jsp"%>
<html>
<head>
    <title>员工注册</title>
</head>
<body>
  <form action="<%=contextPath%>/EmployeeServer">
    <input type="hidden" name="op" value="employeeRegister">
    <p>员工姓名:<input type="text" name="employeeName" placeholder="请输入要注册的员工姓名"></p>
    <p>员工密码:<input type="text" name="employeePassword" placeholder="请输入用于登录的密码"></p>
    <p>员工性别:<input type="text" name="employeeGender" placeholder="输入您的性别"></p>
    <p>员工年龄:<input type="text" name="employeeAge" placeholder="请输入您的年龄"></p>
    <p>员工头像:<input type="text" name="employeeProfile" placeholder="用户头像地址//待完善"></p>
    <p>部门编号:<input type="text" name="employeeDepartmentId" placeholder="部门编号"></p>
    <p>员工职位:<input type="text" name="employeePosition" placeholder="员工的职位"></p>
    <p>员工状态:<input type="text" name="employeeStation" placeholder="员工的状态"></p>
    <button>注册管理员</button>
  </form>
</body>
</html>
