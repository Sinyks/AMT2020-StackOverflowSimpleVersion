<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean scope="request" id="question" type="ch.heigvd.amt.project.application.questionmgmt.QuestionsDTO.QuestionDTO"/>

<%@include file="fragments/header.jsp"%>
<%@include file="fragments/navigation.jsp"%>

<div class="jumbotron">
    <div class="card bg-light mb-3">
        <div class="card-header">
            <h1>${question.title}</h1>
        </div>
        <div class="card-body">
            <p>${question.body}</p>

                <div class="row">
                    <c:set var="alreadyVotedOnQuestion" value="false" />
                    <c:forEach var="vote" items="${question.votes.votes}">
                        <c:if test="${vote.ownerId eq sessionScope.currentUser.id}">
                            <c:set var="alreadyVotedOnQuestion" value="true" />
                        </c:if>
                    </c:forEach>
                    <c:if test="${not empty sessionScope.currentUser.username}">
                        <c:if test="${alreadyVotedOnQuestion == 'false'}">
                            <div class="col-sm-1">
                                <form action="/vote.do" method="post">
                                    <div class="form">
                                        <input type="hidden" name="questionId" value="${question.id}"/>
                                        <input type="hidden" name="vote" value="up"/>
                                        <input type="submit" class="btn btn-success" value="Upvote"/>
                                    </div>
                                </form>
                            </div>
                        </c:if>
                    </c:if>
                    <div class="col-sm-1">
                        <div class="text-center">
                            <p class="text-primary">${question.voteTotal}</p>
                        </div>
                    </div>
                    <c:if test="${not empty sessionScope.currentUser.username}">
                        <c:if test="${alreadyVotedOnQuestion == 'false'}">
                            <div class="col-sm-1">
                                <form action="/vote.do" method="post">
                                    <div class="form">
                                        <input type="hidden" name="questionId" value="${question.id}"/>
                                        <input type="hidden" name="vote" value="down"/>
                                        <input type="submit" class="btn btn-danger" value="Downvote"/>
                                    </div>
                                </form>
                            </div>
                        </c:if>
                    </c:if>
                </div>
            <footer class="blockquote-footer">
                <small class="text-muted">
                    Posted by ${question.ownerName} on ${question.creationDate} (last edited on ${question.lastEditDate})
                </small>
            </footer>
        </div>
    </div>
    <div class="container-fluid" style="margin-left:30px;padding-right:80px">
        <c:if test="${not empty sessionScope.currentUser.username}">
            <div class="card bg-light mb-3">
                <div class="card-header">
                    <h5>Comment...</h5>
                </div>
                <div class="card-body">
                    <form action="/comment.do" method="post">
                        <div class="form-group">
                            <textarea class="form-control" name="commentBody" id="commentBody" rows="3"></textarea>
                            <input type="hidden" name="questionId" value="${question.id}"/>
                            <input type="submit" class="btn btn-primary" name="submit" value="Post" style="margin-top:10px"/>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
        <c:forEach var="comment" items="${question.comments.comments}">
            <div class="card bg-light mb-3">
                <div class="card-body">
                    <p>${comment.body}</p>
                    <footer class="blockquote-footer">
                        <small class="text-muted">
                            Posted by ${comment.ownerName} on ${comment.creationDate} (last edited on ${comment.lastEditDate})
                        </small>
                    </footer>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<c:if test="${not empty sessionScope.currentUser.username}">
    <div class="container" style="margin-bottom:20px">
        <div class="card">
            <div class="card-header">
                <h5>Post an answer...</h5>
            </div>
            <div class="card card-body">
                <form action="/answerQuestion.do" method="post">
                    <div class="form-group">
                        <label for="answerBody"></label>
                        <textarea class="form-control" name="answerBody" id="answerBody" rows="3"></textarea>
                        <input type="hidden" name="questionId" value="${question.id}"/>
                        <input type="submit" class="btn btn-primary" name="submit" value="Post" style="margin-top:10px"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</c:if>

<c:forEach var="answer" items="${question.answers.answers}">
    <div class="container" style="margin-bottom:20px">
        <div class="card">
            <div class="card-body">
                <div class="card bg-light mb-3">
                    <div class="card-body">
                        <p>${answer.body}</p>
                            <div class="row">
                                <c:set var="alreadyVotedOnAnswer" value="false" />
                                <c:forEach var="vote" items="${answer.votes.votes}">
                                    <c:if test="${vote.ownerId eq sessionScope.currentUser.id}">
                                        <c:set var="alreadyVotedOnAnswer" value="true" />
                                    </c:if>
                                </c:forEach>
                                <c:if test="${not empty sessionScope.currentUser.username}">
                                    <c:if test="${alreadyVotedOnAnswer == 'false'}">
                                        <div class="col-sm-1">
                                            <form action="/vote.do" method="post">
                                                <div class="form">
                                                    <input type="hidden" name="questionId" value="${question.id}"/>
                                                    <input type="hidden" name="answerId" value="${answer.id}"/>
                                                    <input type="hidden" name="vote" value="up"/>
                                                    <input type="submit" class="btn btn-success" value="Upvote"/>
                                                </div>
                                            </form>
                                        </div>
                                    </c:if>
                                </c:if>
                                <div class="col-sm-1">
                                    <div class="text-center">
                                        <p class="text-primary">${answer.voteTotal}</p>
                                    </div>
                                </div>
                                <c:if test="${not empty sessionScope.currentUser.username}">
                                    <c:if test="${alreadyVotedOnAnswer == 'false'}">
                                        <div class="col-sm-1">
                                            <form action="/vote.do" method="post">
                                                <div class="form">
                                                    <input type="hidden" name="questionId" value="${question.id}"/>
                                                    <input type="hidden" name="answerId" value="${answer.id}"/>
                                                    <input type="hidden" name="vote" value="down"/>
                                                    <input type="submit" class="btn btn-danger" value="Downvote"/>
                                                </div>
                                            </form>
                                        </div>
                                    </c:if>
                                </c:if>
                            </div>
                        <footer class="blockquote-footer">
                            <small class="text-muted">
                                Posted by ${answer.ownerName} on ${answer.creationDate} (last edited on ${answer.lastEditDate})
                            </small>
                        </footer>
                    </div>
                </div>
                <div class="container" style="margin-left:30px">
                    <c:if test="${not empty sessionScope.currentUser.username}">
                        <div class="card bg-light mb-3">
                            <div class="card-header">
                                <h5>Comment...</h5>
                            </div>
                            <div class="card-body">
                                <form action="/comment.do" method="post">
                                    <div class="form-group">
                                        <label for="answerCommentBody"></label>
                                        <textarea class="form-control" name="commentBody" id="answerCommentBody" rows="3"></textarea>
                                        <input type="hidden" name="questionId" value="${question.id}"/>
                                        <input type="hidden" name="answerId" value="${answer.id}"/>
                                        <input type="submit" class="btn btn-primary" name="submit" value="Post" style="margin-top:10px"/>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </c:if>
                    <c:forEach var="comment" items="${answer.comments.comments}">
                        <div class="card bg-light mb-3">
                            <div class="card-body">
                                <p>${comment.body}</p>
                                <footer class="blockquote-footer">
                                    <small class="text-muted">
                                        Posted by ${comment.ownerName} on ${comment.creationDate} (last edited on ${comment.lastEditDate})
                                    </small>
                                </footer>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</c:forEach>


<%@include file="fragments/footer.jsp"%>
