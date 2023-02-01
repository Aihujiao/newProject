<%@ page import="model.Admin" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 40771
  Date: 2023/1/30
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="base.jsp"%>

<%
    System.out.println("跳到了获取信息页面");
%>

<c:choose>
    <c:when test="${param.type == 'admins'}">
        <c:set var="title" value="查询所有管理员信息"></c:set>
    </c:when>
    <c:when test="${param.type == 'departments'}">
        <c:set var="title" value="查询所有部门信息"></c:set>
    </c:when>
    <c:when test="${param.type == 'employees'}">
        <c:set var="title" value="查询所有员工信息"></c:set>
    </c:when>
</c:choose>

<html>
<head>
    <title>${title}</title>
</head>
<body>
    <h2>${title}</h2>

    <c:choose>
        <c:when test="${param.type == 'admins'}">
            <form action="" method="post">
                <table border="1">
                    <thead>
                        <tr>
                            <th>管理员编号</th>
                            <th>管理员昵称</th>
                            <th>头像地址</th>
                            <th>管理部门编号</th>
                            <th>当前状态</th>
                            <th>具体操作</th>
                        </tr>
                    </thead>
                <c:forEach items="${requestScope.admins}" var="admin">
                    <tbody>
                        <tr>
                            <td>${admin.adminId}</td>
                            <td>${admin.adminNickName}</td>
                            <td>${admin.adminProfile}</td>
                            <td>${admin.adminDepartmentId}</td>
                            <td>${admin.adminStation}</td>
                            <td>
<%--                            <input type="button" formaction="/detail.jsp?adminId=${admin.adminId}" value="详情">--%>
                                <input type="submit" formaction="<%=contextPath%>/detail.jsp?type=admin&adminId=${admin.adminId}" value="详情">
                                <input type="submit" formaction="<%=contextPath%>/AdminServer?op=adminDeleteById&adminId=${admin.adminId}" value="注销">
                            </td>
                        </tr>
                    </tbody>
                </c:forEach>
                </table>
            </form>
        </c:when>
        <c:when test="${param.type == 'departments'}">
            <form action="" method="post">
                <table border="1">
                    <thead>
                    <tr>
                        <th>部门编号</th>
                        <th>部门名称</th>
                        <th>部门介绍</th>
                        <th>具体操作</th>
                    </tr>
                    </thead>
                    <c:forEach items="${requestScope.departments}" var="department">
                        <tbody>
                        <tr>
                            <td>${department.departmentId}</td>
                            <td>${department.departmentName}</td>
                            <td>${department.departmentIntro}</td>
                            <td>
                                <%-- <input type="button" formaction="/detail.jsp?adminId=${admin.adminId}" value="详情">--%>
                                <input type="submit" formaction="<%=contextPath%>/detail.jsp?type=department&departmentId=${department.departmentId}" value="详情">
                                <input type="submit" formaction="<%=contextPath%>/DepartmentServer?op=departmentDeleteById&departmentId=${department.departmentId}" value="注销">
                            </td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </form>
        </c:when>
        <c:when test="${param.type == 'employees'}">

        </c:when>

    </c:choose>

</body>
</html>
