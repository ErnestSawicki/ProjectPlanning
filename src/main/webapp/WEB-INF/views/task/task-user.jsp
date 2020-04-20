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
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <style>
        .footer{
            position: static;
            bottom: 0;
            width: 100%;
        }
        table {
            border-collapse: collapse;
            width: 100%;
        }

        table, th, td {
            border: 1px solid black;
            font-family: Montserrat;
            font-size: 16px;
        }

        th{
            height: 50px;
            text-align: center;
            vertical-align: center;
            padding: 15px;
            font-weight: bold;
        }

        td{
            height: 50px;
            text-align: center;
            vertical-align: center;
        }

        tr:hover{
            background-color: #f5f5f5;
        }
        tr:nth-child(even) {background-color: cadetblue;}
        .row-Lp{
            width: 3%;
        }
        .row-PID{ width: 5%;}
        .row-Description{ width: 30%;}
        .row-Type{
            width: 5%;}
        .row-Status{
            width: 5%;
        }
        .row-StartDate{width: 7%;}
        .row-DueDate{width: 7%;}
        .row-PlannedHours{width: 3%;}
        .row-Modify{width: 5%;}
    </style>
    <title>UserTasks</title>
</head>
<body>
<jsp:include page="../fragments/header.jsp"/>
<div class="container">
<form method="post" action="/userTasks">
    <select name="taskStatus">
        <option value="ALL">ALL</option>
        <c:forEach items="${taskStatuses}" var="taskStatus">
            <option value="${taskStatus}">${taskStatus.name()}</option>
        </c:forEach>
    </select>
    <select name="taskType">
        <option value="ALL">ALL</option>
        <c:forEach items="${taskTypes}" var="taskType">
            <option value="${taskType}">${taskType.name()}</option>
        </c:forEach>
    </select>
    <button class="login-button" type="submit">Filter</button>
    <sec:csrfInput/>
</form>
</div>
<table>
    <tr>
        <th class="row-Lp">Lp.</th>
        <th class="row-PID">PID</th>
        <th class="row-Type">Type</th>
        <th class="row-Description">Description</th>
        <th class="row-Status">Status</th>
        <th class="row-StartDate">Start date</th>
        <th class="row-DueDate">Due date</th>
        <th class="row-PlannedHours">Planned hours</th>
        <th class="row-Modify">Modify task</th>
    </tr>
    <c:forEach items="${userTasks}" var="userTask" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${userTask.project.PID}</td>
            <td>${userTask.taskType}</td>
            <td>${userTask.taskDescription}</td>
            <td>${userTask.taskStatus}</td>
            <td>${userTask.startDate}</td>
            <td>${userTask.dueDate}</td>
            <td>${userTask.plannedHours}</td>
            <td><a href="/task/modifyTask?taskId=${userTask.id}"/>Modify</td>
        </tr>
    </c:forEach>
</table>
<div class="footer">
    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>
</html>
