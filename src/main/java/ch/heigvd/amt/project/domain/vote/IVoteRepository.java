package ch.heigvd.amt.project.domain.vote;

import ch.heigvd.amt.project.domain.IRepository;
import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.question.QuestionId;

import java.util.Collection;

public interface IVoteRepository extends IRepository<Vote, VoteId> {
    public Collection<Vote> findByQuestionID(QuestionId questionId);

    public Collection<Vote> findByAnswerID(AnswerId answerId);
}
