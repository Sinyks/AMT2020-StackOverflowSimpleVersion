package ch.heigvd.amt.project.application.profilemgmt.password;

import ch.heigvd.amt.project.domain.user.UserId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class ProfilePasswordCommand {
    private UserId id;
    private String currentClearPassword;
    private String newClearTextPassword;
    private String newClearTextPasswordConfirm;
}
