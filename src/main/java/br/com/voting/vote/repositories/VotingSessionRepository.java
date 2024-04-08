package br.com.voting.vote.repositories;

import br.com.voting.vote.enums.StatusVotingSession;
import br.com.voting.vote.models.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {
    List<VotingSession> findByStatus(StatusVotingSession statusVotingSession);
}
