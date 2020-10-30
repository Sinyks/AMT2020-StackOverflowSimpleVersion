package ch.heigvd.amt.project.application.commentmgmt.comment;

import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.UserId;
import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
public class CommentCommand {
    private UserId ownerID;
    private String body;
    private QuestionId questionId;
    private AnswerId answerId;
}
