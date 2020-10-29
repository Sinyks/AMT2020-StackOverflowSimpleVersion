package ch.heigvd.amt.project.domain.answer;

import ch.heigvd.amt.project.domain.IEntity;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.UserId;
import lombok.*;

import java.time.Instant;
import java.util.Date;

@Getter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Answer implements IEntity<Answer, AnswerId> {

    @Setter(AccessLevel.NONE)
    private AnswerId id;

    private QuestionId questionId;
    private UserId ownerId;

    private Date creationDate;
    private Date lastEditDate;
    private String body;

    public boolean isEdited() {
        return !this.creationDate.equals(this.lastEditDate);
    }

    public void editAnswer(String newBody) {
        if (newBody == null || newBody.isEmpty()) {
            throw new IllegalArgumentException("the new body must contain something");
        }
        this.body = newBody;
        this.lastEditDate = Date.from(Instant.now());
    }

    @Override
    public Answer deepClone() {
        return this.toBuilder()
                .id(new AnswerId(id.asString()))
                .build();
    }

    public static class AnswerBuilder {
        public Answer build() {
            if (id == null) {
                id = new AnswerId();
            }
            if (creationDate == null) {
                creationDate = Date.from(Instant.now());
            }
            if (lastEditDate == null) {
                lastEditDate = creationDate;
            }
            if (ownerId == null) {
                throw new IllegalArgumentException("ownerId mandatory");
            }
            if (questionId == null) {
                throw new IllegalArgumentException("questionId mandatory");
            }
            if (body == null || body.isEmpty()) {
                throw new IllegalArgumentException("body mandatory");
            }

            return new Answer(id, questionId, ownerId, creationDate, lastEditDate, body);
        }
    }
}
