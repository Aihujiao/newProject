<%@ page import="java.util.List" %>
<%@ page import="ctrl.dao.StationDao" %>
<%@ page import="ctrl.factory.StationFactory" %>
<%@ page import="model.Station" %><%--
  Created by IntelliJ IDEA.
  User: 40771
  Date: 2023/2/5
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../base.jsp"%>

<html>
<head>
    <title>添加工作职位</title>
</head>
<body>
<h2>新增工作职位</h2>
    <form action="<%=contextPath%>/AdminServer">
        <input type="hidden" name="op" value="positionRegister">
        <p>职位名称：<input type="text" name="positionName" placeholder=""></p>
        <p>职位介绍：<textarea name="positionIntro" id="positionIntro" cols="30" rows="10" placeholder="请输入该职位的简要描述"></textarea></p>
        <button>添加职位</button>
    </form>
</body>
</html>
