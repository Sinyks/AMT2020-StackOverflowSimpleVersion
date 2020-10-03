package ch.heigvd.amt.project.domain.person;

import ch.heigvd.amt.project.domain.IEntity;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Person implements IEntity<Person,PersonId> {

    @Setter(AccessLevel.NONE)
    private PersonId id;

    private String username;

    @EqualsAndHashCode.Exclude
    private String encryptedPassword;

    public boolean login(String clearTextPassword){
        encryptedPassword = clearTextPassword;
        return true; // TODO, real encryption
    }

    @Override
    public Person deepClone() {
        return this.toBuilder()
                .id(new PersonId(id.asString())) // shouldn't we also do the other paraneter?
                .build();
    }

    public static class PersonBuilder {
        public PersonBuilder clearTextPassword(String clearTextPassword){
            if(clearTextPassword == null || clearTextPassword.isEmpty()){
                throw new IllegalArgumentException("Password mandatory");
            }

            encryptedPassword = clearTextPassword; // TODO, real encryption
            return this;
        }

        public Person build(){

            if(id == null){
                id =new PersonId();
            }
            if(username==null || username.isEmpty()){
                throw new IllegalArgumentException("username mandatory");
            }

            Person newPerson = new Person(id,username,encryptedPassword); // this line is for debugger purpose
            return newPerson;
        }
    }
}
