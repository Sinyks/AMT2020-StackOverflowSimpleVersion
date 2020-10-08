package ch.heigvd.amt.project.application.core.app.servicies.authenticationmgmt.register;

import ch.heigvd.amt.project.application.BusinessException;

public class RegisterFailedException extends BusinessException { // wtf is a businessexcpetion?
    public RegisterFailedException(String message) {super(message);};
}
