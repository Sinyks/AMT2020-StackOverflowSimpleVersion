package ch.heigvd.amt.project.application.votemgmt;

import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.UserId;
import ch.heigvd.amt.project.domain.vote.VoteId;
import lombok.*;

import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
public class VotesDTO {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class VoteDTO {
        private VoteId id;

        private QuestionId questionId;
        private AnswerId answerId;
        private UserId ownerId;
        private boolean isUpVote;
    }

    @Singular
    private List<VoteDTO> votes;
}
