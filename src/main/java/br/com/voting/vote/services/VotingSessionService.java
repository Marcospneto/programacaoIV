package br.com.voting.vote.services;

import br.com.voting.vote.dtos.VotingSessionDTO;
import br.com.voting.vote.models.VotingSession;

public interface VotingSessionService {
    public void openVotingSession(VotingSessionDTO votingSessionDTO);

}
