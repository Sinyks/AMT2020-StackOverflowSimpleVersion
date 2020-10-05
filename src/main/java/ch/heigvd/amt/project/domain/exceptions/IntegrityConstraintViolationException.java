package ch.heigvd.amt.project.domain.exceptions;

public class IntegrityConstraintViolationException extends PersistenceException{
    public IntegrityConstraintViolationException(String message){
        super(message);
    }
}
