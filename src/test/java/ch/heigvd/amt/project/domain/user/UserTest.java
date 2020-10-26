package ch.heigvd.amt.project.domain.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
    static String newEmail;
    static String newUsername;
    static String newAboutMe;

    @BeforeAll
    static void setBeforeAll() {
        userId = new UserId();
        username = "jean";
        email = "test@test.te";
        aboutMe = "I'm a test user";
        clearTextPassword = "p4ssw0rd";
        newClearTextPassword = "&WrWQbYUt8L#)Pc5";
        preHashedPassword = BCrypt.hashpw(clearTextPassword, BCrypt.gensalt());
        newEmail = "new@new.ne";
        newUsername = "xX_Jean_Xx";
        newAboutMe = "I'm an updated test user";
    }

    @BeforeEach
    void setBeforeEach() {
        userTest = null;
    }


    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void buildFullUserAndDeepCloneTest(boolean isDeepCloneTest) {
        userTest = User.builder()
                .id(userId)
                .email(email)
                .username(username)
                .aboutMe(aboutMe)
                .clearTextPassword(clearTextPassword)
                .build();

        if (isDeepCloneTest) {
            userTest = userTest.deepClone();
        }

        assertNotNull(userTest);
        assertEquals(userId, userTest.getId());
        assertEquals(email, userTest.getEmail());
        assertEquals(username, userTest.getUsername());
        assertEquals(aboutMe, userTest.getAboutMe());
        assertTrue(BCrypt.checkpw(clearTextPassword, userTest.getHashedPassword()));

    }

    @Test
    void buildMinimalUserTest() {
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
    void buildWithHashedPasswordTest() {
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
        try {
            userTest = User.builder()
                    .username(username)
                    .clearTextPassword(clearTextPassword)
                    .build();
            fail("did not throw expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void missingMandatoryClearTextPasswordTest() {
        try {
            userTest = User.builder()
                    .email(email)
                    .username(username)
                    .build();
            fail("did not throw expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void loginTest() {
        userTest = User.builder()
                .username(username)
                .email(email)
                .clearTextPassword(clearTextPassword)
                .build();

        assertTrue(userTest.login(clearTextPassword));
    }

    @Test
    void passwordUpdateTest() {
        userTest = User.builder()
                .username(username)
                .email(email)
                .clearTextPassword(clearTextPassword)
                .build();

        try {
            userTest.updatePassword(newClearTextPassword);
        } catch (final IllegalArgumentException e) {
            fail(e.getMessage());
        }

        assertTrue(BCrypt.checkpw(newClearTextPassword, userTest.getHashedPassword()));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    void updatePersonalInformation(int passNumber) {
        userTest = User.builder()
                .username(username)
                .email(email)
                .aboutMe(aboutMe)
                .clearTextPassword(clearTextPassword)
                .build();

        try {
            userTest.updatePersonalInformations((passNumber / 4 == 1) ? newUsername : null,
                    ((passNumber % 4) / 2 == 1) ? newAboutMe : null,
                    (passNumber % 2 == 1) ? newEmail : null);
        } catch (final IllegalArgumentException e) {
            if (passNumber == 0) {
                assertTrue(true);
            } else {
                fail(e.getMessage());
            }
        }

        assertEquals((passNumber / 4 == 1) ? newUsername : username,
                userTest.getUsername());
        assertEquals(((passNumber % 4) / 2 == 1) ? newAboutMe : aboutMe,
                userTest.getAboutMe());
        assertEquals((passNumber % 2 == 1) ? newEmail : email,
                userTest.getEmail());
    }


}