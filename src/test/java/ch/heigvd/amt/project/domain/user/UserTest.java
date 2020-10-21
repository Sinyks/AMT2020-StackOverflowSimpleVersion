package ch.heigvd.amt.project.domain.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    static User testUser;
    static UserId userId;
    static String username;
    static String email;
    static String aboutMe;
    static String clearTextPassword;
    static String newClearTextPassword;

    @BeforeAll
    static void setForBeforeAll(){
        userId = new UserId();
        username = "jean";
        email = "test@test.te";
        aboutMe = "I'm a test user";
        clearTextPassword = "p4ssw0rd";
        newClearTextPassword = "&WrWQbYUt8L#)Pc5";
    }

    @BeforeEach
    void setForBeforeEach(){
        testUser = null;
    }


    @Test
    void buildFullUserTest(){
            testUser = User.builder()
                    .id(userId)
                    .email(email)
                    .username(username)
                    .aboutMe(aboutMe)
                    .clearTextPassword(clearTextPassword)
                    .build();

        assertNotNull(testUser);
        assertEquals(userId,testUser.getId());
        assertEquals(email,testUser.getEmail());
        assertEquals(username,testUser.getUsername());
        assertEquals(aboutMe,testUser.getAboutMe());
        assertTrue(BCrypt.checkpw(clearTextPassword,testUser.getHashedPassword()));

    }

    @Test
    void buildMinimalUserTest(){
        testUser = User.builder()
                .username(username)
                .email(email)
                .clearTextPassword(clearTextPassword)
                .build();

        assertNotNull(testUser);
        assertNotNull(testUser.getId());
        assertEquals("no informations",testUser.getAboutMe());
    }

    @Test
    void missingMandatoryUsernameTest() {
        try {
            testUser = User.builder()
                    .email(email)
                    .clearTextPassword(clearTextPassword)
                    .build();
            fail("did not return expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void missingMandatoryEmailTest() {
        try{
            testUser = User.builder()
                    .username(username)
                    .clearTextPassword(clearTextPassword)
                    .build();
            fail("did not return expected exception");
        } catch(final IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    void missingMandatoryClearTextPasswordTest() {

        try{
            testUser = User.builder()
                    .email(email)
                    .username(username)
                    .build();
            fail("did not return expected exception");
        } catch(final IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    void loginTest(){
        testUser = User.builder()
                .username(username)
                .email(email)
                .clearTextPassword(clearTextPassword)
                .build();

        assertTrue(testUser.login(clearTextPassword));
    }

    @Test
    void passwordUpdateTest(){
        testUser = User.builder()
                .username(username)
                .email(email)
                .clearTextPassword(clearTextPassword)
                .build();

        if(!testUser.updatePassword(clearTextPassword,newClearTextPassword)){
            fail("clearTextPassword is not the old password");
        }
        assertTrue(BCrypt.checkpw(newClearTextPassword,testUser.getHashedPassword()));
    }

    @Test
    void deepCloneTest(){
        testUser = User.builder()
                .id(userId)
                .email(email)
                .username(username)
                .aboutMe(aboutMe)
                .clearTextPassword(clearTextPassword)
                .build();
        testUser = testUser.deepClone();

        assertNotNull(testUser);
        assertEquals(userId,testUser.getId());
        assertEquals(email,testUser.getEmail());
        assertEquals(username,testUser.getUsername());
        assertEquals(aboutMe,testUser.getAboutMe());
        assertNotNull(testUser.getHashedPassword());
    }

}