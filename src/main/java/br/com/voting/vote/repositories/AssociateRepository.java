package br.com.voting.vote.repositories;

import br.com.voting.vote.models.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {
}
