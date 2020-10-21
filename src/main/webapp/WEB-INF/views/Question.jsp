<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean scope="request" id="question" type="ch.heigvd.amt.project.application.questionmgmt.QuestionsDTO.QuestionDTO"/>

<%@include file="fragments/header.jsp"%>
<%@include file="fragments/navigation.jsp"%>

<div class="container" style="margin-top:50px">
    <h1>${question.title}</h1>
    <p>${question.body}</p>
</div>

<%@include file="fragments/footer.jsp"%>
