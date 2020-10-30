package ch.heigvd.amt.project.application.questionmgmt.ask;

import ch.heigvd.amt.project.application.BusinessException;

public class AskFailedException extends BusinessException {
    public AskFailedException(String message) {
        super(message);
    }
}
