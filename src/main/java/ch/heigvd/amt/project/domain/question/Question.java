package ch.heigvd.amt.project.domain.question;

import ch.heigvd.amt.project.domain.IEntity;
import ch.heigvd.amt.project.domain.user.UserId;
import lombok.*;

import java.time.Instant;
import java.util.Date;

@Getter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Question implements IEntity<Question, QuestionId> {

    private QuestionId id;

    private Date creationDate;
    private Date lastEditDate;
    private UserId ownerId;
    private String title;
    private String body;


    public boolean isEdited() {
        return !this.creationDate.equals(this.lastEditDate);
    }

    /**
     * edit both body and title.
     * if you want to only edit one attribute, let the other null or empty
     * correctly update lastEditDate
     *
     * @param newTitle
     * @param newBody
     * @throws IllegalArgumentException if this is called but both attributes are null or empty
     */
    public void editQuestion(String newTitle, String newBody) {
        if ((newBody == null || newBody.isEmpty()) && (newTitle == null || newTitle.isEmpty())) { // if both are empty/null
            throw new IllegalArgumentException("the new body or title must contain something");
        }

        if (newBody == null || newBody.isEmpty()) {
            newBody = this.body;
        } else if (newTitle == null || newTitle.isEmpty()) {
            newTitle = this.title;
        }

        this.body = newBody;
        this.title = newTitle;
        this.lastEditDate = Date.from(Instant.now());
    }


    @Override
    public Question deepClone() {
        return this.toBuilder()
                .id(new QuestionId(id.asString()))
                .build();
    }

    public static class QuestionBuilder {
        public Question build() {
            if (id == null) {
                id = new QuestionId();
            }
            if (creationDate == null) {
                creationDate = Date.from(Instant.now());
            }
            if (lastEditDate == null) {
                lastEditDate = creationDate;
            }
            if (ownerId == null) {
                throw new IllegalArgumentException("Owner mandatory");
            }
            if (title == null || title.isEmpty()) {
                throw new IllegalArgumentException("title mandatory");
            }
            if (body == null || body.isEmpty()) {
                throw new IllegalArgumentException("body mandatory");
            }

            return new Question(id, creationDate, lastEditDate, ownerId, title, body);
        }
    }
}

