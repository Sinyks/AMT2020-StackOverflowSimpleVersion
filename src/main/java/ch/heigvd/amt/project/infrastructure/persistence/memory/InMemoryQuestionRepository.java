package ch.heigvd.amt.project.infrastructure.persistence.memory;

import ch.heigvd.amt.project.application.questionmgmt.QuestionsQuery;
import ch.heigvd.amt.project.domain.question.*;
import ch.heigvd.amt.project.infrastructure.persistence.irepositories.IQuestionRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryQuestionRepository implements IQuestionRepository {

    private Map<QuestionId, Question> store = new ConcurrentHashMap<>();

    @Override
    public void save(Question question){
        store.put(question.getId(), question);
    }

    @Override
    public void remove(QuestionId questionId){
        store.remove(questionId);
    }

    public Optional<Question> findById(QuestionId questionId) {
        Question existingQuestion = store.get(questionId);
        if(existingQuestion == null){
            return Optional.empty();
        }
        Question clonedQuestion = existingQuestion.toBuilder().build();
        return Optional.of(clonedQuestion);
    }

    @Override
    public Collection<Question> findAll(){
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
