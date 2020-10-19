/*
package ch.heigvd.amt.project.application.questionmgmt;


import ch.heigvd.amt.project.application.questionmgmt.ask.AskCommand;
import ch.heigvd.amt.project.application.questionmgmt.ask.AskFailedException;
import ch.heigvd.amt.project.infrastructure.persistence.inMemory.InMemoryQuestionRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class TestQuestionManagementFacade {

    private static String titleQuestion;
    private static String ownerNameQuestion;
    private static String bodyQuestion;

    private static QuestionsManagementFacade qmf;

    @Test
    public void askQuestionMustNotThrowErrorIfCommandIsCorrect(){
        try {
            qmf.ask(AskCommand.builder()
                    .title(titleQuestion)
                    .ownerName(ownerNameQuestion)
                    .body(bodyQuestion)
                    .build());
        } catch (AskFailedException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void getQuestionMustReturnNonNullObject(){

        QuestionsDTO questions = qmf.getQuestions(QuestionsQuery.builder().build());

        assertNotNull(questions);

    }
}
*/
