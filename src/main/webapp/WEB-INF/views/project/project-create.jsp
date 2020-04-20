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
    <title>ProjectRegistrationPage</title>
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
<jsp:include page="../fragments/header.jsp"/>

<div class="container">
    <form method="post" action="/project/createProject">
        <div class="row">
            <div class="col-25">
                <label for="PID">PID</label>
            </div>
            <div class="col-75">
                <input type="text" required name="PID" id="PID"/>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="name">Name</label>
            </div>
            <div class="col-75">
                <input type="text" required name="name" id="name"/>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="projectDescription">Description</label>
            </div>
            <div class="col-75">
                <input type="text" required name="projectDescription" id="projectDescription"/>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="projectMaturity">STATUS</label>
            </div>
            <div class="col-75">
                <select id="projectMaturity" name="projectMaturity">
                    <option value="A">A Sample</option>
                    <option value="B">B Sample</option>
                    <option value="C">C Sample</option>
                    <option value="SERIES">SERIES</option>
                    <option value="N_A">N/A</option>
                </select>
            </div>
        </div>

        <button type="submit">CREATE PROJECT</button>
        <sec:csrfInput/>
    </form>
</div>

<div class="footer">
    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>
</html>
