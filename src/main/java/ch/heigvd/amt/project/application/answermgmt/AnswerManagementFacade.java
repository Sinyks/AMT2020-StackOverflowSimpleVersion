package ch.heigvd.amt.project.application.answermgmt;

import ch.heigvd.amt.project.application.answermgmt.answer.AnswerCommand;
import ch.heigvd.amt.project.application.answermgmt.answer.AnswerFailedException;
import ch.heigvd.amt.project.application.commentmgmt.CommentManagementFacade;
import ch.heigvd.amt.project.domain.answer.Answer;
import ch.heigvd.amt.project.domain.answer.IAnswerRepository;
import ch.heigvd.amt.project.domain.comment.Comment;
import ch.heigvd.amt.project.domain.comment.ICommentRepository;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.IUserRepository;
import ch.heigvd.amt.project.domain.user.User;
import ch.heigvd.amt.project.domain.user.UserId;
import lombok.Builder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerManagementFacade {
    private IAnswerRepository answerRepository;
    private IUserRepository userRepository;

    private CommentManagementFacade commentManagementFacade;


    public AnswerManagementFacade(IAnswerRepository answerRepository, IUserRepository userRepository, CommentManagementFacade commentManagementFacade) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.commentManagementFacade = commentManagementFacade;
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
                .comments(
                        commentManagementFacade.getComments(answer.getId())
                )
                .build()).collect(Collectors.toList());

        return AnswersDTO.builder()
                .answers(allAnswersDTO)
                .build();
    }

    public void answer(AnswerCommand command) throws AnswerFailedException {
        Answer newAnswer = Answer.builder()
                .body(command.getBody())
                .ownerId(command.getOwnerID())
                .questionId(command.getQuestionId())
                .build();

        answerRepository.save(newAnswer);
    }
}
