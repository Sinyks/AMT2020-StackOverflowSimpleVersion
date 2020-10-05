package ch.heigvd.amt.project.infrastructure.persistence.exceptions;

public class DataCorruptionException extends PersistenceException{
        public DataCorruptionException(String message){
        super(message);
    }
}
