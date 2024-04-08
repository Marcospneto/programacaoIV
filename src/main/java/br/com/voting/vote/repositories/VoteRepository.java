package br.com.voting.vote.repositories;

import br.com.voting.vote.models.Associate;
import br.com.voting.vote.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

}
