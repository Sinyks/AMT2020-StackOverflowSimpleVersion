package ch.heigvd.amt.project.domain.post;

import ch.heigvd.amt.project.application.questionmgmt.QuestionsQuery;
import ch.heigvd.amt.project.domain.IRepository;

import java.util.Collection;

public interface IPostRepository extends IRepository<Post, PostId> {
    public Collection<Post> findQuestions(QuestionsQuery query);
    public Collection<Post> findResponses(PostId id);
}
