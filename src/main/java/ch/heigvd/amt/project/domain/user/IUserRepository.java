package ch.heigvd.amt.project.domain.user;

import ch.heigvd.amt.project.domain.IRepository;

import java.util.Optional;

public interface IUserRepository extends IRepository<User, UserId> {
    public Optional<User> findByUsername(String username);
}
