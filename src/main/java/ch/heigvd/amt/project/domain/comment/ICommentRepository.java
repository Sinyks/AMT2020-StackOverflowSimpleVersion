package ch.heigvd.amt.project.domain.comment;

import ch.heigvd.amt.project.domain.IRepository;
import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.question.QuestionId;

import java.util.Collection;

public interface ICommentRepository extends IRepository<Comment, CommentId> {

    public Collection<Comment> findByQuestionID(QuestionId questionId);

    public Collection<Comment> findByAnswerID(AnswerId answerId);

}
