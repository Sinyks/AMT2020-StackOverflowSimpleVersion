package ch.heigvd.amt.project.application.answermgmt.answer;

import ch.heigvd.amt.project.application.BusinessException;

public class CommentFailedException extends BusinessException {

    public CommentFailedException(String message) {
        super(message);
    }
}
