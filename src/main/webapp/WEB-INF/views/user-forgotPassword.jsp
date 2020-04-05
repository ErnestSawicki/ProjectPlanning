<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-04-05
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ForgotPassword</title>
</head>
<body>
<form action="/login/forgotPassword" method="post">

    <div>
        <label for="email">Email</label>
        <input type="text" id="email" name="email"/>
    </div>
    <button type="submit">Restart password</button>
    <sec:csrfInput/>
</form>
</body>
</html>
