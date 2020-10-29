package ch.heigvd.amt.project.application.commentmgmt;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.answermgmt.AnswerManagementFacade;
import ch.heigvd.amt.project.application.answermgmt.AnswersDTO;
import ch.heigvd.amt.project.application.answermgmt.answer.AnswerFailedException;
import ch.heigvd.amt.project.application.answermgmt.answer.CommentFailedException;
import ch.heigvd.amt.project.application.authenticationmgmt.AuthenticationManagementFacade;
import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterFailedException;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsDTO;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsManagementFacade;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsQuery;
import ch.heigvd.amt.project.application.testUtil.testUtils;
import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.comment.Comment;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.User;
import org.checkerframework.checker.units.qual.C;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;

import static org.junit.Assert.fail;

@RunWith(Arquillian.class)
public class commentmgmtFacadeIT {

    private final static String WARNAME = "arquillian-managed.war";

    @Inject
    ServiceRegistry serviceRegistry;

    private CommentManagementFacade commentManagementFacade;

    private AnswerManagementFacade answerManagementFacade;
    private QuestionsManagementFacade questionsManagementFacade;
    private AuthenticationManagementFacade authenticationManagementFacade;

    CurrentUserDTO currentUserDTO;
    QuestionsDTO questionsDTO;
    AnswersDTO answersDTO;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class,WARNAME)
                .addPackages(true, "ch.heigvd.amt")
                .addClass(BCrypt.class);
    }

    @Before
    public void init(){
        commentManagementFacade = serviceRegistry.getCommentManagementFacade();

        answerManagementFacade = serviceRegistry.getAnswerManagementFacade();
        questionsManagementFacade = serviceRegistry.getQuestionManagementFacade();
        authenticationManagementFacade = serviceRegistry.getAuthenticationManagementFacade();

        // prepare the environement for test

        try {
            currentUserDTO = authenticationManagementFacade.register(testUtils.testRegCommand);
        } catch (RegisterFailedException e) {
            e.printStackTrace();
        }

        try {
            questionsManagementFacade.ask(testUtils.getAskCommand(currentUserDTO.getId(),currentUserDTO.getUsername()));
            questionsDTO = questionsManagementFacade.getQuestions(QuestionsQuery.builder().build());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            answerManagementFacade.answer(testUtils.getAnswerCommand(
                                            currentUserDTO.getId(),
                                            new QuestionId(questionsDTO.getQuestions().get(0).getId()))
            );
        } catch (AnswerFailedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commentValidAnswerMustNotThrowException(){
        answersDTO = answerManagementFacade.getAnswers(new QuestionId(questionsDTO.getQuestions().get(0).getId()));

        try {
            commentManagementFacade.comment(testUtils.getCommentCommandAnswer(
                    currentUserDTO.getId(),
                    new AnswerId(answersDTO.getAnswers().get(0).getId()))
            );
        } catch (CommentFailedException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }



}
