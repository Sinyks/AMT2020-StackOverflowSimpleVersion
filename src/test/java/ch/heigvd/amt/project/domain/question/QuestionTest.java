package ch.heigvd.amt.project.domain.question;

import ch.heigvd.amt.project.domain.user.UserId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {

    static Question questionTest;
    static QuestionId questionId;
    static Date creationDate;
    static Date lastEditDate;
    static UserId ownerId;
    static String title;
    static String body;

    @BeforeAll
    static void setBeforeAll() {
        questionId = new QuestionId();
        creationDate = new Date(1483225200);// January 2017
        lastEditDate = new Date(1498860000);// July 2017
        ownerId = new UserId();
        title = "exiting with vim";
        body = "How do I exit vim? I'm stuck in the terminal since this morning." +
                " My computer is an Apple 2, gtx3080 I9 128 GB RAM";
    }


    @BeforeEach
    void setBeforeEach() {
        questionTest = null;
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void buildFullQuestionAndDeepCloneTest(boolean isDeepCloneTest) {
        questionTest = Question.builder()
                .id(questionId)
                .creationDate(creationDate)
                .lastEditDate(lastEditDate)
                .ownerId(ownerId)
                .title(title)
                .body(body)
                .build();

        if (isDeepCloneTest) {
            questionTest = questionTest.deepClone();
        }

        assertNotNull(questionTest);
        assertEquals(questionId, questionTest.getId());
        assertEquals(creationDate, questionTest.getCreationDate());
        assertEquals(lastEditDate, questionTest.getLastEditDate());
        assertEquals(ownerId, questionTest.getOwnerId());
        assertEquals(title, questionTest.getTitle());
        assertEquals(body, questionTest.getBody());
    }

    @Test
    void buildMinimalQuestionTest() {
        questionTest = Question.builder()
                .ownerId(ownerId)
                .title(title)
                .body(body)
                .build();

        assertNotNull(questionTest);
        assertNotNull(questionTest.getId());
        assertNotNull(questionTest.getCreationDate());
        assertEquals(questionTest.getCreationDate(), questionTest.getLastEditDate());
    }

    @Test
    void missingMandatoryOwnerIdTest() {
        try {
            questionTest = Question.builder()
                    .title(title)
                    .body(body)
                    .build();
            fail("did not throw expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void missingMandatoryTitleTest() {
        try {
            questionTest = Question.builder()
                    .ownerId(ownerId)
                    .body(body)
                    .build();
            fail("did not throw expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void missingMandatoryBodyTest() {
        try {
            questionTest = Question.builder()
                    .ownerId(ownerId)
                    .title(title)
                    .build();
            fail("did not throw expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

}
