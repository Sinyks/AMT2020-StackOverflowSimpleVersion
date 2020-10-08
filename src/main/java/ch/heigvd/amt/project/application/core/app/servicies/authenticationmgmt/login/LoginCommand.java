package ch.heigvd.amt.project.application.core.app.servicies.authenticationmgmt.login;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class LoginCommand {
    private String username;
    private String clearTextPassword;
}
