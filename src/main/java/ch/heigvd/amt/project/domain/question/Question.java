//
//package ch.heigvd.amt.project.domain.question;
//
//import ch.heigvd.amt.project.domain.entity.IEntity;
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Data;
//import lombok.Setter;
//
//@Data
//@Builder(toBuilder = true)
//public class Question implements IEntity<Question,QuestionId> {
//
//    @Setter(AccessLevel.NONE)
//    private QuestionId id;
//
//    private String label;
//    private String content;
//
//    @Override
//    public Question deepClone() {
//        return this.toBuilder()
//                .id(new QuestionId(id.asString()))
//                .build();
//    }
//
//    public static class QuestionBuilder {
//        public Question build(){
//            if(id == null){
//                id = new QuestionId();
//            }
//            if(label == null || label.isEmpty()){
//                throw new IllegalArgumentException("questions need a label");
//            }
//            if(content == null || content.isEmpty()){
//                throw new IllegalArgumentException("questions can't be empty");
//            }
//
//            return new Question(id, label, content);
//        }
//    }
//}
//
//
