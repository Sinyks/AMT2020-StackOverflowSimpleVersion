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


<div class="d-flex justify-content-center">
    <button onclick="location.href='/questions'" type="button" class="btn-primary btn-lg">Browse questions...</button>
</div>

<div class="container" style="margin-top:30px">
    <table class="table table-dark">
        <thead>
            <tr>
                <th scope="col">Username</th>
                <th scope="col">Badges</th>
                <th scope="col">Color</th>
                <th scope="col">Description</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="userReputation" items="${requestScope.userReputations}">
                <c:forEach var="badge" items="${userReputation.badges}">
                    <tr>
                        <td>${userReputation.username}</td>
                        <td>${badge.name}</td>
                        <td>${badge.color}</td>
                        <td>${badge.description}</td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </tbody>
    </table>
</div>

<div class="container" style="margin-top:30px">
    <table class="table table-dark">
        <thead>
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Pointscale</th>
            <th scope="col">Points</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="userReputation" items="${requestScope.userReputations}">
            <c:forEach var="pointscale" items="${userReputation.pointscales}">
                <tr>
                    <td>${userReputation.username}</td>
                    <td>${pointscale.label}</td>
                    <td>${pointscale.pointCounter}</td>
                </tr>
            </c:forEach>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="fragments/footer.jsp"%>