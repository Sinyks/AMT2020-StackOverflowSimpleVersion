package ch.heigvd.amt.project.application.core.app.servicies.authenticationmgmt.questionmgmt;

import ch.heigvd.amt.project.application.core.app.servicies.authenticationmgmt.questionmgmt.ask.*;
import ch.heigvd.amt.project.application.core.domain.exceptions.PersistenceException;
import ch.heigvd.amt.project.application.core.domain.question.IQuestionRepository;
import ch.heigvd.amt.project.application.core.domain.question.Question;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Collection;

public class QuestionManagementFacade {

    private IQuestionRepository questionRepository;

    public QuestionManagementFacade(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    //change this to void and make the QuestionsDTO so that we can display them all at the same time
    public void ask (AskCommand command) throws AskFailedException, PersistenceException {
        Question newQuestion = Question.builder()
                .label(command.getLabel())
                .content(command.getContent())
                .build();
        questionRepository.create(newQuestion);
    }

    public QuestionsDTO getQuestions(QuestionsQuery query) {
        Collection<Question> allQuestions = questionRepository.find(query);

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO = allQuestions.stream().map(question -> QuestionsDTO.QuestionDTO.builder()
                .content(question.getContent())
                .label(question.getLabel())
                .build()).collect(Collectors.toList());

        return QuestionsDTO.builder()
                .questions(allQuestionsDTO)
                .build();
    }

}
