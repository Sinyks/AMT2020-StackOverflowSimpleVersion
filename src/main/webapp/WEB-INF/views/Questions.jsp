<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean scope="request" id="questions" type="ch.heigvd.amt.project.application.questionmgmt.QuestionsDTO"/>

<%@include file="fragments/header.jsp"%>
<%@include file="fragments/navigation.jsp"%>

<!--Whe should mask the form for unregistered users -->
<div class="container" style="margin-top:50px">
    <h1>Ask your post</h1>
    <form class="form-horizontal" action="/askQuestion.do" method="post">
        <div class="form-group">
            <input type="text" class="form-control" id="title" name="title" placeholder="Question title...">
        </div>
        <div class="form-group">
            <textarea class="form-control" id="body" name="body" rows="3" placeholder="Question..."></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Submit Question</button>
    </form>
</div>
<div class="container" style="margin-top:50px">
    <h1>Others asked these questions</h1>
    <c:forEach var="post" items="${questions.questions}">
        <div class well>
            <h2>${post.title}</h2>
            <p>${post.body}</p>
        </div>
    </c:forEach>
</div>

<%@include file="fragments/footer.jsp"%>
