<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-01-18
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <style>
        *:focus {
            outline: none;
        }
        .footer{
            position: fixed;
            bottom: 0;
            width: 100%;
        }
        .form-parent{
            display: table;
            width: 100%;
        }

        .form-login{
            display: table-cell;
            text-align: center;
            vertical-align: middle;
        }

        input{
            width: 30%;
            padding: 12px 20px;
            margin: 8px 0;
            box-sizing: border-box;
            border-radius: 25px;
            background-color: grey;
            color: white;
            text-align: center;
            border-color: #101010;
        }
        label{
            font-family: 'Montserrat';
            font-size: 16px;
        }
        .login-button{
            font-family: 'Montserrat';
            font-size: 16px;
            padding: 12px 20px;
            border-radius: 20px;
            margin: 8px 0;
            box-sizing: border-box;
            background-color: green;
            border-color: black;
        }
        .login-button:hover{
            background-color: aqua;
        }


    </style>
</head>
<body>
<div class="header">
    <jsp:include page="fragments/header.jsp"/>
</div>
<div class="container">
    <div class="row" style="margin-top: 40px">
        <div class="col-1"></div>
        <div class="col-1"></div>
    </div>
    <div class="row">
        <div class="col-1"></div>
        <div class="col-6">
            <c:if test="${param['error'] != null}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    Wrong
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <div class="form-parent">
            <form method="post" action="/login" class="form-login">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" required name="username" id="username" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" required name="password" id="password" class="form-control"/>
                </div>
                <button class="login-button" type="submit">Login</button>
                <sec:csrfInput/>
            </form>
            </div>
        </div>
        <div class="col-5"></div>
    </div>
</div>
<div class="footer">
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>