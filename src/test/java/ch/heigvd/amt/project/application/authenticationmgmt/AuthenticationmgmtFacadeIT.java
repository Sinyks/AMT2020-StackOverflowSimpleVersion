package ch.heigvd.amt.project.application.authenticationmgmt;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginFailedException;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterFailedException;
import ch.heigvd.amt.project.application.testUtil.testUtils;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import static org.junit.Assert.*;


@RunWith(Arquillian.class)
public class AuthenticationmgmtFacadeIT {

    private final static String WARNAME = "arquillian-managed.war";

    private AuthenticationManagementFacade authenticationManagementFacade;

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
        authenticationManagementFacade = serviceRegistry.getAuthenticationManagementFacade();
    }

    @Test
    public void registerUserMustReturnValidObject() {

        CurrentUserDTO currentUserDTO = null;

        try {
            currentUserDTO = this.authenticationManagementFacade.register(testUtils.getRegCommand(testUtils.USERNAME,testUtils.USER_EMAIL));
        } catch (RegisterFailedException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        assertNotNull(currentUserDTO);
        assertEquals(currentUserDTO.getUsername(), testUtils.USERNAME);
        assertEquals(currentUserDTO.getEmail(), testUtils.USER_EMAIL);
    }

    @Test
    public void loginWithValidUserMustReturnValidObject(){
        String currentUsername = testUtils.randUsername();
        String currentEmail = currentUsername + "@mail.com";
        CurrentUserDTO currentUserDTO = null;
        try {
            currentUserDTO = this.authenticationManagementFacade.register(testUtils.getRegCommand(currentUsername,currentEmail));
        } catch (RegisterFailedException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }


        LoginCommand loginCmd = LoginCommand.builder()
                .username(currentUserDTO.getUsername())
                .clearTextPassword(testUtils.USER_PASSWORD)
                .build();

        try {
            currentUserDTO = this.authenticationManagementFacade.login(loginCmd);
        } catch (LoginFailedException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        assertNotNull(currentUserDTO);
        assertEquals(currentUserDTO.getUsername(),currentUsername);
        assertEquals(currentUserDTO.getEmail(),currentEmail);
    }

    @Test
    public void registerUserWithWrongPasswordConfirmMustFailed(){

        String username = testUtils.USERNAME;

        RegisterCommand registerCmd = RegisterCommand.builder()
                .username(username)
                .email(username +"@mail.com")
                .clearTextPassword(testUtils.USER_PASSWORD)
                .clearTextPasswordConfirm("wrong")
                .build();

        try {
            this.authenticationManagementFacade.register(registerCmd);
            fail("Wrong password Must failed");
        } catch (RegisterFailedException e) {
            assertNotNull(e);
        }

    }
}

