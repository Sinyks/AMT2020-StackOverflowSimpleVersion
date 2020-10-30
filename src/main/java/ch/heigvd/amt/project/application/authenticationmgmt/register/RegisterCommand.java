package ch.heigvd.amt.project.application.authenticationmgmt.register;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class RegisterCommand {
    //no aboutMe in register command, only in user modification
    private String username;
    private String email;
    private String clearTextPassword;
    private String clearTextPasswordConfirm;
}
