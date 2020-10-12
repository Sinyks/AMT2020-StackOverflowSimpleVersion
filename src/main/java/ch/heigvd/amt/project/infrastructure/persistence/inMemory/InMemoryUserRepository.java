package ch.heigvd.amt.project.infrastructure.persistence.inMemory;

import ch.heigvd.amt.project.domain.user.*;
import ch.heigvd.amt.project.infrastructure.persistence.DataCorruptionException;
import ch.heigvd.amt.project.infrastructure.persistence.IntegrityConstraintViolationException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryUserRepository extends InMemoryRepository<User, UserId> implements IUserRepository {
    @Override
    public Optional<User> findByUsername(String username) {
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
        super.remove(userId); // should we do synchronization here?
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return super.findById(userId);
    }

    @Override
    public void save(User entity) {
        //enforce unique username
        synchronized (entity.getUsername()) {
            if (!findByUsername(entity.getUsername()).isEmpty()) {
                throw new IntegrityConstraintViolationException("this person already exists in store");
            }
        }
        super.save(entity);
    }

}
