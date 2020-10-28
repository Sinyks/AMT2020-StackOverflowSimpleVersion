package ch.heigvd.amt.project.application.commentmgmt;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsManagementFacade;
import ch.heigvd.amt.project.application.testUtil.testUtils;
import ch.heigvd.amt.project.domain.comment.Comment;
import ch.heigvd.amt.project.domain.user.User;
import org.checkerframework.checker.units.qual.C;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;

import static org.junit.Assert.fail;

@RunWith(Arquillian.class)
public class commentmgmtFacadeIT {

    private final static String WARNAME = "arquillian-managed.war";

    @Inject
    ServiceRegistry serviceRegistry;

    private CommentManagementFacade commentManagementFacade;

    private final static String USERNAME = "test";
    private final static String PASSWORD = "test";
    private final static String COMMENT_BODY = "just for testing";

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class,WARNAME)
                .addPackages(true, "ch.heigvd.amt")
                .addClass(BCrypt.class);
    }

    @Before
    public void init(){
        commentManagementFacade = serviceRegistry.getCommentManagementFacade();

    }

    @Test
    public void commentAnswer(){
        fail("not Implemented");
    }



}
