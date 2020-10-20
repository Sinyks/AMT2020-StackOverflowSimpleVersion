package ch.heigvd.amt.project.application.questionmgmt;

import ch.heigvd.amt.project.application.questionmgmt.ask.*;
import ch.heigvd.amt.project.domain.question.IQuestionRepository;
import ch.heigvd.amt.project.domain.question.Question;
import ch.heigvd.amt.project.domain.question.QuestionId;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Collection;

public class QuestionsManagementFacade {

    private IQuestionRepository questionRepository;

    public QuestionsManagementFacade(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void ask (AskCommand command) throws AskFailedException {
        Question newQuestion = Question.builder()
                .ownerName(command.getOwnerName())
                .title(command.getTitle())
                .body(command.getBody())
                .tags(command.getTags())
                .build();
        questionRepository.save(newQuestion);
    }

    public QuestionsDTO.QuestionDTO getQuestion(QuestionId id){
        Question question = questionRepository.findById(id).orElse(null);

        return QuestionsDTO.QuestionDTO.builder()
                .id(question.getId())
                .creationDate(question.getCreationDate())
                .lastEditDate(question.getLastEditDate())
                .ownerName(question.getOwnerName())
                .body(question.getBody())
                .title(question.getTitle())
                .voteTotal(question.getVoteTotal())
                .tags(question.getTags())
                .build();
    }

    public QuestionsDTO getQuestions(QuestionsQuery query) {
        Collection<Question> allQuestions = questionRepository.find(query);

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO = allQuestions.stream().map(question -> QuestionsDTO.QuestionDTO.builder()
                .id(question.getId())
                .creationDate(question.getCreationDate())
                .lastEditDate(question.getLastEditDate())
                .ownerName(question.getOwnerName())
                .body(question.getBody())
                .title(question.getTitle())
                .voteTotal(question.getVoteTotal())
                .tags(question.getTags())
                .build()).collect(Collectors.toList());

        return QuestionsDTO.builder()
                .questions(allQuestionsDTO)
                .build();
    }

}
