package ch.heigvd.amt.project.application.answermgmt;

import ch.heigvd.amt.project.domain.answer.Answer;
import ch.heigvd.amt.project.domain.answer.IAnswerRepository;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.IUserRepository;
import ch.heigvd.amt.project.domain.user.User;
import ch.heigvd.amt.project.domain.user.UserId;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerManagementFacade {
    private IAnswerRepository answerRepository;
    private IUserRepository userRepository;


    public AnswerManagementFacade(IAnswerRepository answerRepository, IUserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
    }

    private String getUserNameById(UserId id){
        User user = userRepository.findById(id).orElse(null);

        if(user != null){
            return user.getUsername();
        }else {
            throw new NullPointerException("No user with this id found");
        }
    }

    public AnswersDTO getAnswers(QuestionId id){
        Collection<Answer> allAnswers = answerRepository.findByQuestionID(id);

        List<AnswersDTO.AnswerDTO> allAnswersDTO = allAnswers.stream().map(answer -> AnswersDTO.AnswerDTO.builder()
                .id(answer.getId().getId())
                .questionID(answer.getQuestionId().getId())
                .creationDate(answer.getCreationDate())
                .lastEditDate(answer.getLastEditDate())
                .ownerId(answer.getOwnerId())
                .ownerName(getUserNameById(answer.getOwnerId()))
                .body(answer.getBody())
                .build()).collect(Collectors.toList());

        return AnswersDTO.builder()
                .answers(allAnswersDTO)
                .build();
    }
}
