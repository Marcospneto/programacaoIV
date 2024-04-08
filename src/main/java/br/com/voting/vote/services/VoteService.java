package br.com.voting.vote.services;

import br.com.voting.vote.dtos.VoteDTO;
import br.com.voting.vote.enums.TypeVote;
import br.com.voting.vote.models.Associate;
import br.com.voting.vote.models.Vote;
import br.com.voting.vote.models.VotingSession;

import java.util.Map;

public interface VoteService {

    public Vote registerVote(Vote objVote);

    public boolean existsByAssociateAndTopic(Associate associate, Long topicId);

    public Map<TypeVote, Integer> contVotesTopic(Long topicId);

    public Vote fromDTO(VoteDTO objDTO);

    public boolean isVotingSessionExpired(VotingSession votingSession);

    public boolean isVotingSessionNotStarted(VotingSession votingSession);

}