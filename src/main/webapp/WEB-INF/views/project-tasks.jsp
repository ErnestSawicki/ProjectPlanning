<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-20
  Time: 08:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ProjectTasks</title>
    <style>
        .footer {
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

        th {
            height: 50px;
            text-align: center;
            vertical-align: center;
            padding: 15px;
            font-weight: bold;
        }

        td {
            height: 50px;
            text-align: center;
            vertical-align: center;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        tr:nth-child(even) {
            background-color: cadetblue;
        }

        .row-Lp {
            width: 3%;
        }

        .row-PID {
            width: 5%;
        }

        .row-Description {
            width: 30%;
        }

        .row-Type {
            width: 5%;
        }

        .row-Status {
            width: 5%;
        }

        .row-StartDate {
            width: 7%;
        }

        .row-DueDate {
            width: 7%;
        }

        .row-PlannedHours {
            width: 3%;
        }

        .row-Modify {
            width: 5%;
        }
    </style>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div>
    <form method="post" action="/projectTasks">
        <div class="filler">
            <div class="row">
                <div class="col-25">
                    <label for="taskStatus">TASK STATUS</label>
                </div>
                <div class="col-75">
                    <select id="taskStatus" name="taskStatus">
                        <option value="ALL">ALL</option>
                        <c:forEach items="${taskStatuses}" var="taskStatus">
                            <option value="${taskStatus}">${taskStatus.name()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-25">
                    <label for="projectParticipants">PARTICIPANT</label>
                </div>
                <div class="col-75">
                    <select name="projectParticipants" id="projectParticipants">
                        <option value="ALL">ALL</option>
                        <c:forEach items="${projectParticipants}" var="projectParticipant">
                            <option value="${projectParticipant}">${projectParticipant.firstName} ${projectParticipant.lastName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-25">
                    <label for="startDate">YEAR</label>
                </div>
                <div class="col-75">
                    <input type="date" required name="startDate" id="startDate" value="${taskToModify.startDate}" readonly/>
                </div>
            </div>
        </div>
        <button class="myButton" type="submit">Filter</button>
        <sec:csrfInput/>
    </form>
</div>

<table>
    <tr>
        <th class="row-Lp">Lp.</th>
        <th class="row-PartName">Part name</th>
        <th class="row-PartNumber">Part number</th>
        <th class="row-Assignee">Assignee</th>
        <th class="row-Description">Description</th>
        <th class="row-Type">Type</th>
        <th class="row-Status">Status</th>
        <th class="row-StartDate">Start date</th>
        <th class="row-DueDate">Due date</th>
        <th class="row-DueDate">Finish date</th>
        <th class="row-PlannedHours">Planned hours</th>
        <th class="row-ActualHours">Hours spent</th>
        <th class="row-Modify">Modify</th>
    </tr>
    <c:forEach items="${projectTasks}" var="projectTask" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>PartName tbc</td>
            <td>Part number tbc</td>
            <td>${projectTask.taskAssignee.firstName} ${projectTask.taskAssignee.lastName}</td>
            <td>${projectTask.taskDescription}</td>
            <td>${projectTask.taskType.toString()}</td>
            <td>${projectTask.taskStatus.toString()}</td>
            <td>${projectTask.startDate}</td>
            <td>${projectTask.dueDate}</td>
            <td>${projectTask.finishDate}</td>
            <td>${projectTask.plannedHours}</td>
            <td>${projectTask.actualHours}</td>
            <td><a href="/modifyTask?taskId=${projectTask.id}"/>Modify</td>
        </tr>
    </c:forEach>
</table>

<div class="footer">
    <jsp:include page="fragments/footer.jsp"/>
</div>

</body>
</html>
