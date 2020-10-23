package ch.heigvd.amt.project.application;

import ch.heigvd.amt.project.application.answermgmt.AnswerManagementFacade;
import ch.heigvd.amt.project.application.authenticationmgmt.AuthenticationManagementFacade;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsManagementFacade;
import ch.heigvd.amt.project.domain.answer.IAnswerRepository;
import ch.heigvd.amt.project.domain.user.IUserRepository;
import ch.heigvd.amt.project.domain.question.IQuestionRepository;
import ch.heigvd.amt.project.infrastructure.persistence.inMemory.InMemoryUserRepository;
import ch.heigvd.amt.project.infrastructure.persistence.inMemory.InMemoryQuestionRepository;
import ch.heigvd.amt.project.infrastructure.persistence.jdbc.pgsql.PgsqlUserRepository;

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

    //Management facades
    private static QuestionsManagementFacade questionsManagementFacade;
    private static AuthenticationManagementFacade authenticationManagementFacade;
    private static AnswerManagementFacade answerManagementFacade;

    public AuthenticationManagementFacade getAuthenticationManagementFacade(){
        return authenticationManagementFacade;
    }

    public QuestionsManagementFacade getQuestionManagementFacade(){
        return questionsManagementFacade;
    }

    public AnswerManagementFacade getAnswerManagementFacade(){ return answerManagementFacade;}

    public ServiceRegistry(){}

    @PostConstruct
    private void initInjection(){
        authenticationManagementFacade = new AuthenticationManagementFacade(userRepository);
        answerManagementFacade = new AnswerManagementFacade(answerRepository, userRepository);
        questionsManagementFacade = new QuestionsManagementFacade(questionRepository, userRepository, answerManagementFacade);

    }

}
