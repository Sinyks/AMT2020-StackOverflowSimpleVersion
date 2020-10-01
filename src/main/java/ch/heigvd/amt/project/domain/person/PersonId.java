package ch.heigvd.amt.project.domain.person;

import ch.heigvd.amt.project.domain.Id;

import java.util.UUID;

public class PersonId extends Id {

    //constructors
    public PersonId(){
        super();
    }

    public PersonId(String id){
        super(id);
    }

    public PersonId(UUID id){
        super(id);
    }
}
