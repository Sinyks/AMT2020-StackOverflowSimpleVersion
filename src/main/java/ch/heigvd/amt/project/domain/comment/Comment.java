package ch.heigvd.amt.project.domain.comment;

import ch.heigvd.amt.project.domain.IEntity;
import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.UserId;
import lombok.*;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Comment implements IEntity<Comment, CommentId> {
    @Setter(AccessLevel.NONE)
    private CommentId id;

    private UserId ownerId;
    private AnswerId answerId;
    private QuestionId questionId;
    private Date creationDate;
    private Date lastEditDate;
    private String body;

    @Override
    public Comment deepClone() {
        return this.toBuilder()
                .id(new CommentId(id.asString()))
                .build();
    }

    public static class CommentBuilder{
        public Comment build(){
            if(id == null){
                id = new CommentId();
            }
            if(ownerId==null){
                throw new IllegalArgumentException("ownerId mandatory");
            }
            if(answerId == null && questionId == null){
                throw new IllegalArgumentException("answerId or questionId mandatory");
            }
            if(answerId != null && questionId != null){
                throw new IllegalArgumentException("one and only one of answerId or questionId");
            }
            if(creationDate == null){
                creationDate=Date.from(Instant.now());
            }
            if(lastEditDate == null){
                lastEditDate=creationDate;
            }
            if(body == null || body.isEmpty()){
                throw new IllegalArgumentException("body mandatory");
            }

            Comment newComment = new Comment(id, ownerId, answerId, questionId, creationDate, lastEditDate, body);
            return newComment;
        }
    }
}
