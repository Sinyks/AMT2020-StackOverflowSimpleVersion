package ch.heigvd.amt.project.application.authenticationmgmt;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterFailedException;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import java.util.Random;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class AuthenticationmgmtIT {

    private final static String WARNAME = "arquillian-managed.war";

    private AuthenticationManagementFacade authMF;

    private final static String USERNAME = "Carl" + (new Random()).nextInt();
    private final static String EMAIL = USERNAME + "@mail.com";
    private final static String PASSWORD = "pass1234";

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
}
