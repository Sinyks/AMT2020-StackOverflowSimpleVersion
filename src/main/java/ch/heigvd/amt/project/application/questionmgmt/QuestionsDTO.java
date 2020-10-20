package ch.heigvd.amt.project.application.questionmgmt;

import ch.heigvd.amt.project.domain.question.QuestionId;
import lombok.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
public class QuestionsDTO {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class QuestionDTO {
        private QuestionId id;
        private Date creationDate;
        private Date lastEditDate;
        private String ownerName;
        private String title;
        private String body;
        private int voteTotal;
        private Collection<String> tags;
    }

    @Singular
    private List<QuestionDTO> questions;
}
