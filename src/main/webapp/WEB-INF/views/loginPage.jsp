<%--
  Created by IntelliJ IDEA.
  User: bowen
  Date: 2019/7/25
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>登录页</title>
    <form method="post" action="/login/toJson">
        用户名:<input type="text" name="username"/>
        密码:<input type="password" name="password"/>
        <input type="submit" value="提交"/>
    </form>
    <a href="/toRegister" >注册</a>
</head>
<body>

</body>
</html>
