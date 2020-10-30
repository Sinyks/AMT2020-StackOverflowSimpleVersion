<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean scope="request" id="questions" type="ch.heigvd.amt.project.application.questionmgmt.QuestionsDTO"/>

<%@include file="fragments/header.jsp"%>
<%@include file="fragments/navigation.jsp"%>

<c:if test="${not empty sessionScope.currentUser.username}">
    <div class="container" style="margin-top:50px">
        <div class="container" style="margin-bottom:20px">
            <h1>Ask your question</h1>
        </div>
        <form class="form-horizontal" action="/askQuestion.do" method="post">
            <div class="form-group">
                <input type="text" class="form-control" id="title" name="title" placeholder="Question title..." required>
            </div>
            <div class="form-group">
                <textarea class="form-control" id="body" name="body" rows="3" placeholder="Question..." required></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit Question</button>
        </form>
    </div>
</c:if>
<div class="container" style="margin-top:50px">
    <div class="container" style="margin-bottom:20px">
        <h1>Others asked these questions</h1>
    </div>
    <c:forEach var="question" items="${questions.questions}">
        <div class="card bg-light mb-3" onclick="location.href='/question?questionId=${question.id}'">
            <div class="card-header">
                <h5>${question.title}</h5>
            </div>
            <div class="card-body">
                <p class="card-text">${question.body}</p>
                <footer class="blockquote-footer">
                    <small class="text-muted">
                        Posted by ${question.ownerName} on ${question.creationDate} (last edited on ${question.lastEditDate})
                    </small>
                </footer>
            </div>
        </div>
    </c:forEach>
</div>

<%@include file="fragments/footer.jsp"%>
