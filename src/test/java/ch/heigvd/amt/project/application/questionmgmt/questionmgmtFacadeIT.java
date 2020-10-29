package ch.heigvd.amt.project.application.questionmgmt;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.authenticationmgmt.AuthenticationManagementFacade;
import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterFailedException;
import ch.heigvd.amt.project.application.questionmgmt.ask.AskCommand;
import ch.heigvd.amt.project.application.questionmgmt.ask.AskFailedException;
import ch.heigvd.amt.project.application.testUtil.testUtils;
import ch.heigvd.amt.project.domain.question.Question;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.User;
import ch.heigvd.amt.project.domain.user.UserId;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import javax.swing.text.Utilities;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.JVM)
public class questionmgmtFacadeIT {

    private final static String WARNAME = "arquillian-managed.war";

    private QuestionsManagementFacade questionsManagementFacade;
    private AuthenticationManagementFacade authenticationManagementFacade;

    private CurrentUserDTO currentUserDTO = null;

    @Inject
    ServiceRegistry serviceRegistry;


    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class,WARNAME)
                .addPackages(true, "ch.heigvd.amt")
                .addClass(BCrypt.class);
    }

    @Before
    public void init(){

        questionsManagementFacade = serviceRegistry.getQuestionManagementFacade();
        authenticationManagementFacade = serviceRegistry.getAuthenticationManagementFacade();

        try {
            currentUserDTO = authenticationManagementFacade.register(testUtils.testRegCommand);
        } catch (RegisterFailedException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void aksQuestionWithInvalidUserMustNotThrowException(){

        AskCommand command = testUtils.getAskCommand(currentUserDTO.getId(),currentUserDTO.getUsername());

        try {
            questionsManagementFacade.ask(testUtils.getAskCommand(currentUserDTO.getId(),currentUserDTO.getUsername()));
        } catch (AskFailedException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void getQuestionsMustReturnValidObjects(){
            QuestionsQuery query = QuestionsQuery.builder().build();

            QuestionsDTO questions = questionsManagementFacade.getQuestions(query);

            assertNotNull(questions);
    }

}
