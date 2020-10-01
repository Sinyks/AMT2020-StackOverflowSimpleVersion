package ch.heigvd.amt.project.domain;

import java.util.Collection;
import java.util.Optional;

public interface IRepository<IENTITY extends IEntity, ID extends Id> {

    public void save(IENTITY entity);
    public void remove(ID id);
    public Optional<IENTITY> findById(ID id);
    public Collection<IENTITY> findAll();
}
