<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean scope="request" id="question" type="ch.heigvd.amt.project.application.questionmgmt.QuestionsDTO.QuestionDTO"/>

<%@include file="fragments/header.jsp"%>
<%@include file="fragments/navigation.jsp"%>

<div class="container" style="margin-top:50px">
    <h1>${question.title}</h1>
    <p>${question.body}</p>
    <c:if test="${not empty sessionScope.currentUser.username}">
        <div class="card-body">
            <form action="/comment.do" method="post">
                <div class="form-group">
                    <label for="commentBody">Your comment</label>
                    <textarea class="form-control" name="commentBody" id="commentBody" rows="3"></textarea>
                    <input type="hidden" name="questionId" value="${question.id}"/>
                    <input type="submit" class="btn btn-primary" name="submit" value="post comment"/>
                </div>
            </form>
        </div>
    </c:if>
    <c:forEach var="comment" items="${question.comments.comments}">
        <div class="card">
            <div class="card-body">
                <p>${comment.body}</p>
            </div>
        </div>
    </c:forEach>
</div>

<c:if test="${not empty sessionScope.currentUser.username}">
    <div class="card card-body">
        <form action="/answerQuestion.do" method="post">
            <div class="form-group">
                <label for="answerBody">Your answer</label>
                <textarea class="form-control" name="answerBody" id="answerBody" rows="3"></textarea>
                <input type="hidden" name="questionId" value="${question.id}"/>
                <input type="submit" class="btn btn-primary" name="submit" value="post answer"/>
            </div>
        </form>
    </div>
</c:if>

<c:forEach var="answer" items="${question.answers.answers}">
    <div class="card">
        <div class="card-body">
            <p>${answer.body}</p>
            <c:if test="${not empty sessionScope.currentUser.username}">
                <div class="card-body">
                    <form action="/comment.do" method="post">
                        <div class="form-group">
                            <label for="answerCommentBody">Your comment</label>
                            <textarea class="form-control" name="commentBody" id="answerCommentBody" rows="3"></textarea>
                            <input type="hidden" name="questionId" value="${question.id}"/>
                            <input type="hidden" name="answerId" value="${answer.id}"/>
                            <input type="submit" class="btn btn-primary" name="submit" value="post comment"/>
                        </div>
                    </form>
                </div>
            </c:if>
            <c:forEach var="comment" items="${answer.comments.comments}">
                <div class="card">
                    <div class="card-body">
                        <p>${comment.body}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:forEach>


<%@include file="fragments/footer.jsp"%>
