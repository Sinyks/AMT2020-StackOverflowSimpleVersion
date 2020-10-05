//package ch.heigvd.amt.project.domain.entity;
//
//import ch.heigvd.amt.project.domain.person.PersonId;
//import lombok.*;
//
//
//// to implement the IEntity method
//// problem with new Id (cant do as Id is abstract)
//// come back when we have more than just User Ententy
//// common code reduction
//
//
//@Getter
//@Setter
//@EqualsAndHashCode
//@Builder(toBuilder = true)
//public abstract class Entity implements IEntity<Entity, Id> {
//
//    @Setter(AccessLevel.NONE)
//    private PersonId id;
//
//
//    @Override
//    public Id getId() {
//        return this.toBuilder()
//                .id(new id(id.asString())) // shouldn't we also do the other paraneter?
//                .build();
//    }
//
//    @Override
//    public Entity deepClone() {
//        return null;
//    }
//}
