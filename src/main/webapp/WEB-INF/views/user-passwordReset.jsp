<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-04-18
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
Here you can reset your password:
Your token is :
<div>${confirmationToken}</div>
User information:
<div>${user.toString()}</div>
<form method="post" action="/login/confirm-reset">
    <div>
        <label for="newPassword">New Password</label>
    </div>
    <div>
        <input type="text" name="newPassword" id="newPassword">
    </div>
    <div>
        <label for="confirmPassword">Confirm Password</label>
    </div>
    <div>
        <input type="text" name="confirmPassword" id="confirmPassword">
    </div>
    <input type="hidden" name="username" value="${user.username}">
    <button type="submit">Reset password</button>
    <sec:csrfInput/>
</form>
</body>
</html>
