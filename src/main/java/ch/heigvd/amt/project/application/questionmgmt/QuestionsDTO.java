package ch.heigvd.amt.project.application.questionmgmt;

import ch.heigvd.amt.project.domain.user.UserId;
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
        private UserId ownerId;
        private String title;
        private String body;
        private Collection<String> tags;
    }

    @Singular
    private List<QuestionDTO> questions;
}
