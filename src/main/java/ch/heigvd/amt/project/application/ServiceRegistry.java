package ch.heigvd.amt.project.application;

import ch.heigvd.amt.project.application.authenticationmgmt.AuthenticationManagementFacade;
import ch.heigvd.amt.project.domain.person.IPersonRepository;
import ch.heigvd.amt.project.infrastructure.persistence.InMemoryPersonRepository;

// this was briefly seen in the end of slides 4
// did not understand it so everything will be static final and hope it works with it's calls in servlets
public class ServiceRegistry {

    private static final ServiceRegistry SERVICE_REGISTRY = new ServiceRegistry();
    private static final IPersonRepository I_PERSON_REPOSITORY = new InMemoryPersonRepository();
    private static final AuthenticationManagementFacade AUTHENTICATION_MANAGEMENT_FACADE = new AuthenticationManagementFacade(I_PERSON_REPOSITORY);

    public static ServiceRegistry getServiceRegistry(){
        return SERVICE_REGISTRY;
    }

    public static AuthenticationManagementFacade getAuthenticationManagementFacade(){
        return AUTHENTICATION_MANAGEMENT_FACADE;
    }
}
