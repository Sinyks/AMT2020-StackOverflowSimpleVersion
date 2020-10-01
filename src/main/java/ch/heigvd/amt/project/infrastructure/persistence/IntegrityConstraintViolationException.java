package ch.heigvd.amt.project.infrastructure.persistence;

public class IntegrityConstraintViolationException extends Error{
    public IntegrityConstraintViolationException(String message){
        super(message);
    }
}
