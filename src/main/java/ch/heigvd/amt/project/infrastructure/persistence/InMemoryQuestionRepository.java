package ch.heigvd.amt.project.infrastructure.persistence;

import ch.heigvd.amt.project.application.questionmgmt.QuestionsQuery;
import ch.heigvd.amt.project.domain.question.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryQuestionRepository extends InMemoryRepository<Question,QuestionId> implements IQuestionRepository {
    public Optional<Question> findByLabel(String label) {
        List<Question> matchingEntities = findAll().stream()
                .filter(question -> question.getLabel().equals(label))
                .collect(Collectors.toList());
        if(matchingEntities.size() < 1) {
            return Optional.empty();
        }

        if(matchingEntities.size() > 1) {
            throw new DataCorruptionException("Datastore corrupt");
        }

        return Optional.of(matchingEntities.get(0).deepClone());
    }

    @Override
    public void remove(QuestionId questionId) {

    }

    @Override
    public Optional<Question> findById(QuestionId questionId) { return Optional.empty(); }

    @Override
    public void save(Question entity) {
        //we might want to be able to ask questions with the same label, but not for now
        synchronized (entity.getLabel()) {
            if(!findByLabel(entity.getLabel()).isEmpty()) {
                throw new IntegrityConstraintViolationException("this question has already been asked");
            }
        }
        super.save(entity);
    }

    @Override
    public Collection<Question> find(QuestionsQuery query) {
        return null;
    }
}
