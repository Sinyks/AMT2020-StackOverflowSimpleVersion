package ch.heigvd.amt.project.application.core.app.servicies.authenticationmgmt.questionmgmt.ask;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class AskCommand {

    @Builder.Default
    private String label = "No label";

    @Builder.Default
    private String content = "No content";
}
