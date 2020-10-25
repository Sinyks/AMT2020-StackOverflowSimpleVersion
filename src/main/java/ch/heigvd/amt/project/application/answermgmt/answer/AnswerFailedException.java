package ch.heigvd.amt.project.application.answermgmt.answer;

import ch.heigvd.amt.project.application.BusinessException;

public class AnswerFailedException extends BusinessException {

    public AnswerFailedException(String message) {
        super(message);
    }
}
