package ch.heigvd.amt.project.domain.vote;

import ch.heigvd.amt.project.domain.IEntity;
import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.UserId;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Vote implements IEntity<Vote, VoteId> {

    @Setter(AccessLevel.NONE)
    private VoteId id;

    private QuestionId questionId;
    private AnswerId answerId;
    private UserId ownerId;
    private boolean isUpVote;

    @Override
    public Vote deepClone() {
        return this.toBuilder()
                .id(new VoteId(id.asString()))
                .build();
    }

    public static class VoteBuilder{
        public Vote build(){
            if(id == null){
                id = new VoteId();
            }
            if(questionId==null){
                throw new IllegalArgumentException("questionId mandatory");
            }
            if(answerId==null){
                throw new IllegalArgumentException("answerId mandatory");
            }
            if(ownerId==null){
                throw new IllegalArgumentException("userId mandatory");
            }

            Vote newVote = new Vote(id, questionId, answerId, ownerId, isUpVote);
            return newVote;
        }
    }
}
