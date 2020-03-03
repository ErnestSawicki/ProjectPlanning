<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-03-03
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        .submitButton{
            font-family: 'Montserrat';
            font-size: 16px;
            padding: 12px 20px;
            border-radius: 20px;
            margin: 8px 0;
            box-sizing: border-box;
            background-color: green;
            border-color: black;
        }
        .submitButton:hover{
            background-color: aqua;
        }


    </style>
    <title>UserUpdateProfile</title>
</head>
<body>
<%--attibute = "userProfile"--%>
<div class="header">
    <jsp:include page="fragments/header.jsp"/>
</div>

<div>
    <form method="post" action="/user/updateProfile">
        <div class="row">
            <div class="col-25">
                <label for="username">Username</label>
            </div>
            <div class="col-75">
                <input type="text" id="username" required name="username" class="form-control" value="${userProfile.username}" readonly/>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="firstName">First name</label>
            </div>
            <div class="col-75">
                <input type="text" required name="firstName" id="firstName" class="form-control"
                       value="${userProfile.firstName}"/>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="lastName">Last name</label>
            </div>
            <div class="col-75">
                <input type="text" required name="lastName" id="lastName" class="form-control"
                       value="${userProfile.lastName}"/>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="email">Email</label>
            </div>
            <div class="col-75">
                <input type="text" required name="email" id="email" class="form-control" value="${userProfile.email}"/>
            </div>
        </div>

        <button class="submitButton" type="submit">Register</button>
        <sec:csrfInput/>
    </form>
</div>

<div class="footer">
    <jsp:include page="fragments/footer.jsp"/>
</div>

</body>
</html>
