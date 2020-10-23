package ch.heigvd.amt.project.application.questionmgmt.ask;

import ch.heigvd.amt.project.domain.user.UserId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collection;

@Builder
@Getter
@EqualsAndHashCode
public class AskCommand {
    private String ownerName;
    private UserId ownerId;
    private String title;
    private String body;
    private Collection<String> tags; // change when enum tags is done
}
