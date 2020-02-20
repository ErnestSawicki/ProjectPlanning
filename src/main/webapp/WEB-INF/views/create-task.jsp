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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>UserRegistrationPage</title>
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <style>
        .footer{
            position: fixed;
            bottom: 0;
            width: 100%;
        }
        input {
            width: 175px;
            padding: 5px 5px;
            margin: 5px 0;
            -webkit-box-sizing: border-box; /* Safari/Chrome, other WebKit */
            -moz-box-sizing: border-box;    /* Firefox, other Gecko */
            box-sizing: border-box;         /* Opera/IE 8+ */
            border-radius: 10px;
            border-color: black;
            font-family: 'Montserrat';
            font-size: 16px;
        }

        select {
            width: 175px;
            padding: 5px 5px;
            margin: 5px 0;
            box-sizing: border-box;
            border-radius: 10px;
            border-color: black;
            font-family: 'Montserrat';
            font-size: 16px;
            cursor: pointer;
        }
        label {
            font-family: 'Montserrat';
            font-size: 20px;
            box-sizing: border-box;
            padding: 5px 5px;
        }
        button{
            font-family: 'Montserrat';
            font-size: 16px;
            padding: 12px 20px;
            border-radius: 20px;
            margin: 8px 0;
            box-sizing: border-box;
            background-color: green;
            border-color: black;
        }
        button:hover{
            background-color: aqua;
        }
        form{
            box-sizing: border-box;
            width: 100%;
            horiz-align: center;
        }
        .container{
            border-radius: 5px;
            padding: 20px;
        }
        .col-25 {
            float: left;
            width: 25%;
            margin-top: 6px;
        }
        .col-75 {
            float: left;
            width: 75%;
            margin-top: 6px;
        }
        .row:after{
            content: "";
            display: table;
            clear: both;
        }
        @media screen and (max-width: 600px) {
            .col-25, .col-75, input[type=submit] {
                width: 100%;
                margin-top: 0;
            }
        }
    </style>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container">
<form method="post" action="/createTask">
    <div class="row">
        <div class="col-25">
        <label for="PID">PID</label>
        </div>
        <div>
            <select id="PID" name="PID">
                <c:forEach items="${projects}" var="project">
                    <option value="${project.PID}">${project.PID}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="row">
        <div class="col-25">
        <label for="taskDescription">TASK DESCRIPTION</label>
        </div>
        <div class="col-75">
        <input type="text" required name="taskDescription" id="taskDescription"/>
        </div>
    </div>
    <div class="row">
        <div class="col-25">
        <label for="startDate">START DATE</label>
        </div>
        <div class="col-75">
        <input type="date" required name="startDate" id="startDate"/>
        </div>
    </div>
    <div class="row">
        <div class="col-25">
        <label for="dueDate">DUE DATE</label>
        </div>
        <div class="col-75">
        <input type="date" required name="dueDate" id="dueDate"/>
        </div>
    </div>
    <div class="row">
        <div class="col-25">
        <label for="plannedHours">HOURS PLANNED</label>
        </div>
        <div class="col-75">
        <input type="number" required name="plannedHours" id="plannedHours"/>
        </div>
    </div>
    <div class="row">
        <div class="col-25">
        <label for="taskStatus">STATUS</label>
        </div>
        <div class="col-75">
        <select id="taskStatus" name="taskStatus">
            <option value="IN_PROGRESS">IN PROGRESS</option>
            <option value="ON_HOLD">ON HOLD</option>
            <option value="CANCELLED">CANCELLED</option>
            <option value="IN_QUEUE">IN QUEUE</option>
            <option value="FINISHED">FINISHED</option>
        </select>
        </div>
    </div>
    <div class="row">
        <div class="col-25">
        <label for="taskType">TYPE</label>
        </div>
        <div class="col-75">
        <select id="taskType" name="taskType">
            <option value="CAD_MODELING">CAD MODELING</option>
            <option value="FEA">FEA</option>
            <option value="PLM">PLM</option>
            <option value="FMEA">FMEA</option>
            <option value="DOC">DOC</option>
            <option value="CALC">CALC</option>
            <option value="DRAWING">DRAWING</option>
            <option value="REQUIREMENTS">REQUIREMENTS</option>
            <option value="OTHER">OTHER</option>
        </select>
        </div>
    </div>
    <div class="row">
        <div class="col-25">
            <label for="assigneeName">ASSIGNEE</label>
        </div>
        <div>
            <select id="assigneeName" name="assigneeName">
                <c:forEach items="${assignees}" var="assignee">
                    <option value="${assignee.username}">${assignee.firstName} ${assignee.lastName}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <button type="submit">CREATE TASK</button>
    <sec:csrfInput/>
</form>
</div>

<div class="footer">
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>
