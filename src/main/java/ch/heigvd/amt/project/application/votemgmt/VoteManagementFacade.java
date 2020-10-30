package ch.heigvd.amt.project.application.votemgmt;

import ch.heigvd.amt.project.application.votemgmt.vote.VoteCommand;
import ch.heigvd.amt.project.application.votemgmt.vote.VoteFailedException;
import ch.heigvd.amt.project.domain.vote.IVoteRepository;
import ch.heigvd.amt.project.domain.vote.Vote;
import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.answer.IAnswerRepository;
import ch.heigvd.amt.project.domain.comment.ICommentRepository;
import ch.heigvd.amt.project.domain.question.QuestionId;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class VoteManagementFacade {

    private IVoteRepository voteRepository;
    private ICommentRepository commentRepository;
    private IAnswerRepository answerRepository;


    public VoteManagementFacade(IVoteRepository voteRepository, ICommentRepository commentRepository, IAnswerRepository answerRepository) {
        this.voteRepository = voteRepository;
        this.commentRepository = commentRepository;
        this.answerRepository = answerRepository;
    }

    public VotesDTO getVotes(QuestionId id){
        Collection<Vote> allVotes = voteRepository.findByQuestionID(id);

        List<VotesDTO.VoteDTO> allVotesDTO = allVotes.stream().map(vote -> VotesDTO.VoteDTO.builder()
                .id(vote.getId())
                .questionId(vote.getQuestionId())
                .answerId(vote.getAnswerId())
                .ownerId(vote.getOwnerId())
                .isUpVote(vote.isUpVote())
                .build()).collect(Collectors.toList());

        return VotesDTO.builder()
                .votes(allVotesDTO)
                .build();
    }

    public VotesDTO getVotes(AnswerId id){
        Collection<Vote> allVotes = voteRepository.findByAnswerID(id);

        List<VotesDTO.VoteDTO> allVotesDTO = allVotes.stream().map(vote -> VotesDTO.VoteDTO.builder()
                .id(vote.getId())
                .questionId(vote.getQuestionId())
                .answerId(vote.getAnswerId())
                .ownerId(vote.getOwnerId())
                .isUpVote(vote.isUpVote())
                .build()).collect(Collectors.toList());

        return VotesDTO.builder()
                .votes(allVotesDTO)
                .build();
    }

    public void vote(VoteCommand command) throws VoteFailedException {
        Vote newVote = null;
        if(command.getAnswerId() != null){
            newVote = Vote.builder()
                    .isUpVote(command.isUpVote())
                    .ownerId(command.getOwnerId())
                    .answerId(command.getAnswerId())
                    .build();
        }else{
            newVote = Vote.builder()
                    .isUpVote(command.isUpVote())
                    .ownerId(command.getOwnerId())
                    .questionId(command.getQuestionId())
                    .build();
        }

        voteRepository.save(newVote);
    }
}
