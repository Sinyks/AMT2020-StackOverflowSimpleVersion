package ch.heigvd.amt.project.application.profilemgmt.info;

import ch.heigvd.amt.project.domain.user.UserId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class ProfileInfoCommand {

    private UserId id;
    private String newUsername;
    private String newEmail;
    private String newAboutMe;
}
