package ch.heigvd.amt.project.application.authenticationmgmt.register;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class RegisterCommand {
    private String username;
    private String clearTextPassword;
    // private String clearTextPasswordConfirm;
}
