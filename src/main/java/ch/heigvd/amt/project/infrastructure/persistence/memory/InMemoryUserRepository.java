package ch.heigvd.amt.project.infrastructure.persistence.memory;

import ch.heigvd.amt.project.domain.exceptions.PersistenceException;
import ch.heigvd.amt.project.domain.user.*;
import ch.heigvd.amt.project.domain.exceptions.DataCorruptionException;
import ch.heigvd.amt.project.domain.exceptions.IntegrityConstraintViolationException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryUserRepository extends InMemoryRepository<User, UserId> implements IUserRepository {
    @Override
    public Optional<User> retrieveByUsername(String username) throws DataCorruptionException {
        List<User> matchingEntities = retrieve().stream()
                .filter(user -> user.getUsername().equals(username))
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
    public void delete(UserId userId) {

    }

    @Override
    public Optional<User> retrieve(UserId userId) {
        return Optional.empty();
    }

    @Override
    public Collection<User> retrieve(String string) throws PersistenceException {
        return null; // has to implement this
    }

    @Override
    public User update(User entity) throws PersistenceException {
        return null; // has to implement this
    }

    @Override
    public void delete(User entity) throws PersistenceException {
        // has to implement this
    }

    @Override
    public User create(User entity) throws IntegrityConstraintViolationException, DataCorruptionException {
        //enforce unique username
        synchronized (entity.getUsername()) {
            if (!retrieveByUsername(entity.getUsername()).isEmpty()) {
                throw new IntegrityConstraintViolationException("this person already exists in store");
            }
        }
        super.create(entity); // TODO we should return the database question to prove it has been created
        return entity;
    }

}
