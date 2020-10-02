<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean scope="request" id="questions" type="ch.heigvd.amt.project.application.questionmgmt.QuestionsDTO"/>

<%@include file="fragments/header.jsp"%>
<%@include file="fragments/navigation.jsp"%>

<div class="container" style="margin-top:50px">
    <form id="newQuestion" method="POST" action="/askQuestion.do">
        <textarea id="tfLabel" name="label" form="newQuestion"/>
        <textarea id="tfContent" name="content" form="newQuestion"/>
        <button id="bSubmitQuestion" type="submit">Submit Question</button>
    </form>
</div>
<div class="container">
    <c:forEach var="question" items="${questions.questions}">
        <div class well>
            <h2>${question.label}</h2>
            <p>${question.content}</p>
        </div>
    </c:forEach>
</div>

<%@include file="fragments/footer.jsp"%>
