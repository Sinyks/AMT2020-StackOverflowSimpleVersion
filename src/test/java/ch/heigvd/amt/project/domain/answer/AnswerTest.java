package ch.heigvd.amt.project.domain.answer;

import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.UserId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AnswerTest {
    static Answer answerTest;

    static AnswerId answerId;
    static QuestionId questionId;
    static UserId ownerId;
    static Date creationDate;
    static Date lastEditDate;
    static String body;

    @BeforeAll
    static void setForBeforeAll() {
        answerId = new AnswerId();
        questionId = new QuestionId();
        ownerId = new UserId();
        creationDate = new Date(1483225200);// January 2017
        lastEditDate = new Date(1498860000);// July 2017
        body = "you have to enter `:wq` to exit vi/vim";
    }

    @BeforeEach
    void serForBeforeEach() {
        answerTest = null;
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void buildFullAnswerAndDeepCloneTest(boolean isDeepCloneTest) {
        answerTest = Answer.builder()
                .id(answerId)
                .creationDate(creationDate)
                .lastEditDate(lastEditDate)
                .ownerId(ownerId)
                .questionId(questionId)
                .body(body)
                .build();

        if (isDeepCloneTest) {
            answerTest = answerTest.deepClone();
        }

        assertNotNull(answerTest);
        assertEquals(answerId, answerTest.getId());
        assertEquals(creationDate, answerTest.getCreationDate());
        assertEquals(lastEditDate, answerTest.getLastEditDate());
        assertEquals(ownerId, answerTest.getOwnerId());
        assertEquals(questionId, answerTest.getQuestionId());
        assertEquals(body, answerTest.getBody());

    }

    @Test
    void buildMinimalAnswerTest() {
        answerTest = Answer.builder()
                .ownerId(ownerId)
                .questionId(questionId)
                .body(body)
                .build();

        assertNotNull(answerTest);
        assertNotNull(answerTest.getId());
        assertNotNull(answerTest.getCreationDate());
        assertEquals(answerTest.getCreationDate(), answerTest.getLastEditDate());
    }

    @Test
    void missingMandatoryOwnerIdTest() {
        try {
            answerTest = Answer.builder()
                    .questionId(questionId)
                    .body(body)
                    .build();
            fail("did not throw expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void missingMandatoryQuestionIdTest() {
        try {
            answerTest = Answer.builder()
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
            answerTest = Answer.builder()
                    .ownerId(ownerId)
                    .questionId(questionId)
                    .build();
            fail("did not throw expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

}
