package ch.heigvd.amt.project.application.profilemgmt.password.info;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class ProfilePasswordCommand {
    private String currentClearPassword;
    private String newClearTextNewPassword;
    private String newClearTextNewPasswordConfirm;
}
