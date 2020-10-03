package ch.heigvd.amt.project.application.questionmgmt;

import lombok.*;

import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
public class QuestionsDTO {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class QuestionDTO {
        private String label;
        private String content;
    }

    @Singular
    private List<QuestionDTO> questions;
}
