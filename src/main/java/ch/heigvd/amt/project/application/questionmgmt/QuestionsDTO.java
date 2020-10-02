package ch.heigvd.amt.project.application.questionmgmt;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Getter
@Builder
public class QuestionsDTO {

    @Builder
    @Getter
    public static class QuestionDTO {
        private String label;
        private String content;
    }

    @Singular
    private List<QuestionDTO> questions;
}
