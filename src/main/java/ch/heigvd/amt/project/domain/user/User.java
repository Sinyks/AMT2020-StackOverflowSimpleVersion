package ch.heigvd.amt.project.domain.user;

import ch.heigvd.amt.project.domain.IEntity;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;
// http://www.mindrot.org/projects/jBCrypt/


@Getter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class User implements IEntity<User, UserId> {

    private UserId id;

    private String username;
    private String email;
    private String aboutMe;

    @EqualsAndHashCode.Exclude
    private String hashedPassword;

    public boolean login(String clearTextPassword) {
        return BCrypt.checkpw(clearTextPassword, hashedPassword);
    }


    /**
     * updates the password with the one given in parameter.
     * before calling this, check if the user know his actual password with login, even if already logged in.
     *
     * @param newPassword
     * @throws IllegalArgumentException if newPassword empty or null
     */
    public void updatePassword(String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("Password can't be empty or null");
        }
        this.hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
    }

    /**
     * Update personal information except password
     * if you don't want to update all field, just let them empty or null
     * at least one field must be not empty or not null
     *
     * @param newUsername
     * @param newAboutMe
     * @param newEmail
     * @throws IllegalArgumentException if all three fields are empty or null
     */
    public void updatePersonalInformations(String newUsername, String newAboutMe, String newEmail) {
        if ((newUsername == null || newUsername.isEmpty())
                && (newAboutMe == null || newAboutMe.isEmpty())
                && (newEmail == null || newEmail.isEmpty())) {
            throw new IllegalArgumentException("at least one attribute should be not null or not empty");
        }

        if (newUsername == null || newUsername.isEmpty()) {
            newUsername = this.username;
        }

        if (newAboutMe == null || newAboutMe.isEmpty()) {
            newAboutMe = this.aboutMe;
        }

        if (newEmail == null || newEmail.isEmpty()) {
            newEmail = this.email;
        }

        this.email = newEmail;
        this.aboutMe = newAboutMe;
        this.username = newUsername;
    }

    @Override
    public User deepClone() {
        return this.toBuilder()
                .id(new UserId(id.asString()))
                .build();
    }

    public static class UserBuilder {
        public UserBuilder clearTextPassword(String clearTextPassword) {
            if (clearTextPassword == null || clearTextPassword.isEmpty()) {
                throw new IllegalArgumentException("Password mandatory");
            }

            hashedPassword = BCrypt.hashpw(clearTextPassword, BCrypt.gensalt());
            return this;
        }

        public User build() {

            if (id == null) {
                id = new UserId();
            }
            if (username == null || username.isEmpty()) {
                throw new IllegalArgumentException("username mandatory");
            }
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("email mandatory");
            }
            if (aboutMe == null || aboutMe.isEmpty()) {
                aboutMe = "no informations";
            }

            if (this.hashedPassword == null || this.hashedPassword.isEmpty()) {
                throw new IllegalArgumentException("Password mandatory");
            }

            User newUser = new User(id, username, email, aboutMe, hashedPassword); // this line is for debugging purpose
            return newUser;
        }
    }
}
