package ch.heigvd.amt.project.application.authenticationmgmt.login;

public class LoginFailedException extends BusinessException { // wtf is a businessexcpetion?
    public LoginFailedException(String message) {
        super(message);
    }
}
