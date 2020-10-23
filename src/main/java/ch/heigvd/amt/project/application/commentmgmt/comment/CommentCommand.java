package ch.heigvd.amt.project.application.answermgmt.answer;

import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.UserId;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@EqualsAndHashCode
public class CommentCommand {
    private UserId ownerID;
    private String body;
    private QuestionId questionId;
}
