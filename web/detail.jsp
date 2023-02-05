<%@ page import="model.Admin" %>
<%@ page import="ctrl.implement.AdminImplement" %>
<%@ page import="model.Department" %>
<%@ page import="ctrl.implement.DepartmentImplement" %>
<%@ page import="ctrl.dao.DepartmentDao" %>
<%@ page import="ctrl.factory.DepartmentFactory" %>
<%@ page import="ctrl.dao.EmployeeDao" %>
<%@ page import="model.Employee" %>
<%@ page import="ctrl.factory.EmployeeFactory" %>
<%@ page import="ctrl.implement.AdminImplement" %>
<%@ page import="ctrl.implement.DepartmentImplement" %><%--
  Created by IntelliJ IDEA.
  User: 40771
  Date: 2023/1/30
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>详情页</title>
</head>
<body>
<h2>详情页</h2>
    <c:choose>
        <c:when test="${param.type== 'admin'}">
            <%
                System.out.println("能进入admin分支");
                int adminId = Integer.parseInt(request.getParameter("adminId"));
                Admin admin = null;
                AdminImplement adminImplement = new AdminImplement();
                admin = adminImplement.getAdminById(adminId);
                int adminDepartmentId = admin.getAdminDepartmentId();

                Department department = null;
                DepartmentImplement departmentImplement = new DepartmentImplement();
                department = departmentImplement.getDepartmentById(adminDepartmentId);

                //  可以优化
                int stationNum = admin.getAdminStationId();
                String station = null;
                if(stationNum == 0){
                    station = "在线";
                }else if (stationNum == 1){
                    station = "待机";
                }else {
                    station = "下线";
                }
            %>

            <p>管理员编号:<%=admin.getAdminId()%></p>
            <p>管理员昵称:<%=admin.getAdminNickName()%></p>
            <p>所管理部门:<%=department.getDepartmentName()%></p>
            <p>当前状态:<%=station%></p>

        </c:when>
        <c:when test="${param.type== 'department'}">
            <%
                int departmentId = Integer.parseInt(request.getParameter("departmentId"));
                System.out.println(departmentId);
                Department department = null;
                DepartmentDao departmentImplement = DepartmentFactory.instance().getDepartmentCtrl();
                department = departmentImplement.getDepartmentById(departmentId);
            %>

            <p>部门编号:<%=department.getDepartmentId()%></p>
            <p>部门名称:<%=department.getDepartmentName()%></p>
            <p>部门介绍:<%=department.getDepartmentIntro()%></p>
        </c:when>
        <c:when test="${param.type== 'employee'}">
            <%
                int employeeId = Integer.parseInt(request.getParameter("employeeId"));
                System.out.println(employeeId);
                Employee employee = null;
                EmployeeDao employeeCtrl = EmployeeFactory.instance().getEmployeeCtrl();
                employee = employeeCtrl.getEmployeeById(employeeId);
            %>

            <p>员工编号:<%=employee.getEmployeeId()%></p>
            <p>员工名称:<%=employee.getEmployeeName()%></p>
            <p>员工密码:<%=employee.getEmployeePassword()%></p>
            <p>员工性别:<%=employee.getEmployeeGender()%></p>
            <p>员工头像:<%=employee.getEmployeeProfile()%></p>
            <p>员工部门:<%=employee.getEmployeeDepartmentId()%></p>
            <p>员工权限:<%=employee.getEmployeePowerId()%></p>
            <p>员工职位:<%=employee.getEmployeePosition()%></p>
            <p>员工状态:<%=employee.getEmployeeStation()%></p>
        </c:when>
    </c:choose>
</body>
</html>
