package ch.heigvd.amt.project.infrastructure.persistence.exceptions;

public class IntegrityConstraintViolationException extends PersistenceException{
    public IntegrityConstraintViolationException(String message){
        super(message);
    }
}
