package ch.heigvd.amt.project.infrastructure.persistence.jdbc.pgsql;

import ch.heigvd.amt.project.domain.answer.IAnswerRepository;
import ch.heigvd.amt.project.domain.user.IUserRepository;
import ch.heigvd.amt.project.domain.user.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import javax.inject.Named;

import static org.junit.Assert.fail;

@RunWith(Arquillian.class)
public class PgsqlAnswerRepositoryIT {
    private final static String WARNAME = "arquillian-managed.war";

    @Inject
    @Named("PgsqlAnswerRepository")
    IAnswerRepository answerRepository;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class,WARNAME)
                .addPackages(true, "ch.heigvd.amt").addClass(BCrypt.class);
    }

    @Test
    public void AnswerRepositorySaveMustAddEntry(){
        fail("Not Implemented");

    }
}
