package ch.heigvd.amt.project.infrastructure.persistence.memory;

import ch.heigvd.amt.project.application.core.app.servicies.authenticationmgmt.questionmgmt.QuestionsQuery;
import ch.heigvd.amt.project.application.core.domain.exceptions.PersistenceException;
import ch.heigvd.amt.project.application.core.domain.question.*;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryQuestionRepository implements IQuestionRepository {

    private Map<QuestionId, Question> store = new ConcurrentHashMap<>();

    @Override
    public Question create(Question question){
        store.put(question.getId(), question);
        return question; // TODO we should return the database question to prove it has been created
    }


    @Override
    public void delete(QuestionId questionId){
        store.remove(questionId);
    }

    @Override
    public Optional<Question> retrieve(QuestionId questionId) {
        Question existingQuestion = store.get(questionId);
        if(existingQuestion == null){
            return Optional.empty();
        }
        Question clonedQuestion = existingQuestion.toBuilder().build();
        return Optional.of(clonedQuestion);
    }

    @Override
    public Collection<Question> retrieve(){
        return store.values().stream()
                .map(question -> question.toBuilder().build())
                .collect(Collectors.toList());
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
    public Collection<Question> find(QuestionsQuery query){
        if(query != null){
            return retrieve().stream()
                    .collect(Collectors.toList());
        }
        return retrieve();
    }
}
