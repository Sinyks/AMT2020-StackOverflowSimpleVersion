package ch.heigvd.amt.project.application.core.app.servicies.authenticationmgmt.questionmgmt.ask;

import ch.heigvd.amt.project.application.BusinessException;

public class AskFailedException extends BusinessException {
    public AskFailedException(String message) { super(message); }
}
