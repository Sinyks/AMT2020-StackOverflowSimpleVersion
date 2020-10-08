package ch.heigvd.amt.project.application.core.app.servicies.authenticationmgmt.login;


import ch.heigvd.amt.project.application.core.app.servicies.authenticationmgmt.BusinessException;

public class LoginFailedException extends BusinessException { // wtf is a businessexcpetion?
    public LoginFailedException(String message) {
        super(message);
    }
}
