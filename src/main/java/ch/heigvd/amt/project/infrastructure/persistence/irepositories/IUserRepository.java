package ch.heigvd.amt.project.infrastructure.persistence.irepositories;

import ch.heigvd.amt.project.domain.user.User;
import ch.heigvd.amt.project.domain.user.UserId;
import ch.heigvd.amt.project.domain.exceptions.DataCorruptionException;

import java.util.Optional;

public interface IUserRepository extends IRepository<User, UserId> {
    Optional<User> retrieveByUsername(String username) throws DataCorruptionException; // only by username, if nedeed add byEmail
}
