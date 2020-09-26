<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="fragments/header.jsp"%>
<body>

<form action="/register" method="post">
    <label for="username">username:</label><br>
    <input type="text" id="username" name="username" value="username"><br>
    <label for="password">password:</label><br>
    <input type="password" id="password" name="password" value="password"><br><br>
    <label for="confirmPassword">confirmPassword:</label><br>
    <input type="password" id="confirmPassword" name="confirm password" value="confirm password"><br><br>
    <input type="submit" value="Submit">
</form>

</body>
</html>