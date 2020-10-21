package ch.heigvd.amt.project.domain.question;

import ch.heigvd.amt.project.domain.IEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date; // WARNING is a dependency, should we write our own date

@Data
@Builder(toBuilder = true)
public class Question implements IEntity<Question, QuestionId> {

    @Setter(AccessLevel.NONE)
    private QuestionId id;

    private Date creationDate;
    private Date lastEditDate;
    private String ownerName;
    private String title;
    private String body;
    private int voteTotal=0;
    private Collection<String> tags; // will probably be replaced with enum of predefinte tags

    @Override
    public Question deepClone() {
        return this.toBuilder()
                .id(new QuestionId(id.asString()))
                .build();
    }

    public static class QuestionBuilder {
        public Question build(){
            if(id == null){
                id = new QuestionId();
            }
            if(creationDate == null){
                creationDate=Date.from(Instant.now());
            }
            if(lastEditDate == null){
                lastEditDate=creationDate;
            }
            if(ownerName==null){
                throw new IllegalArgumentException("ownerName mandatory");
            }
            if(title == null || title.isEmpty()){
                throw new IllegalArgumentException("title mandatory");
            }
            if(body == null || body.isEmpty()){
                throw new IllegalArgumentException("body mandatory");
            }
            // voteTotal init to 0 by default in java
            if(tags==null){
                tags=new ArrayList<String>(); // until enum is done
            }
            // answerTo can be null for questions

            return new Question(id, creationDate, lastEditDate, ownerName, title, body,voteTotal,tags);
        }
    }
}

