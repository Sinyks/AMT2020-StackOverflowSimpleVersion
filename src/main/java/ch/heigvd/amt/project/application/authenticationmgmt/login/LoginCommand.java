package ch.heigvd.amt.project.application.authenticationmgmt.login;

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
