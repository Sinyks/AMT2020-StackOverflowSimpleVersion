package ch.heigvd.amt.project.domain.user;

import ch.heigvd.amt.project.domain.entity.Id;

import java.util.UUID;

public class UserId extends Id {

    //constructors
    public UserId(){
        super();
    }

    public UserId(String id){
        super(id);
    }

    public UserId(UUID id){
        super(id);
    }
}
