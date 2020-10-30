package ch.heigvd.amt.project.application.answermgmt;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.answermgmt.answer.AnswerFailedException;
import ch.heigvd.amt.project.application.authenticationmgmt.AuthenticationManagementFacade;
import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterFailedException;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsDTO;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsManagementFacade;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsQuery;
import ch.heigvd.amt.project.application.testUtil.testUtils;
import ch.heigvd.amt.project.domain.question.QuestionId;
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
public class answermgmtFacadeIT {

    private final static String WARNAME = "arquillian-managed.war";

    @Inject
    ServiceRegistry serviceRegistry;

    private AnswerManagementFacade answerManagementFacade;
    private QuestionsManagementFacade questionsManagementFacade;
    private AuthenticationManagementFacade authenticationManagementFacade;

    private CurrentUserDTO currentUserDTO;
    private QuestionsDTO questionsDTO;
    private AnswersDTO.AnswerDTO answerDTO;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class,WARNAME)
                .addPackages(true, "ch.heigvd.amt")
                .addClass(BCrypt.class);
    }

    @Before
    public void init(){
        answerManagementFacade = serviceRegistry.getAnswerManagementFacade();
        questionsManagementFacade = serviceRegistry.getQuestionManagementFacade();
        authenticationManagementFacade = serviceRegistry.getAuthenticationManagementFacade();

        // prepare the environement for test

        try {
            currentUserDTO = authenticationManagementFacade.register(testUtils.getRegCommand(testUtils.USERNAME,testUtils.USER_EMAIL));
        } catch (RegisterFailedException e) {
            e.printStackTrace();
        }

        try {
            questionsManagementFacade.ask(testUtils.getAskCommand(currentUserDTO.getId(),currentUserDTO.getUsername()));
            questionsDTO = questionsManagementFacade.getQuestions(QuestionsQuery.builder().build());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void answerToQuestionMustNotThrowException(){
        QuestionId questionId = new QuestionId(questionsDTO.getQuestions().get(0).getId());

        try {
            answerManagementFacade.answer(testUtils.getAnswerCommand(currentUserDTO.getId(),questionId));
        } catch (AnswerFailedException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

}
