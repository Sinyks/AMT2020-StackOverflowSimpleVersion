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

    /*
    private static HashMap<String,String> fakeDatabase = new HashMap<>();

    static public void isAuth(String username, String password){
        if(!isCorrectPassword(username,password)){
            throw new IllegalArgumentException("incorrect password");
        }
    }

    static private boolean isCorrectPassword(String username, String password){
        if(isInDataBase(username)) {
            String tmp = fakeDatabase.get(username);
            return fakeDatabase.get(username).equals(password);
        }
        return false;
    }

    static private boolean isInDataBase(String username){
        return fakeDatabase.containsKey(username);
    }

    static public void addToDataBase(String username, String password){
        if(isInDataBase(username)){
            throw new IllegalArgumentException("already in database");
        }
        fakeDatabase.put(username,password);
    }*/
}
