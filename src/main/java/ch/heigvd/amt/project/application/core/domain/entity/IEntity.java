package ch.heigvd.amt.project.application.core.domain.entity;

public interface IEntity<ENTITY extends IEntity<ENTITY,ID>, ID extends Id> {
    ID getId();
    ENTITY deepClone();
}
