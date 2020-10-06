package ch.heigvd.amt.project.domain.vote;

import ch.heigvd.amt.project.domain.Id;

import java.util.UUID;

public class VoteId extends Id {

    //constructors
    public VoteId(){
        super();
    }

    public VoteId(String id){
        super(id);
    }

    public VoteId(UUID id){
        super(id);
    }
}