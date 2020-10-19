package ch.heigvd.amt.project.domain.user;

import ch.heigvd.amt.project.domain.IEntity;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;
// http://www.mindrot.org/projects/jBCrypt/



@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class User implements IEntity<User, UserId> {

    @Setter(AccessLevel.NONE)
    private UserId id;

    private String username;

    private String email;

    private String aboutMe;

    @EqualsAndHashCode.Exclude
    private String encryptedPassword;

    public boolean login(String clearTextPassword){
        return BCrypt.checkpw(clearTextPassword,encryptedPassword);
    }

    @Override
    public User deepClone() {
        return this.toBuilder()
                .id(new UserId(id.asString())) // shouldn't we also do the other parameter?
                .build();
    }

    public static class UserBuilder {
        public UserBuilder clearTextPassword(String clearTextPassword){
            if(clearTextPassword == null || clearTextPassword.isEmpty()){
                throw new IllegalArgumentException("Password mandatory");
            }

            encryptedPassword = BCrypt.hashpw(clearTextPassword,BCrypt.gensalt());
            return this;
        }

        public User build(){

            if(id == null){
                id =new UserId();
            }
            if(username==null || username.isEmpty()){
                throw new IllegalArgumentException("username mandatory");
            }
            if(email==null || email.isEmpty()){
                throw new IllegalArgumentException("email mandatory");
            }
            if(aboutMe==null || aboutMe.isEmpty()){
                aboutMe="no informations";
            }

            User newUser = new User(id,username,email,aboutMe,encryptedPassword); // this line is for debugger purpose
            return newUser;
        }
    }
}
