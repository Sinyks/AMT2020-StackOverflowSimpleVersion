package ch.heigvd.amt.project.application;

import ch.heigvd.amt.project.application.authenticationmgmt.AuthenticationManagementFacade;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsManagementFacade;
import ch.heigvd.amt.project.domain.user.IUserRepository;
import ch.heigvd.amt.project.domain.question.IQuestionRepository;
import ch.heigvd.amt.project.infrastructure.persistence.inMemory.InMemoryUserRepository;
import ch.heigvd.amt.project.infrastructure.persistence.inMemory.InMemoryQuestionRepository;
import ch.heigvd.amt.project.infrastructure.persistence.jdbc.pgsql.PgsqlUserRepository;

public class ServiceRegistry {

    private static final ServiceRegistry SERVICE_REGISTRY = new ServiceRegistry();

    private static final IUserRepository I_USER_REPOSITORY = new PgsqlUserRepository();
    private static final IQuestionRepository I_QUESTION_REPOSITORY = new InMemoryQuestionRepository();

    private static final AuthenticationManagementFacade AUTHENTICATION_MANAGEMENT_FACADE = new AuthenticationManagementFacade(I_USER_REPOSITORY);
    private static final QuestionsManagementFacade QUESTION_MANAGEMENT_FACADE = new QuestionsManagementFacade(I_QUESTION_REPOSITORY);

    public static ServiceRegistry getServiceRegistry(){
        return SERVICE_REGISTRY;
    }

    public static AuthenticationManagementFacade getAuthenticationManagementFacade(){
        return AUTHENTICATION_MANAGEMENT_FACADE;
    }

    public static QuestionsManagementFacade getQuestionManagementFacade(){
        return QUESTION_MANAGEMENT_FACADE;
    }
}
