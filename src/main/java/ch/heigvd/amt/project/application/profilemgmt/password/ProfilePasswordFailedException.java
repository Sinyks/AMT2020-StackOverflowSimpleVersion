package ch.heigvd.amt.project.application.profilemgmt.password;

import ch.heigvd.amt.project.application.BusinessException;


public class ProfilePasswordFailedException extends BusinessException {
    public ProfilePasswordFailedException(String message) {
        super(message);
    }
}
