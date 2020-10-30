package ch.heigvd.amt.project.application.votemgmt.vote;

import ch.heigvd.amt.project.application.BusinessException;

public class VoteFailedException extends BusinessException {
    public VoteFailedException(String message){super(message);};
}
