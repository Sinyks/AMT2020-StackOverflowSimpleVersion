package ch.heigvd.amt.project.domain.tag;

import ch.heigvd.amt.project.domain.IEntity;
import ch.heigvd.amt.project.domain.user.UserId;
import lombok.*;

@Getter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Tag implements IEntity<Tag, TagId> {
    @Setter(AccessLevel.NONE)
    private TagId id;

    private String name;

    @Override
    public Tag deepClone() {
        return this.toBuilder()
                .id(new TagId(id.asString()))
                .build();
    }

    public static class TagBuilder {
        public Tag build(){
            if(id==null){
                id=new TagId();
            }
            if(name==null || name.isEmpty()){
                throw new IllegalArgumentException("name mandatory");
            }

            Tag newTag = new Tag(id, name);
            return newTag;
        }
    }
}
