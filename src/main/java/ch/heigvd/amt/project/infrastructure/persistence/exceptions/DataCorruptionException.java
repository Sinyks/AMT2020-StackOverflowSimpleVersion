package ch.heigvd.amt.project.infrastructure.persistence.exceptions;

import ch.heigvd.amt.project.application.core.domain.exceptions.PersistenceException;

public class DataCorruptionException extends PersistenceException {
        public DataCorruptionException(String message){
        super(message);
    }
}
