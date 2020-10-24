package ch.heigvd.amt.project.domain.comment;

import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.UserId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommentTest {

    static Comment commentTest;

    static CommentId commentId;
    static UserId ownerId;
    static AnswerId answerId;
    static QuestionId questionId;
    static Date creationDate;
    static Date lastEditDate;
    static String body;

    @BeforeAll
    static void setForBeforeAll(){
        commentId = new CommentId();
        ownerId = new UserId(); // Richard Stallman
        answerId = new AnswerId();
        questionId = new QuestionId();
        creationDate = new Date(1483225200);// January 2017
        lastEditDate = new Date(1498860000);// July 2017
        body = " I'd just like to interject for a moment. " +
                "What you're refering to as Linux, is in fact, " +
                "GNU/Linux, or as I've recently taken to calling " +
                "it, GNU plus Linux. Linux is not an operating " +
                "system unto itself, but rather another free " +
                "component of a fully functioning GNU system made" +
                " useful by the GNU corelibs, shell utilities and" +
                " vital system components comprising a full OS as" +
                " defined by POSIX.";
    }
    @BeforeEach
    void setForBeforeEach(){
        commentTest=null;
    }



    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void buildFullCommentTest(int passNumber){
        commentTest= Comment.builder()
                .id(commentId)
                .ownerId(ownerId)
                .questionId((passNumber%2==0)?questionId:null)
                .answerId((passNumber%2==0)?null:answerId)
                .creationDate(creationDate)
                .lastEditDate(lastEditDate)
                .body(body)
                .build();

        if(passNumber/2==1){
            commentTest=commentTest.deepClone();
        }

        assertNotNull(commentTest);
        assertEquals(commentId, commentTest.getId());
        assertEquals(ownerId,commentTest.getOwnerId());
        if(passNumber%2==0){
            assertEquals(questionId,commentTest.getQuestionId());
        } else {
            assertEquals(answerId,commentTest.getAnswerId());
        }
        assertEquals(creationDate, commentTest.getCreationDate());
        assertEquals(lastEditDate, commentTest.getLastEditDate());
        assertEquals(body, commentTest.getBody());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void buildMinimalCommentTest(boolean isQuestion){
        commentTest= Comment.builder()
                .ownerId(ownerId)
                .questionId(isQuestion?questionId:null)
                .answerId(isQuestion?null:answerId)
                .body(body)
                .build();

        assertNotNull(commentTest);
        assertNotNull(commentTest.getId());
        assertNotNull(commentTest.getCreationDate());
        assertEquals(commentTest.getCreationDate(), commentTest.getLastEditDate());
    }


    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void missingMandatoryOwnerIdTest(boolean isQuestion){
        try{
            commentTest= Comment.builder()
                    .questionId(isQuestion?questionId:null)
                    .answerId(isQuestion?null:answerId)
                    .body(body)
                    .build();
            fail("did not throw expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void missingMandatoryQuestionAndAnswerIdTest(){
        try{
            commentTest= Comment.builder()
                    .ownerId(ownerId)
                    .body(body)
                    .build();
            fail("did not throw expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void missingMandatoryBodyTest(boolean isQuestion){
        try{
            commentTest= Comment.builder()
                    .ownerId(ownerId)
                    .questionId(isQuestion?questionId:null)
                    .answerId(isQuestion?null:answerId)
                    .build();
            fail("did not throw expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void answerIdAndQuestionIdPresentTest(){
        try{
            commentTest= Comment.builder()
                    .ownerId(ownerId)
                    .questionId(questionId)
                    .answerId(answerId)
                    .body(body)
                    .build();
            fail("did not throw expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void invalidObjectWithSetterTest(){
        commentTest= Comment.builder()
                .ownerId(ownerId)
                .questionId(questionId)
                .body(body)
                .build();

        commentTest.setOwnerId(null);
        assertNotEquals(null,commentTest.getOwnerId());
    }


}
