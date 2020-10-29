package ch.heigvd.amt.project.application.questionmgmt;

import ch.heigvd.amt.project.application.answermgmt.AnswerManagementFacade;
import ch.heigvd.amt.project.application.commentmgmt.CommentManagementFacade;
import ch.heigvd.amt.project.application.questionmgmt.ask.*;
import ch.heigvd.amt.project.application.votemgmt.VoteManagementFacade;
import ch.heigvd.amt.project.application.votemgmt.VotesDTO;
import ch.heigvd.amt.project.domain.question.IQuestionRepository;
import ch.heigvd.amt.project.domain.question.Question;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.IUserRepository;
import ch.heigvd.amt.project.domain.user.User;
import ch.heigvd.amt.project.domain.user.UserId;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Collection;

public class QuestionsManagementFacade {

    private IQuestionRepository questionRepository;
    private IUserRepository userRepository;

    private AnswerManagementFacade answerManagementFacade;
    private CommentManagementFacade commentManagementFacade;
    private VoteManagementFacade voteManagementFacade;

    public QuestionsManagementFacade(IQuestionRepository questionRepository,
                                     IUserRepository userRepository,
                                     AnswerManagementFacade answerManagementFacade,
                                     CommentManagementFacade commentManagementFacade,
                                     VoteManagementFacade voteManagementFacade)
    {

        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.answerManagementFacade = answerManagementFacade;
        this.commentManagementFacade = commentManagementFacade;
        this.voteManagementFacade = voteManagementFacade;
    }

    public void ask (AskCommand command) throws AskFailedException {
        Question newQuestion = Question.builder()
                .ownerId(command.getOwnerId())
                .title(command.getTitle())
                .body(command.getBody())
                /*.tags(command.getTags())*/
                .build();
        questionRepository.save(newQuestion);
    }

    private String getUserNameById(UserId id){
        User user = userRepository.findById(id).orElse(null);

        if(user != null){
            return user.getUsername();
        }else {
            throw new NullPointerException("No user with this id found");
        }
    }

    public QuestionsDTO.QuestionDTO getQuestion(QuestionId id){
        Question question = questionRepository.findById(id).orElse(null);

        if(question == null){
            throw new NullPointerException("No question exists with this id.");
        }

        VotesDTO votesDTO = voteManagementFacade.getVotes(question.getId());

        int voteTotal = 0;

        for (VotesDTO.VoteDTO vote : votesDTO.getVotes()) {
            if(vote.isUpVote()){
                voteTotal++;
            }else{
                voteTotal--;
            }
        }

        return QuestionsDTO.QuestionDTO.builder()
                .id(question.getId().getId())
                .creationDate(question.getCreationDate())
                .lastEditDate(question.getLastEditDate())
                .ownerId(question.getOwnerId())
                .ownerName(getUserNameById(question.getOwnerId()))
                .body(question.getBody())
                .title(question.getTitle())
                .answers(
                        answerManagementFacade.getAnswers(question.getId())
                )
                .comments(
                        commentManagementFacade.getComments(question.getId())
                )
                .votes(
                        votesDTO
                )
                .voteTotal(
                        voteTotal
                )
                //.tags(question.getTags())*/
                .build();
    }

    public QuestionsDTO getQuestions(QuestionsQuery query) {
        Collection<Question> allQuestions = questionRepository.find(query);

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO = allQuestions.stream().map(question -> QuestionsDTO.QuestionDTO.builder()
                .id(question.getId().getId())
                .creationDate(question.getCreationDate())
                .lastEditDate(question.getLastEditDate())
                .ownerId(question.getOwnerId())
                .ownerName(getUserNameById(question.getOwnerId()))
                .body(question.getBody())
                .title(question.getTitle())
                /*.voteTotal(question.getVoteTotal())
                .tags(question.getTags())*/
                .build()).collect(Collectors.toList());

        return QuestionsDTO.builder()
                .questions(allQuestionsDTO)
                .build();
    }

}
