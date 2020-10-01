package ch.heigvd.amt.project.application.authenticationmgmt;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrentUserDTO {
    private String username;
}
