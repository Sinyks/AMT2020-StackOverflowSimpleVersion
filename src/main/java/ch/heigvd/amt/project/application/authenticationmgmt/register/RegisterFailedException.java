package ch.heigvd.amt.project.application.authenticationmgmt.register;

import ch.heigvd.amt.project.application.BusinessException;

public class RegisterFailedException extends BusinessException {
    public RegisterFailedException(String message) {super(message);};
}
