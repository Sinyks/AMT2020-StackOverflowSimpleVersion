package ch.heigvd.amt.project.application.core.app.servicies.authenticationmgmt;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrentUserDTO {
    private String username;
}
