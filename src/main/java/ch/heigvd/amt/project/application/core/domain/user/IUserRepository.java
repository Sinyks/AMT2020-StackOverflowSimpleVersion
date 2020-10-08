package ch.heigvd.amt.project.application.core.domain.user;

import ch.heigvd.amt.project.application.core.domain.entity.IRepository;
import ch.heigvd.amt.project.infrastructure.persistence.exceptions.DataCorruptionException;

import java.util.Optional;

public interface IUserRepository extends IRepository<User, UserId> {
    Optional<User> retrieveByUsername(String username) throws DataCorruptionException; // only by username, if nedeed add byEmail
}
