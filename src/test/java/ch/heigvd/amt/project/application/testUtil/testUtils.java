package ch.heigvd.amt.project.application.testUtil;

import ch.heigvd.amt.project.application.answermgmt.answer.AnswerCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterCommand;
import ch.heigvd.amt.project.application.commentmgmt.comment.CommentCommand;
import ch.heigvd.amt.project.application.questionmgmt.ask.AskCommand;
import ch.heigvd.amt.project.domain.answer.Answer;
import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.comment.Comment;
import ch.heigvd.amt.project.domain.question.Question;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.User;
import ch.heigvd.amt.project.domain.user.UserId;

import java.util.Random;

public class testUtils {
    private static final Random rand = new Random();

    public static final String USERNAME = "Popey" + rand.nextInt();
    public static final String USER_EMAIL = USERNAME + "@mail.com";
    public static final String USER_PASSWORD = "pass1234";

    public final static String QUESTION_TITLE = "testing" + rand.nextInt();
    public final static String QUESTION_BODY = "just for testing";

    public final static String ANSWER_BODY = "just for testing";

    public final static String COMMENT_BODY = "just for testing";

    public final static UserId testUserId = new UserId();

    public final static RegisterCommand testRegCommand = RegisterCommand.builder()
                                                                        .username(USERNAME)
                                                                        .email(USER_EMAIL)
                                                                        .clearTextPassword(USER_PASSWORD)
                                                                        .clearTextPasswordConfirm(USER_PASSWORD)
                                                                        .build();

    public static AskCommand getAskCommand(UserId ownerId, String ownerName){
        return AskCommand.builder()
                .body(QUESTION_BODY)
                .ownerId(ownerId)
                .ownerName(ownerName)
                .title(QUESTION_TITLE)
                .build();
    }

    public static AnswerCommand getAnswerCommand(UserId ownerId, QuestionId questionId){
        return AnswerCommand.builder()
                .body(ANSWER_BODY)
                .ownerID(ownerId)
                .questionId(questionId)
                .build();
    }

    public static CommentCommand getCommentCommandAnswer(UserId ownerId, AnswerId answerId){
        return CommentCommand.builder()
                .answerId(answerId)
                .ownerID(ownerId)
                .body(COMMENT_BODY)
                .build();
    }

    public static CommentCommand getCommentCommandQuestion(UserId ownerId, QuestionId questionId){
        return CommentCommand.builder()
                .questionId(questionId)
                .ownerID(ownerId)
                .body(COMMENT_BODY)
                .build();
    }
}
