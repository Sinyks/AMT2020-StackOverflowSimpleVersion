package ch.heigvd.amt.project.domain.comment;

import ch.heigvd.amt.project.domain.Id;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CommentId extends Id {


    //constructors
    public CommentId(){
        super();
    }

    public CommentId(String id){
        super(id);
    }

    public CommentId(UUID id){
        super(id);
    }
}
