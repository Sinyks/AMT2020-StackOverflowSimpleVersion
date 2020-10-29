<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean scope="request" id="profileInfo" type="ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO"/>


<%@include file="fragments/header.jsp" %>
<%@include file="fragments/navigation.jsp" %>

<div class="container" style="margin-top:50px">
    <div class="container">
        <h1>Profile info</h1>
        <form class="form-horizontal" action="/profileInfo.do" method="post">

            <div class="form-group">
                <label for="username">Username</label>
                <input class="form-control" type="text" name="username" id="username" placeholder="${profileInfo.username}">
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input class="form-control" type="text" name="email" id="email" placeholder="${profileInfo.email}">
            </div>

            <div class="form-group">
                <label for="aboutMe">About me</label>
                <textarea class="form-control" id="aboutMe" name="aboutMe" rows="3" placeholder="${profileInfo.aboutMe}"></textarea>
            </div>

            <div class="form-group">
                <input type="submit" value="Update info">
            </div>
        </form>

    </div>


    <div class="container">
        <h2>Password change</h2>
        <form class="form-horizontal" action="/passwordInfo.do" method="post">
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