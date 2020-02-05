<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-03
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>HomePage</title>
    <style>
        .footer{
            position: fixed;
            bottom: 0;
            width: 100%;

        }
    </style>
</head>
<body>
<div class="header">
<jsp:include page="fragments/header.jsp"/>
</div>

<div class="footer">
<jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>
