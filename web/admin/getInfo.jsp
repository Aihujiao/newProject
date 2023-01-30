<%--
  Created by IntelliJ IDEA.
  User: 40771
  Date: 2023/1/30
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
${title}
</body>
</html>
