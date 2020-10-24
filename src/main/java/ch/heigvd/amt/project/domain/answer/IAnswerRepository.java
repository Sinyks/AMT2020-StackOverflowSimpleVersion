package ch.heigvd.amt.project.domain.answer;

import ch.heigvd.amt.project.domain.IRepository;
import ch.heigvd.amt.project.domain.question.QuestionId;

import java.util.Collection;

public interface IAnswerRepository extends IRepository<Answer, AnswerId> {
    public Collection<Answer> findByQuestionID(QuestionId questionId);
}
