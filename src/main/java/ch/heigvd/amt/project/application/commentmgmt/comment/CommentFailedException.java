package ch.heigvd.amt.project.application.commentmgmt.comment;


import ch.heigvd.amt.project.application.BusinessException;

public class CommentFailedException extends BusinessException {

    public CommentFailedException(String message) {
        super(message);
    }
}
