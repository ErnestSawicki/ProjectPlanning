<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-03
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        .footer{
            position: fixed;
            bottom: 0;
            width: 100%;
        }
        input {
            width: 125px;
            padding: 5px;
            -webkit-box-sizing: border-box; /* Safari/Chrome, other WebKit */
            -moz-box-sizing: border-box;    /* Firefox, other Gecko */
            box-sizing: border-box;         /* Opera/IE 8+ */
        }
    </style>
    <title>UserRegistrationPage</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<p>Task creator</p>
<form method="post" action="/createTask">
    <div>
        <label for="PID">PID</label>
        <input type="text" required name="PID" id="PID"/>
    </div>
    <div>
        <label for="taskDescription">taskDescription</label>
        <input type="text" required name="taskDescription" id="taskDescription"/>
    </div>
    <div>
        <label for="startDate">startDate</label>
        <input type="date" required name="startDate" id="startDate"/>
    </div>
    <div>
        <label for="endDate">endDate</label>
        <input type="date" required name="endDate" id="endDate"/>
    </div>
    <div>
        <label for="plannedHours">plannedHours</label>
        <input type="number" required name="plannedHours" id="plannedHours"/>
    </div>

    <button class="btn btn-primary" type="submit">Register</button>
    <sec:csrfInput/>
</form>

<div class="footer">
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>
