package ch.heigvd.amt.project.application.questionmgmt.ask;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collection;

@Builder
@Getter
@EqualsAndHashCode
public class AskCommand {
    private String ownerName;
    private String title;
    private String body;
    private Collection<String> tags; // change when enum tags is done
}
