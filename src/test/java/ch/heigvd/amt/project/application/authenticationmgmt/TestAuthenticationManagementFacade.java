package ch.heigvd.amt.project.application.authenticationmgmt;

import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginFailedException;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterFailedException;
import ch.heigvd.amt.project.infrastructure.persistence.InMemoryPersonRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestAuthenticationManagementFacade {

    private final String username = "User";
    private final String password = "pass";

    private final String passwordMatchError = "Passwords don't match";
    private final  String nonExistingUserError = "User not found";
    private final  String incorrectPasswordError = "Credentials verification failed";

    private final AuthenticationManagementFacade amf = new AuthenticationManagementFacade(new InMemoryPersonRepository());

    @Test
    public void registerUserMustReturnNonNullObject() {

        CurrentUserDTO cUser = null;
        try {
            cUser = amf.register(RegisterCommand.builder()
                                                .username(username)
                                                .clearTextPassword(password)
                                                .clearTextPasswordConfirm(password)
                                                .build());
        } catch (RegisterFailedException e) {
            e.printStackTrace();
            fail();
        }

        assertNotNull(cUser);
    }

    @Test
    public void registerUserMustThrowErrorIfUnmatchPassword(){

        CurrentUserDTO cUser = null;
        try {
            amf.register(RegisterCommand.builder()
                                        .username(username)
                                        .clearTextPassword(password)
                                        .clearTextPasswordConfirm("notmatch")
                                        .build()
            );
        } catch (RegisterFailedException e) {
            e.printStackTrace();
            assertEquals(passwordMatchError,e.getMessage());
        }
    }


    @Test
    public void loginWithExistigUserMustReturnNonNullObject(){
        CurrentUserDTO cUser = null;

        try {
            cUser = amf.login(LoginCommand.builder().username(username).clearTextPassword(password).build());
        } catch (LoginFailedException e) {
            e.printStackTrace();
            fail();
        }

        assertNotNull(cUser);
    }

    @Test
    public void loginWithIncorrectPasswordMustThrowError(){
        try {
            amf.login(LoginCommand.builder().username(username).clearTextPassword("error").build());
        } catch (LoginFailedException e) {
            e.printStackTrace();
            assertEquals(incorrectPasswordError,e.getMessage());
        }
    }

    @Test
    public void loginWithNonExistingUserMustThrowError(){
        try {
            amf.login(LoginCommand.builder().username("null").clearTextPassword("null").build());
        } catch (LoginFailedException e) {
            e.printStackTrace();
            assertEquals(nonExistingUserError,e.getMessage());
        }

    }
}
