package ch.heigvd.amt.project.application.questionmgmt;

import lombok.*;

import java.util.Collection;
import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
public class QuestionsDTO {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class QuestionDTO {
        private String ownerName;
        private String title;
        private String body;
        private Collection<String> tags;
    }

    @Singular
    private List<QuestionDTO> questions;
}
