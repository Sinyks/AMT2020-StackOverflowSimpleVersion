package ch.heigvd.amt.project.infrastructure.persistence.jdbc;

import ch.heigvd.amt.project.application.core.app.servicies.authenticationmgmt.questionmgmt.QuestionsQuery;
import ch.heigvd.amt.project.application.core.domain.exceptions.PersistenceException;
import ch.heigvd.amt.project.application.core.domain.question.IQuestionRepository;
import ch.heigvd.amt.project.application.core.domain.question.Question;
import ch.heigvd.amt.project.application.core.domain.question.QuestionId;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class JdbcPgSqlQuestionRepository implements IQuestionRepository {


    @Override
    public Collection<Question> find(QuestionsQuery query) {
        return null;
    }

    @Override
    public Question create(Question entity) throws PersistenceException {
        return null;
    }

    @Override
    public Optional<Question> retrieve(QuestionId id) throws PersistenceException {
        return Optional.empty();
    }

    @Override
    public Collection<Question> retrieve() throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Question> retrieve(String string) throws PersistenceException {
        return null;
    }

    @Override
    public Question update(Question entity) throws PersistenceException {
        return null;
    }

    @Override
    public void delete(Question entity) throws PersistenceException {

    }

    @Override
    public void delete(QuestionId id) throws PersistenceException {

    }
}
