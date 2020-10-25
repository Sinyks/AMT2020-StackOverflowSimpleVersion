package ch.heigvd.amt.project.application.authenticationmgmt;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginFailedException;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterFailedException;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runners.MethodSorters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import java.util.Random;
import static org.junit.Assert.*;


@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.JVM)
public class AuthenticationmgmtIT {

    private final static String WARNAME = "arquillian-managed.war";

    private AuthenticationManagementFacade authMF;

    private static final Random rand = new Random();

    private static final String USERNAME = "Carl" + rand.nextInt();
    private static final String EMAIL = USERNAME + "@mail.com";
    private static final String PASSWORD = "pass1234";

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
        authMF = serviceRegistry.getAuthenticationManagementFacade();
    }

    @Test
    public void registerUserMustReturnValidObject() {

        RegisterCommand registerCmd = RegisterCommand.builder()
                .username(USERNAME)
                .email(EMAIL)
                .clearTextPassword(PASSWORD)
                .clearTextPasswordConfirm(PASSWORD)
                .build();

        CurrentUserDTO currentUserDTO = null;

        try {
            currentUserDTO = this.authMF.register(registerCmd);
        } catch (RegisterFailedException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        assertNotNull(currentUserDTO);
        assertEquals(currentUserDTO.getUsername(), USERNAME);
        assertEquals(currentUserDTO.getEmail(), EMAIL);
    }

    @Test
    public void loginWithValidUserMustReturnValidObject(){
        LoginCommand loginCmd = LoginCommand.builder()
                .username(USERNAME)
                .clearTextPassword(PASSWORD)
                .build();

        CurrentUserDTO currentUserDTO = null;

        try {
            currentUserDTO = this.authMF.login(loginCmd);
        } catch (LoginFailedException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        assertNotNull(currentUserDTO);
        assertEquals(currentUserDTO.getUsername(),USERNAME);
        assertEquals(currentUserDTO.getEmail(),EMAIL);
    }

    @Test
    public void registerUserWithWrongPasswordConfirmMustFailed(){

        String username = "test"+rand.nextInt();

        RegisterCommand registerCmd = RegisterCommand.builder()
                .username(username)
                .email(username +"@mail.com")
                .clearTextPassword(PASSWORD)
                .clearTextPasswordConfirm("wrong")
                .build();

        try {
            this.authMF.register(registerCmd);
            fail("Wrong password Must failed");
        } catch (RegisterFailedException e) {
            assertNotNull(e);
        }

    }
}

