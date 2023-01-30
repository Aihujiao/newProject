<%@ page import="model.Admin" %>
<%@ page import="ctrl.AdminCtrl" %><%--
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
    <c:choose>
        <c:when test="${type== admin}">
            <%
                int adminId = Integer.parseInt(request.getParameter("adminId"));
                Admin admin = new Admin();
                AdminCtrl adminCtrl = new AdminCtrl();
                adminCtrl.getAdminById(adminId);
            %>
            <p>管理员昵称:${sessionScope.admin.adminNickName}</p>
            <p>管理员昵称:${sessionScope.admin.adminNickName}</p>
        </c:when>
    </c:choose>
</body>
</html>
