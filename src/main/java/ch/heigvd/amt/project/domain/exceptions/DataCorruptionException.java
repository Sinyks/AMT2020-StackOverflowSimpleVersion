package ch.heigvd.amt.project.domain.exceptions;

import ch.heigvd.amt.project.domain.exceptions.PersistenceException;

public class DataCorruptionException extends PersistenceException {
        public DataCorruptionException(String message){
        super(message);
    }
}
