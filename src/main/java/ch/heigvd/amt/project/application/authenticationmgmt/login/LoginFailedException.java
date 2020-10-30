package ch.heigvd.amt.project.application.authenticationmgmt.login;

import ch.heigvd.amt.project.application.BusinessException;

public class LoginFailedException extends BusinessException {
    public LoginFailedException(String message) {
        super(message);
    }
}
