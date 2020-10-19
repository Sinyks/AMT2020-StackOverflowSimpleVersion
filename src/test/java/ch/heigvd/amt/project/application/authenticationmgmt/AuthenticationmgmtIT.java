package ch.heigvd.amt.project.application.authenticationmgmt;

import ch.heigvd.amt.project.application.ServiceRegistry;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class AuthenticationmgmtIT {

    private final static String WARNAME = "arquillian-managed.war";

    @Inject
    ServiceRegistry serviceRegistry;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class,WARNAME)
                .addPackages(true, "ch.heigvd.amt");
    }

    @Test
    public void registerUserMustReturnValidObject() {
        AuthenticationManagementFacade amf = serviceRegistry.getAuthenticationManagementFacade();

        assertNotNull(amf);
    }
}
