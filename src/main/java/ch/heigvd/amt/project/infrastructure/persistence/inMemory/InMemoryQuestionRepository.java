package ch.heigvd.amt.project.infrastructure.persistence.inMemory;

import ch.heigvd.amt.project.application.questionmgmt.QuestionsQuery;
import ch.heigvd.amt.project.domain.question.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@ApplicationScoped
@Named("InMemoryQuestionRepository")
public class InMemoryQuestionRepository implements IQuestionRepository {

    private Map<QuestionId, Question> store = new ConcurrentHashMap<>();

    @Override
    public void save(Question question) {
        store.put(question.getId(), question);
    }

    @Override
    public void remove(QuestionId questionId) {
        store.remove(questionId);
    }

    public Optional<Question> findById(QuestionId questionId) {
        Question existingQuestion = store.get(questionId);
        if (existingQuestion == null) {
            return Optional.empty();
        }
        Question clonedQuestion = existingQuestion.toBuilder().build();
        return Optional.of(clonedQuestion);
    }

    @Override
    public Collection<Question> findAll() {
        return store.values().stream()
                .map(question -> question.toBuilder().build())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Question> find(QuestionsQuery query){
        if(query != null){
            return findAll().stream()
                    .collect(Collectors.toList());
        }
        return findAll();
    }
}
