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
    <title>UserRegistrationPage</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<form method="post" action="/user/register">
    <div class="row">
        <div class="col-25">
            <label for="username">Username</label>
        </div>
        <div class="col-75">
            <input type="text" required name="username" id="username" class="form-control"
                   placeholder="Enter your username"/>
        </div>
    </div>
    <div class="row">
        <div class="col-25">
            <label for="firstName">First name</label>
        </div>
        <div class="col-75">
            <input type="text" required name="firstName" id="firstName" class="form-control"
                   placeholder="Enter your first name"/>
        </div>
    </div>
    <div class="row">
        <div class="col-25">
            <label for="lastName">Last name</label>
        </div>
        <div class="col-75">
            <input type="text" required name="lastName" id="lastName" class="form-control"
                   placeholder="Enter your last name"/>
        </div>
    </div>
    <div class="row">
        <div class="col-25">
            <label for="email">Email</label>
        </div>
        <div class="col-75">
            <input type="text" required name="email" id="email" class="form-control" placeholder="Enter your e-mail"/>
        </div>
    </div>
    <div class="row">
        <div class="col-25">
        <label for="password">Password</label>
        </div>
        <div class="col-75">
        <input type="password" required name="password" id="password" class="form-control"
               placeholder="Enter your password"/>
        </div>
    </div>

    <button class="myButton" type="submit">Register</button>
    <sec:csrfInput/>
</form>

<div class="footer">
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>
