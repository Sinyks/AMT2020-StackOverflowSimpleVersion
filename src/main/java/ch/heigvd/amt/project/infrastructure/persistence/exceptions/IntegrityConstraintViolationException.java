package ch.heigvd.amt.project.infrastructure.persistence.exceptions;

import ch.heigvd.amt.project.application.core.domain.exceptions.PersistenceException;

public class IntegrityConstraintViolationException extends PersistenceException {
    public IntegrityConstraintViolationException(String message){
        super(message);
    }
}
