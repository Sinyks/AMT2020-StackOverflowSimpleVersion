package ch.heigvd.amt.project.domain.vote;

import ch.heigvd.amt.project.domain.IEntity;
import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.UserId;
import lombok.*;

@Getter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Vote implements IEntity<Vote, VoteId> {

    private VoteId id; // this is really artificial

    private QuestionId questionId;
    private AnswerId answerId;
    private UserId ownerId;
    private boolean isUpVote;

    public void invertVote() {
        isUpVote = !isUpVote;
    }

    @Override
    public Vote deepClone() {
        return this.toBuilder()
                .id(new VoteId(id.asString()))
                .build();
    }

    public static class VoteBuilder {
        public Vote build() {
            if (id == null) {
                id = new VoteId();
            }
            if (answerId == null && questionId == null) {
                throw new IllegalArgumentException("answerId or questionId mandatory");
            }
            if (answerId != null && questionId != null) {
                throw new IllegalArgumentException("one and only one of answerId or questionId");
            }
            if (ownerId == null) {
                throw new IllegalArgumentException("userId mandatory");
            }

            //isUpvote is false by default per java standard

            Vote newVote = new Vote(id, questionId, answerId, ownerId, isUpVote);
            return newVote;
        }
    }
}
