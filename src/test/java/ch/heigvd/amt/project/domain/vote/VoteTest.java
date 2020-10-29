package ch.heigvd.amt.project.domain.vote;

import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.comment.Comment;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.UserId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class VoteTest {

    static Vote voteTest;

    static VoteId voteId;
    static QuestionId questionId;
    static AnswerId answerId;
    static UserId ownerId;
    static boolean isUpVote;

    @BeforeAll
    static void setBeforeAll() {
        voteId = new VoteId();
        questionId = new QuestionId();
        answerId = new AnswerId();
        ownerId = new UserId();
        isUpVote = false;
    }

    @BeforeAll
    static void setBeforeEach() {
        voteTest = null;
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void buildFullVoteAndDeepCloneTest(int passNumber) {
        voteTest = Vote.builder()
                .id(voteId)
                .questionId((passNumber % 2 == 0) ? questionId : null)
                .answerId((passNumber % 2 == 0) ? null : answerId)
                .ownerId(ownerId)
                .isUpVote(isUpVote)
                .build();

        if (passNumber / 2 == 1) { // pass 2 and 3 test the deepClone
            voteTest = voteTest.deepClone();
        }

        assertNotNull(voteTest);
        assertEquals(voteId, voteTest.getId());
        assertEquals(ownerId, voteTest.getOwnerId());
        if (passNumber % 2 == 0) {
            assertEquals(questionId, voteTest.getQuestionId());
        } else {
            assertEquals(answerId, voteTest.getAnswerId());
        }
        assertFalse(voteTest.isUpVote());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void buildMinimalVoteTest(boolean isQuestion) {
        voteTest = Vote.builder()
                .ownerId(ownerId)
                .questionId(isQuestion ? questionId : null)
                .answerId(isQuestion ? null : answerId)
                .build();

        assertNotNull(voteTest);
        assertNotNull(voteTest.getId());
        assertFalse(voteTest.isUpVote());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void missingMandatoryOwnerIdTest(boolean isQuestion) {
        try {
            voteTest = Vote.builder()
                    .questionId(isQuestion ? questionId : null)
                    .answerId(isQuestion ? null : answerId)
                    .build();
            fail("did not throw expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void missingMandatoryQuestionAndAnswerIdTest() {
        try {
            voteTest = Vote.builder()
                    .ownerId(ownerId)
                    .build();
            fail("did not throw expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void answerIdAndQuestionIdPresentTest() {
        try {
            voteTest = Vote.builder()
                    .ownerId(ownerId)
                    .questionId(questionId)
                    .answerId(answerId)
                    .build();
            fail("did not throw expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void invertVoteTest(boolean isUpVote) {
        voteTest = Vote.builder()
                .ownerId(ownerId)
                .questionId(questionId)
                .isUpVote(isUpVote)
                .build();
        voteTest.invertVote();
        assertEquals(voteTest.isUpVote(), !isUpVote);
    }

}
