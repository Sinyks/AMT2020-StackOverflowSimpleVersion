<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@include file="fragments/header.jsp" %>
<%@include file="fragments/navigation.jsp" %>

<c:if test="${not empty requestScope.success}">
    <div class="alert alert-success text-center" role="alert">${requestScope.success}</div>
</c:if>

<c:if test="${not empty requestScope.failure}">
    <div class="alert alert-danger text-center" role="alert">${requestScope.failure}</div>
</c:if>


<div class="container" style="margin-top:50px">
    <div class="container">
        <h1>Profile info</h1>
        <form class="form-horizontal" action="/profileUpdate.do" method="post">

            <div class="form-group">
                <label for="username">Username</label>
                <input class="form-control" type="text" name="username" id="username"
                       placeholder="${sessionScope.currentUser.username}">
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input class="form-control" type="text" name="email" id="email"
                       placeholder="${sessionScope.currentUser.email}">
            </div>

            <div class="form-group">
                <label for="aboutMe">About me</label>
                <textarea class="form-control" id="aboutMe" name="aboutMe" rows="3"
                          placeholder="${sessionScope.currentUser.aboutMe}"></textarea>
            </div>

            <div class="form-group">
                <input type="submit" value="Update info">
            </div>
        </form>

    </div>


    <div class="container">
        <h2>Password change</h2>
        <form class="form-horizontal" action="/profilePassword.do" method="post">

            <div class="form-group">
                <label for="oldPassword">Password</label>
                <input class="form-control" type="password" name="oldPassword" id="oldPassword"
                       placeholder="Enter your password">
            </div>

            <div class="form-group">
                <label for="newPassword">New password</label>
                <input class="form-control" type="password" name="newPassword" id="newPassword"
                       placeholder="Enter your new password">
            </div>

            <div class="form-group">
                <label for="newPasswordConfirm">New password</label>
                <input class="form-control" type="password" name="newPasswordConfirm" id="newPasswordConfirm"
                       placeholder="Enter your new password again">
            </div>

            <div class="form-group">
                <input type="submit" value="Change password">
            </div>

        </form>

    </div>

</div>

<%@include file="fragments/footer.jsp" %>