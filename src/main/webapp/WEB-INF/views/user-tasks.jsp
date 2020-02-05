<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-04
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>UserTasks</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<table border="black">
    <tr>
        <th>Lp.</th>
        <th>PID</th>
        <th>Description</th>
        <th>Start date</th>
        <th>End date</th>
    </tr>
    <c:forEach items="${userTasks}" var="userTask" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${userTask.PID}</td>
            <td>${userTask.taskDescription}</td>
            <td>${userTask.startDate}</td>
            <td>${userTask.endDate}</td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
