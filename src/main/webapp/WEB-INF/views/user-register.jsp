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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
    <title>UserRegistrationPage</title>
</head>
<body>
<p>Welcome to registerPage new User</p>
<form method="post" action="/register">
    <div>
        <label for="userName">Username</label>
        <input type="text" required name="userName" id="userName" class="form-control" placeholder="Enter your username"/>
    </div>
    <div>
        <label for="firstName">First name</label>
        <input type="text" required name="firstName" id="firstName" class="form-control" placeholder="Enter your first name"/>
    </div>
    <div>
        <label for="lastName">Last name</label>
        <input type="text" required name="lastName" id="lastName" class="form-control" placeholder="Enter your last name"/>
    </div>
    <div>
        <label for="email">Email</label>
        <input type="text" required name="email" id="email" class="form-control" placeholder="Enter your e-mail"/>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" required name="password" id="password" class="form-control" placeholder="Enter your password"/>
    </div>

    <button class="btn btn-primary" type="submit">Register</button>
    <sec:csrfInput/>
</form>
</body>
</html>
