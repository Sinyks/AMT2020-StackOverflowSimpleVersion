<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/stackoverflow-simplified/register" method="post">
    <label for="username">username:</label><br>
    <input type="text" id="username" name="username" value="username"><br>
    <label for="password">password:</label><br>
    <input type="text" id="password" name="password" value="password"><br><br>
    <label for="confirmPassword">confirmPassword:</label><br>
    <input type="text" id="confirmPassword" name="confirmPassword" value="confirmPassword"><br><br>
    <input type="submit" value="Submit">
</form>

</body>
</html>