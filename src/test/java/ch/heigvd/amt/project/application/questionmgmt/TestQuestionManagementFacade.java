package ch.heigvd.amt.project.application.questionmgmt;

import ch.heigvd.amt.project.application.questionmgmt.ask.AskCommand;
import ch.heigvd.amt.project.application.questionmgmt.ask.AskFailedException;
import ch.heigvd.amt.project.domain.question.Question;
import ch.heigvd.amt.project.infrastructure.persistence.InMemoryQuestionRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class TestQuestionManagementFacade {

    private final String labelQuestion = "ToBeOrNotToBe";
    private final String contentQuestion = "... Not to Be";

    private final QuestionManagementFacade qmf = new QuestionManagementFacade(new InMemoryQuestionRepository());

    @Test
    public void askQuestionMustNotThrowErrorIfCommandIsCorrect(){
        try {
            qmf.ask(AskCommand.builder().label(labelQuestion).content(contentQuestion).build());
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
