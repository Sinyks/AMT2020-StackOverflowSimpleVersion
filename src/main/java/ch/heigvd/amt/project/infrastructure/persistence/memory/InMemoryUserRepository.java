package ch.heigvd.amt.project.infrastructure.persistence.memory;

import ch.heigvd.amt.project.domain.user.*;
import ch.heigvd.amt.project.infrastructure.persistence.exceptions.DataCorruptionException;
import ch.heigvd.amt.project.infrastructure.persistence.exceptions.IntegrityConstraintViolationException;
import ch.heigvd.amt.project.infrastructure.persistence.irepositories.IUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryUserRepository extends InMemoryRepository<User, UserId> implements IUserRepository {
    @Override
    public Optional<User> retrieveByUsername(String username) throws DataCorruptionException {
        List<User> matchingEntities = findAll().stream()
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
    public void remove(UserId userId) {

    }

    @Override
    public Optional<User> findById(UserId userId) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) throws IntegrityConstraintViolationException {
        //enforce unique username
        synchronized (entity.getUsername()) {
            if (!retrieveByUsername(entity.getUsername()).isEmpty()) {
                throw new IntegrityConstraintViolationException("this person already exists in store");
            }
        }
        super.save(entity);
    }

}
