package ch.heigvd.amt.project.application.authenticationmgmt;

import ch.heigvd.amt.project.domain.user.UserId;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrentUserDTO {
    private UserId id;
    private String username;
    private String email;
    private String aboutMe;
}
