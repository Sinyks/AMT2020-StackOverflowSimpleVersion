package ch.heigvd.amt.project.domain.user;

import ch.heigvd.amt.project.domain.exceptions.DataCorruptionException;
import ch.heigvd.amt.project.infrastructure.persistence.irepositories.IRepository;

import java.util.Optional;

public interface IUserRepository extends IRepository<User, UserId> {
    Optional<User> retrieveByUsername(String username) throws DataCorruptionException; // only by username, if nedeed add byEmail
}
