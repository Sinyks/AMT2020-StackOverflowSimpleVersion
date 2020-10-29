package ch.heigvd.amt.project.application;

import ch.heigvd.amt.project.application.answermgmt.AnswerManagementFacade;
import ch.heigvd.amt.project.application.authenticationmgmt.AuthenticationManagementFacade;
import ch.heigvd.amt.project.application.commentmgmt.CommentManagementFacade;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsManagementFacade;
import ch.heigvd.amt.project.application.votemgmt.VoteManagementFacade;
import ch.heigvd.amt.project.domain.answer.IAnswerRepository;
import ch.heigvd.amt.project.domain.comment.ICommentRepository;
import ch.heigvd.amt.project.domain.user.IUserRepository;
import ch.heigvd.amt.project.domain.question.IQuestionRepository;
import ch.heigvd.amt.project.domain.vote.IVoteRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class ServiceRegistry {

    //Injecting the repositories
    @Inject @Named("PgsqlUserRepository")
    IUserRepository userRepository;

    @Inject @Named("PgsqlQuestionRepository")
    IQuestionRepository questionRepository;

    @Inject @Named("PgsqlAnswerRepository")
    IAnswerRepository answerRepository;

    @Inject @Named("PgsqlCommentRepository")
    ICommentRepository commentRepository;

    @Inject @Named("PgsqlVoteRepository")
    IVoteRepository voteRepository;

    //Management facades
    private static QuestionsManagementFacade questionsManagementFacade;
    private static AuthenticationManagementFacade authenticationManagementFacade;
    private static AnswerManagementFacade answerManagementFacade;
    private static CommentManagementFacade commentManagementFacade;
    private static VoteManagementFacade voteManagementFacade;

    public AuthenticationManagementFacade getAuthenticationManagementFacade(){
        return authenticationManagementFacade;
    }

    public QuestionsManagementFacade getQuestionManagementFacade(){
        return questionsManagementFacade;
    }

    public AnswerManagementFacade getAnswerManagementFacade(){ return answerManagementFacade;}

    public CommentManagementFacade getCommentManagementFacade(){ return commentManagementFacade;}

    public VoteManagementFacade getVoteManagementFacade(){ return voteManagementFacade;}

    public ServiceRegistry(){}

    @PostConstruct
    private void initInjection(){
        authenticationManagementFacade = new AuthenticationManagementFacade(userRepository);
        voteManagementFacade = new VoteManagementFacade(voteRepository, commentRepository, answerRepository);
        commentManagementFacade = new CommentManagementFacade(commentRepository, answerRepository, userRepository);
        answerManagementFacade = new AnswerManagementFacade(answerRepository, userRepository, commentManagementFacade);
        questionsManagementFacade = new QuestionsManagementFacade(questionRepository, userRepository, answerManagementFacade,commentManagementFacade, voteManagementFacade);
    }

}
