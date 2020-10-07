<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="fragments/header.jsp"%>
<%@include file="fragments/navigation.jsp"%>

<div class="container" style="margin-top:50px">
    <h1>Register</h1>
    <form class="form-horizontal" action="/register.do" method="post">
        <div class="form-group">
            <label class="control-label col-sm-2" for="username">Username</label>
            <div class="col-sm-10">
                <input type="text" id="username" name="username" placeholder="username">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">Email</label>
            <div class="col-sm-10">
                <input type="text" id="email" name="email" placeholder="email">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="password">Password</label>
            <div class="col-sm-10">
                <input type="password" id="password" name="password" placeholder="password">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="confirmPassword">Confirm Password</label>
            <div class="col-sm-10">
                <input type="password" id="confirmPassword" name="confirmPassword" placeholder="confirm password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="submit" value="Submit">
            </div>
        </div>
    </form>
</div>

<%@include file="fragments/footer.jsp"%>