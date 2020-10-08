package ch.heigvd.amt.project.application.core.domain.question;

import ch.heigvd.amt.project.application.core.app.servicies.authenticationmgmt.questionmgmt.QuestionsQuery;
import ch.heigvd.amt.project.application.core.domain.entity.IRepository;

import java.util.Collection;

public interface IQuestionRepository extends IRepository<Question, QuestionId> {
    public Collection<Question> find(QuestionsQuery query);
}
