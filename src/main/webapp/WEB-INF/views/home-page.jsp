<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-03
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>HomePage</title>
</head>
<body>
<h1>Welcome to home page</h1>
<h1>More magic to come in future!</h1>
<sec:authorize access="isAuthenticated()">
    <form method="post" action="/logout">
        <button type="submit">Logout</button>
        <sec:csrfInput/>
    </form>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
    <form method="post" action="/login">
        <button type="submit">Login</button>
        <sec:csrfInput/>
    </form>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <form method="get" action="/createTask">
        <button type="submit">Create task</button>
        <sec:csrfInput/>
    </form>
</sec:authorize>


</body>
</html>
