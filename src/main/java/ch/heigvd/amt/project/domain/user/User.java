package ch.heigvd.amt.project.domain.user;

import ch.heigvd.amt.project.domain.entity.IEntity;
import lombok.*;

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
        encryptedPassword = clearTextPassword;
        return true; // TODO, real encryption
    }

    @Override
    public User deepClone() {
        return this.toBuilder()
                .id(new UserId(id.asString())) // shouldn't we also do the other paraneter?
                .build();
    }

    public static class UserBuilder {
        public UserBuilder clearTextPassword(String clearTextPassword){
            if(clearTextPassword == null || clearTextPassword.isEmpty()){
                throw new IllegalArgumentException("Password mandatory");
            }

            encryptedPassword = clearTextPassword; // TODO, real encryption
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

            User newUser = new User(id,username,email,aboutMe,encryptedPassword); // this line is for debugger purpose
            return newUser;
        }
    }
}
