package ch.heigvd.amt.project.domain.tag;

import ch.heigvd.amt.project.domain.IEntity;
import lombok.*;

@Getter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Tag implements IEntity<Tag, TagId> {
    private TagId id;

    private String name; // should this be final?

    // should we declare all possible tags statically?

    @Override // does a deep clone really make sense in a situation where tags are predetermined?
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
