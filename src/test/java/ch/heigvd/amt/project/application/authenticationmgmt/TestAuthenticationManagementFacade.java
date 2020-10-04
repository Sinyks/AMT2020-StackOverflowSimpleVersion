package ch.heigvd.amt.project.application.authenticationmgmt;

import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterFailedException;
import ch.heigvd.amt.project.infrastructure.persistence.InMemoryPersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestAuthenticationManagementFacade {

    @Test
    public void registerUserMustReturnNonNullObject() {
        AuthenticationManagementFacade amf = new AuthenticationManagementFacade(new InMemoryPersonRepository());

        CurrentUserDTO cUser = null;
        try {
            String username = "Didier";
            String password = "pass";
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
}
