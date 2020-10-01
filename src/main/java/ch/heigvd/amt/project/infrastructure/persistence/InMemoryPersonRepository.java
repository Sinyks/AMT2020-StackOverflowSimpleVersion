package ch.heigvd.amt.project.infrastructure.persistence;

import ch.heigvd.amt.project.domain.person.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryPersonRepository extends InMemoryRepository<Person, PersonId> implements IPersonRepository {
    @Override
    public Optional<Person> findByUsername(String username) {
        List<Person> matchingEntities = findAll().stream()
                .filter(person -> person.getUsername().equals(username))
                .collect(Collectors.toList());

        if (matchingEntities.size() < 1) {
            return Optional.empty();
        }

        if (matchingEntities.size() > 1) {
            throw new DataCorruptionException("Data store corrupt");
        }

        return Optional.of(matchingEntities.get(0).deepClone());

    }

    @Override
    public void remove(PersonId personId) {

    }

    @Override
    public Optional<Person> findById(PersonId personId) {
        return Optional.empty();
    }

    @Override
    public void save(Person entity) {
        //enforce unique username
        synchronized (entity.getUsername()) {
            if (!findByUsername(entity.getUsername()).isEmpty()) {
                throw new IntegrityConstraintViolationException("this person already exists in store");
            }
        }
        super.save(entity);
    }

}
