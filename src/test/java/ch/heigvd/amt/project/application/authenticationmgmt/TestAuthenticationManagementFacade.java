/*
package ch.heigvd.amt.project.application.authenticationmgmt;

import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginFailedException;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterFailedException;
import ch.heigvd.amt.project.domain.user.User;
import ch.heigvd.amt.project.infrastructure.persistence.inMemory.InMemoryUserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestAuthenticationManagementFacade {


    private static String passwordMatchError = "Passwords don't match";
    private static  String nonExistingUserError = "User not found";
    private static  String incorrectPasswordError = "Credentials verification failed";

    private static AuthenticationManagementFacade amf;
    private static User user;

    // this is to initiate all that is needed for all the tests
    @BeforeAll
    static void newUser(){
        user = User.builder()
                .username("JeanTest")
                .email("test@test.te")
                .aboutMe("i am a test person")
                .clearTextPassword("p4ssw0rd")
                .build();
    }

    // this is for all that needs to be brought back in an initial states for each tests
    @BeforeEach
    public void loadStore(){
        User testPerson = User.builder()
                .username(loginUsername)
                .email(loginEmail)
                .clearTextPassword(loginPassword)
                .build();

        InMemoryUserRepository testStore = new InMemoryUserRepository();
        testStore.save(testPerson);
        amf = new AuthenticationManagementFacade(testStore);
    }

    @Test
    public void registerUserMustReturnValidObject() {

        CurrentUserDTO cUser = null;
        try {
            cUser = amf.register(
                    RegisterCommand.builder()
                            .username(newUsername)
                            .email(loginEmail)
                            .clearTextPassword(newPassword)
                            .clearTextPasswordConfirm(newPassword)
                            .build());
        } catch (RegisterFailedException e) {
            e.printStackTrace();
            fail();
        }

        assertNotNull(cUser);
        assertEquals(cUser.getUsername(),newUsername);
    }

    @Test
    public void registerUserMustThrowErrorIfUnmatchPassword(){

        CurrentUserDTO cUser = null;
        try {
            amf.register(RegisterCommand.builder()
                                        .username(newUsername)
                                        .clearTextPassword(newPassword)
                                        .clearTextPasswordConfirm("notmatch")
                                        .build()
            );
        } catch (RegisterFailedException e) {
            e.printStackTrace();
            assertEquals(passwordMatchError,e.getMessage());
        }
    }


    @Test
    public void loginWithExistigUserMustReturnValidObject(){
        CurrentUserDTO cUser = null;

        try {
            cUser = amf.login(LoginCommand.builder().username(loginUsername).clearTextPassword(loginPassword).build());
        } catch (LoginFailedException e) {
            e.printStackTrace();
            fail();
        }

        assertNotNull(cUser);
        assertEquals(cUser.getUsername(),loginUsername);
    }

    @Test
    public void loginWithIncorrectPasswordMustThrowError(){
        try {
            amf.login(LoginCommand.builder().username(loginUsername).clearTextPassword("error").build());
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
*/