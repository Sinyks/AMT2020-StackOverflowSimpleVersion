package ch.heigvd.amt.project.application.questionmgmt;

import ch.heigvd.amt.project.application.questionmgmt.ask.*;
import ch.heigvd.amt.project.domain.post.IPostRepository;
import ch.heigvd.amt.project.domain.post.Post;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Collection;

public class QuestionsManagementFacade {

    private IPostRepository postRepository;

    public QuestionsManagementFacade(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    //change this to void and make the QuestionsDTO so that we can display them all at the same time
    public void ask (AskCommand command) throws AskFailedException {
        Post newQuestion = Post.builder()
                .ownerName(command.getOwnerName())
                .title(command.getTitle())
                .body(command.getBody())
                .tags(command.getTags())
                .build();
        postRepository.save(newQuestion);
    }

    public QuestionsDTO getQuestions(QuestionsQuery query) {
        Collection<Post> allQuestions = postRepository.find(query);

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO = allQuestions.stream().map(question -> QuestionsDTO.QuestionDTO.builder()
                .ownerName(question.getOwnerName())
                .body(question.getBody())
                .title(question.getTitle())
                .tags(question.getTags())
                .build()).collect(Collectors.toList());

        return QuestionsDTO.builder()
                .questions(allQuestionsDTO)
                .build();
    }

}
