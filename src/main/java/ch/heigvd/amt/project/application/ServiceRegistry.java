package ch.heigvd.amt.project.application;

import ch.heigvd.amt.project.application.authenticationmgmt.AuthenticationManagementFacade;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsManagementFacade;
import ch.heigvd.amt.project.domain.user.IUserRepository;
import ch.heigvd.amt.project.domain.question.IQuestionRepository;
import ch.heigvd.amt.project.infrastructure.persistence.inMemory.InMemoryUserRepository;
import ch.heigvd.amt.project.infrastructure.persistence.inMemory.InMemoryQuestionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class ServiceRegistry {

    /** Putting this service registry stuff aside for now, we shouldn't need it anymore
    private static final ServiceRegistry SERVICE_REGISTRY = new ServiceRegistry();

    public static ServiceRegistry getServiceRegistry(){
        return SERVICE_REGISTRY;
    }
     */

    //Injecting the repositories, maybe we need to do it for the facades too, but not everything at once
    @Inject @Named("PgsqlUserRepository")
    IUserRepository userRepository;

    @Inject @Named("InMemoryQuestionRepository")
    IQuestionRepository questionRepository;

    /** Creating these facades as before doesn't make sense with our get functions, so we'll put these aside for now :
     *  we're basically creating a facade instance whenever someone makes a call to get it (probably not optimal)
    private static QuestionsManagementFacade questionsManagementFacade;
    private static AuthenticationManagementFacade authenticationManagementFacade;
     */

    public AuthenticationManagementFacade getAuthenticationManagementFacade(){
        return new AuthenticationManagementFacade(userRepository);
    }

    public QuestionsManagementFacade getQuestionManagementFacade(){
        return new QuestionsManagementFacade(questionRepository);
    }

    //this is the old implementation for the service listing

    /**
    private static final IUserRepository I_USER_REPOSITORY = new InMemoryUserRepository();
    private static final IQuestionRepository I_QUESTION_REPOSITORY = new InMemoryQuestionRepository();

    private static final AuthenticationManagementFacade AUTHENTICATION_MANAGEMENT_FACADE = new AuthenticationManagementFacade(I_USER_REPOSITORY);
    private static final QuestionsManagementFacade QUESTION_MANAGEMENT_FACADE = new QuestionsManagementFacade(I_QUESTION_REPOSITORY);
    */

}
