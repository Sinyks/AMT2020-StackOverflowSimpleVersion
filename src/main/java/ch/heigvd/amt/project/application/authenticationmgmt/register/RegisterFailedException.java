package ch.heigvd.amt.project.application.authenticationmgmt.register;

import ch.heigvd.amt.project.application.BusinessException;

public class RegisterFailedException extends BusinessException { // wtf is a businessexcpetion?
    public RegisterFailedException(String message) {super(message);};
}
