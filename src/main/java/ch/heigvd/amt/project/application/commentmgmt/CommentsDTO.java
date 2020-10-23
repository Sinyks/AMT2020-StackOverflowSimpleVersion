package ch.heigvd.amt.project.application.commentmgmt;

import ch.heigvd.amt.project.domain.user.UserId;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@EqualsAndHashCode
public class CommentsDTO {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class CommentDTO {
        private UUID id;
        private UUID questionID;
        private UUID answerID;
        private Date creationDate;
        private Date lastEditDate;
        private UserId ownerId;
        private String ownerName;
        private String body;
    }

    @Singular
    private List<CommentDTO> comments;
}
