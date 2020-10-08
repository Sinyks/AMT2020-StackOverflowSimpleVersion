package ch.heigvd.amt.project.application.authenticationmgmt;

import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginFailedException;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterFailedException;
import ch.heigvd.amt.project.domain.person.Person;
import ch.heigvd.amt.project.infrastructure.persistence.InMemoryPersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestAuthenticationManagementFacade {

    private final String newUsername = "Vincent";
    private final String newPassword = "pass";

    private final String loginUsername = "User";
    private final String loginPassword = "pass";

    private final String passwordMatchError = "Passwords don't match";
    private final  String nonExistingUserError = "User not found";
    private final  String incorrectPasswordError = "Credentials verification failed";

    private AuthenticationManagementFacade amf = null;

    @BeforeEach
    public void loadStore(){
        Person testPerson = Person.builder().username(loginUsername).clearTextPassword(loginPassword).build();
        InMemoryPersonRepository testStore = new InMemoryPersonRepository();
        testStore.save(testPerson);
        amf = new AuthenticationManagementFacade(testStore);
    }

    @Test
    public void registerUserMustReturnValidObject() {

        CurrentUserDTO cUser = null;
        try {
            cUser = amf.register(RegisterCommand.builder()
                                                .username(newUsername)
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
