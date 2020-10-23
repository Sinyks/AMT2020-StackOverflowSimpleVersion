package ch.heigvd.amt.project.domain.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    static User userTest;
    static UserId userId;
    static String username;
    static String email;
    static String aboutMe;
    static String clearTextPassword;
    static String newClearTextPassword;
    static String preHashedPassword;

    @BeforeAll
    static void setForBeforeAll(){
        userId = new UserId();
        username = "jean";
        email = "test@test.te";
        aboutMe = "I'm a test user";
        clearTextPassword = "p4ssw0rd";
        newClearTextPassword = "&WrWQbYUt8L#)Pc5";
        preHashedPassword = BCrypt.hashpw(clearTextPassword,BCrypt.gensalt());
    }

    @BeforeEach
    void setForBeforeEach(){
        userTest = null;
    }


    @Test
    void buildFullUserTest(){
            userTest = User.builder()
                    .id(userId)
                    .email(email)
                    .username(username)
                    .aboutMe(aboutMe)
                    .clearTextPassword(clearTextPassword)
                    .build();

        assertNotNull(userTest);
        assertEquals(userId, userTest.getId());
        assertEquals(email, userTest.getEmail());
        assertEquals(username, userTest.getUsername());
        assertEquals(aboutMe, userTest.getAboutMe());
        assertTrue(BCrypt.checkpw(clearTextPassword, userTest.getHashedPassword()));

    }

    @Test
    void buildMinimalUserTest(){
        userTest = User.builder()
                .username(username)
                .email(email)
                .clearTextPassword(clearTextPassword)
                .build();

        assertNotNull(userTest);
        assertNotNull(userTest.getId());
        assertEquals("no informations", userTest.getAboutMe());
    }

    @Test
    void buildWithHashedPasswordTest(){

        userTest = User.builder()
                .email(email)
                .username(username)
                .hashedPassword(preHashedPassword)
                .build();

        assertNotNull(userTest);
        assertEquals(email, userTest.getEmail());
        assertEquals(username, userTest.getUsername());
        assertEquals(preHashedPassword, userTest.getHashedPassword());
    }

    @Test
    void missingMandatoryUsernameTest() {
        try {
            userTest = User.builder()
                    .email(email)
                    .clearTextPassword(clearTextPassword)
                    .build();
            fail("did not throw expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void missingMandatoryEmailTest() {
        try{
            userTest = User.builder()
                    .username(username)
                    .clearTextPassword(clearTextPassword)
                    .build();
            fail("did not throw expected exception");
        } catch(final IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    void missingMandatoryClearTextPasswordTest() {

        try{
            userTest = User.builder()
                    .email(email)
                    .username(username)
                    .build();
            fail("did not throw expected exception");
        } catch(final IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    void loginTest(){
        userTest = User.builder()
                .username(username)
                .email(email)
                .clearTextPassword(clearTextPassword)
                .build();

        assertTrue(userTest.login(clearTextPassword));
    }

    @Test
    void passwordUpdateTest(){
        userTest = User.builder()
                .username(username)
                .email(email)
                .clearTextPassword(clearTextPassword)
                .build();

        if(!userTest.updatePassword(clearTextPassword,newClearTextPassword)){
            fail("clearTextPassword is not the old password");
        }
        assertTrue(BCrypt.checkpw(newClearTextPassword, userTest.getHashedPassword()));
    }

    @Test
    void deepCloneTest(){
        userTest = User.builder()
                .id(userId)
                .email(email)
                .username(username)
                .aboutMe(aboutMe)
                .hashedPassword(preHashedPassword)
                .build();
        userTest = userTest.deepClone();

        assertNotNull(userTest);
        assertEquals(userId, userTest.getId());
        assertEquals(email, userTest.getEmail());
        assertEquals(username, userTest.getUsername());
        assertEquals(aboutMe, userTest.getAboutMe());
        assertEquals(preHashedPassword, userTest.getHashedPassword());
    }



}