<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
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
            <td>${projectTask.endDate}</td>
            <td>Finish date tbc</td>
            <td>${projectTask.plannedHours}</td>
            <td>Hours spent tbc</td>
            <td><a href="/modifyTask?taskId=${projectTask.id}"/>Modify</td>
        </tr>
    </c:forEach>
</table>

<div class="footer">
    <jsp:include page="fragments/footer.jsp">
</div>

</body>
</html>
