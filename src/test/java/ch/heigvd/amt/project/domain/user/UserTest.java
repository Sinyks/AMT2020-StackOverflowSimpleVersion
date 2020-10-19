package ch.heigvd.amt.project.domain.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    static User goodUser;
    static UserId userId;
    static String username;
    static String email;
    static String aboutMe;
    static String encryptedPassword;
    static String clearTextPassword;

    @BeforeAll
    static void setAllStrings(){
        userId = new UserId();
        username = "jean";
        email = "test@test.te";
        aboutMe = "I'm a test user";
        clearTextPassword = "p4ssw0rd";
        encryptedPassword = BCrypt.hashpw(clearTextPassword,BCrypt.gensalt());
        goodUser = User.builder()
                .id(userId)
                .email(email)
                .username(username)
                .aboutMe(aboutMe)
                .clearTextPassword(clearTextPassword)
                .build();
    }


    @Test
    void buildNotNullTest(){


        assertNotNull(goodUser);
    }





}
