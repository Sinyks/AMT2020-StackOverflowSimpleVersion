package ch.heigvd.amt.project.application.votemgmt.vote;

import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.UserId;
import lombok.*;


@Builder
@Getter
@EqualsAndHashCode
public class VoteCommand {
    private QuestionId questionId;
    private AnswerId answerId;
    private UserId ownerId;
    private boolean isUpVote;
}
