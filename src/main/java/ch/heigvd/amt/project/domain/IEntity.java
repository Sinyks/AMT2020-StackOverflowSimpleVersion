package ch.heigvd.amt.project.domain;

public interface IEntity<ENTITY extends IEntity<ENTITY,ID>, ID extends Id> {
    //stuff like get id or clone
    ID getId();
    ENTITY deepClone();
}
