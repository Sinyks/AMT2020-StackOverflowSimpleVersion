package ch.heigvd.amt.project.domain.tag;

import ch.heigvd.amt.project.domain.Id;
import lombok.Getter;

import java.util.UUID;

@Getter
public class TagId extends Id {


    //constructors
    public TagId(){
        super();
    }

    public TagId(String id){
        super(id);
    }

    public TagId(UUID id){
        super(id);
    }
}
