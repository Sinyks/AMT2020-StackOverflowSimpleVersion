package ch.heigvd.amt.project.infrastructure.persistence;

public class DataCorruptionException extends Error{
        public DataCorruptionException(String message){
        super(message);
    }
}
