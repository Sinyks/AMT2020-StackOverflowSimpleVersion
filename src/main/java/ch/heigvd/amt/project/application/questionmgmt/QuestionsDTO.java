package ch.heigvd.amt.project.application.questionmgmt;

import ch.heigvd.amt.project.domain.user.UserId;
import lombok.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@EqualsAndHashCode
public class QuestionsDTO {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class QuestionDTO {
        private UUID id;
        private Date creationDate;
        private Date lastEditDate;
        private UserId ownerId;
        private String title;
        private String body;
        /*private int voteTotal;
        private Collection<String> tags;*/
    }

    @Singular
    private List<QuestionDTO> questions;
}
