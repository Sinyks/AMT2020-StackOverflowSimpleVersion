package ch.heigvd.amt.project.application.questionmgmt;

import ch.heigvd.amt.project.application.questionmgmt.ask.AskCommand;
import ch.heigvd.amt.project.application.questionmgmt.ask.AskFailedException;
import ch.heigvd.amt.project.infrastructure.persistence.InMemoryQuestionRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TestQuestionManagementFacade {

    private final QuestionManagementFacade qmf = new QuestionManagementFacade(new InMemoryQuestionRepository());

    @Test
    public void askQuestionMustThrowErrorIf(){
        try {
            qmf.ask(AskCommand.builder().build());
        } catch (AskFailedException e) {
            e.printStackTrace();
            fail();
        }
    }

}
