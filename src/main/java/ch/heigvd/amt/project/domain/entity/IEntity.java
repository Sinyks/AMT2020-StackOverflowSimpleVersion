package ch.heigvd.amt.project.domain.entity;

public interface IEntity<ENTITY extends IEntity<ENTITY,ID>, ID extends Id> {
    ID getId();
    ENTITY deepClone();
}
