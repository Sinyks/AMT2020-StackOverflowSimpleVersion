<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="fragments/header.jsp"%>
<%@include file="fragments/navigation.jsp"%>

<div class="jumbotron text-center">
    <h1>Welcome!</h1>
    <p>Our application has ${requestScope.userCount} user(s)!</p>
    <p>${requestScope.questionCount} question(s) has(have) been asked!</p>
    <p>${requestScope.answerCount} answer(s) has(have) been given!</p>
    <p>We are a QA webpage!</p>
</div>

<div class="jumbotron text-center">
    <p>${requestScope.scoreboard}</p>
</div>

<div class="d-flex justify-content-center">
    <button onclick="location.href='/questions'" type="button" class="btn-primary btn-lg">Browse questions...</button>
</div>

<%@include file="fragments/footer.jsp"%>