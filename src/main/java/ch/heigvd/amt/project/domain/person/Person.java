package ch.heigvd.amt.project.domain.person;

import ch.heigvd.amt.project.domain.IEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Person implements IEntity<Person,PersonId> {

    private PersonId id;
    private String username;

    @EqualsAndHashCode.Exclude
    private String encryptedPassword;

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

            encryptedPassword = clearTextPassword; // ici mettre le chiffrement duh TODO
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
