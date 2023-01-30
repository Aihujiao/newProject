<%--
  Created by IntelliJ IDEA.
  User: 40771
  Date: 2023/1/30
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门注册</title>
</head>
<body>
  <h2>部门注册</h2>
  <form action="/AdminServer">
      <input type="hidden" name="op" value="departmentRegister">
      <input type="text" name="departmentName">
      <input type="text" name="departmentIntro">
      <button>注册部门</button>
  </form>
</body>
</html>
