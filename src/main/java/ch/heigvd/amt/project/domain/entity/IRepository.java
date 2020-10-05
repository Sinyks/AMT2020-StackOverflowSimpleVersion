package ch.heigvd.amt.project.domain.entity;

import ch.heigvd.amt.project.domain.exceptions.PersistenceException;

import java.util.Collection;
import java.util.Optional;

public interface IRepository<ENTITY extends IEntity, ID extends Id> {

    ENTITY create(ENTITY entity) throws PersistenceException;
    Optional<ENTITY> retrieve(ID id) throws PersistenceException;
    Collection<ENTITY> retrieve() throws PersistenceException;
    Collection<ENTITY> retrieve(String string) throws PersistenceException;
    ENTITY update(ENTITY entity) throws PersistenceException;
    void delete(ENTITY entity) throws PersistenceException;
    void delete(ID id) throws PersistenceException;

    /*
    public void save(ENTITY entity);
    public void remove(ID id);

     */
}
