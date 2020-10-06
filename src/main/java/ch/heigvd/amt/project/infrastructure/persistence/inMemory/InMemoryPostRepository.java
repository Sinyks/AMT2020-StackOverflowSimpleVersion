package ch.heigvd.amt.project.infrastructure.persistence.inMemory;

import ch.heigvd.amt.project.application.questionmgmt.QuestionsQuery;
import ch.heigvd.amt.project.domain.post.*;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryPostRepository implements IPostRepository {

    private Map<PostId, Post> store = new ConcurrentHashMap<>();

    @Override
    public void save(Post post) {
        store.put(post.getId(), post);
    }

    @Override
    public void remove(PostId postId) {
        store.remove(postId);
    }

    public Optional<Post> findById(PostId postId) {
        Post existingPost = store.get(postId);
        if (existingPost == null) {
            return Optional.empty();
        }
        Post clonedPost = existingPost.toBuilder().build();
        return Optional.of(clonedPost);
    }

    @Override
    public Collection<Post> findAll() {
        return store.values().stream()
                .map(post -> post.toBuilder().build())
                .collect(Collectors.toList());
    }


    @Override
    public Collection<Post> findQuestions(QuestionsQuery query) {
        Collection<Post> allQuestions = findAll().stream()
                .filter(post -> post.getAnswerTo().equals(null))
                .collect(Collectors.toList());

        if (query != null) {
            return allQuestions.stream()
                    .collect(Collectors.toList());
        }
        return allQuestions;
    }

    @Override
    public Collection<Post> findResponses(PostId id) {
        Collection<Post> allResponses = findAll().stream()
                .filter(post -> post.getTags().equals(null))
                .collect(Collectors.toList());

        return allResponses.stream()
                .filter(post -> post.getAnswerTo().equals(id))
                .collect(Collectors.toList());

    }
}
