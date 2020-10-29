package ch.heigvd.amt.project.application.profilemgmt.info;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class ProfileInfoCommand {

    private String newUsername;
    private String newEmail;
    private String newAboutMe;
}
