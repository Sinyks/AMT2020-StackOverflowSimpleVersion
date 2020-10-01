package ch.heigvd.amt.project.domain.person;

import ch.heigvd.amt.project.domain.IRepository;

import java.util.Optional;

public interface IPersonRepository extends IRepository<Person,PersonId> {
    public Optional<Person> findByUsername(String username);
}
