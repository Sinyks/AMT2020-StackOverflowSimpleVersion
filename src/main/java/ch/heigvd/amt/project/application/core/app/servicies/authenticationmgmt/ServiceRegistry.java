package ch.heigvd.amt.project.application.core.app.servicies.authenticationmgmt;

import ch.heigvd.amt.project.application.core.app.servicies.authenticationmgmt.AuthenticationManagementFacade;
import ch.heigvd.amt.project.application.core.app.servicies.authenticationmgmt.questionmgmt.QuestionManagementFacade;
import ch.heigvd.amt.project.application.core.domain.question.IQuestionRepository;
import ch.heigvd.amt.project.application.core.domain.user.IUserRepository;
import ch.heigvd.amt.project.infrastructure.persistence.memory.InMemoryUserRepository;
import ch.heigvd.amt.project.infrastructure.persistence.memory.InMemoryQuestionRepository;

public class ServiceRegistry {

    private static final ServiceRegistry SERVICE_REGISTRY = new ServiceRegistry();

    private static final IUserRepository I_PERSON_REPOSITORY = new InMemoryUserRepository();
    private static final IQuestionRepository I_QUESTION_REPOSITORY = new InMemoryQuestionRepository();

    private static final AuthenticationManagementFacade AUTHENTICATION_MANAGEMENT_FACADE = new AuthenticationManagementFacade(I_PERSON_REPOSITORY);
    private static final QuestionManagementFacade QUESTION_MANAGEMENT_FACADE = new QuestionManagementFacade(I_QUESTION_REPOSITORY);

    public static ServiceRegistry getServiceRegistry(){
        return SERVICE_REGISTRY;
    }

    public static AuthenticationManagementFacade getAuthenticationManagementFacade(){
        return AUTHENTICATION_MANAGEMENT_FACADE;
    }

    public static QuestionManagementFacade getQuestionManagementFacade(){
        return QUESTION_MANAGEMENT_FACADE;
    }
}
