<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-20
  Time: 09:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ChooserProject</title>
</head>
<body>
<form method="get" action="/projectTasks">
    <select name="choseProject">
        <c:forEach items="${userProjects}" var="userProject">
            <option value="${userProject}">${userProject.PID}</option>
        </c:forEach>
    </select>
    <button class="myButton" type="submit">Open project</button>
    <sec:csrfInput/>
</form>
</body>
</html>
