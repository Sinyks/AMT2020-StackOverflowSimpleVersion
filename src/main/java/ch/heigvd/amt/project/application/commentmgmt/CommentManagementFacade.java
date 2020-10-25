package ch.heigvd.amt.project.application.commentmgmt;

import ch.heigvd.amt.project.application.commentmgmt.comment.CommentCommand;
import ch.heigvd.amt.project.application.answermgmt.answer.CommentFailedException;
import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.answer.IAnswerRepository;
import ch.heigvd.amt.project.domain.comment.Comment;
import ch.heigvd.amt.project.domain.comment.ICommentRepository;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.IUserRepository;
import ch.heigvd.amt.project.domain.user.User;
import ch.heigvd.amt.project.domain.user.UserId;

import javax.swing.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CommentManagementFacade {

    private ICommentRepository commentRepository;
    private IAnswerRepository answerRepository;
    private IUserRepository userRepository;


    public CommentManagementFacade(ICommentRepository commentRepository,IAnswerRepository answerRepository, IUserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
    }

    private String getUserNameById(UserId id){
        User user = userRepository.findById(id).orElse(null);

        if(user != null){
            return user.getUsername();
        }else {
            throw new NullPointerException("No user with this id found");
        }
    }

    public CommentsDTO getComments(QuestionId id){
        Collection<Comment> allComments = commentRepository.findByQuestionID(id);

        List<CommentsDTO.CommentDTO> allCommentsDTO = allComments.stream().map(comment -> CommentsDTO.CommentDTO.builder()
                .id(comment.getId().getId())
                .questionID(comment.getQuestionId().getId())
                .creationDate(comment.getCreationDate())
                .lastEditDate(comment.getLastEditDate())
                .ownerId(comment.getOwnerId())
                .ownerName(getUserNameById(comment.getOwnerId()))
                .body(comment.getBody())
                .build()).collect(Collectors.toList());

        return CommentsDTO.builder()
                .comments(allCommentsDTO)
                .build();
    }

    public CommentsDTO getComments(AnswerId id){
        Collection<Comment> allComments = commentRepository.findByAnswerID(id);

        List<CommentsDTO.CommentDTO> allCommentsDTO = allComments.stream().map(comment -> CommentsDTO.CommentDTO.builder()
                .id(comment.getId().getId())
                .answerID(comment.getAnswerId().getId())
                .creationDate(comment.getCreationDate())
                .lastEditDate(comment.getLastEditDate())
                .ownerId(comment.getOwnerId())
                .ownerName(getUserNameById(comment.getOwnerId()))
                .body(comment.getBody())
                .build()).collect(Collectors.toList());

        return CommentsDTO.builder()
                .comments(allCommentsDTO)
                .build();
    }

    public void comment(CommentCommand command) throws CommentFailedException {
        Comment newComment = null;
        if(command.getAnswerId() != null){
            newComment = Comment.builder()
                    .body(command.getBody())
                    .ownerId(command.getOwnerID())
                    .answerId(command.getAnswerId())
                    .build();
        }else{
            newComment = Comment.builder()
                    .body(command.getBody())
                    .ownerId(command.getOwnerID())
                    .questionId(command.getQuestionId())
                    .build();
        }

        commentRepository.save(newComment);
    }
}
