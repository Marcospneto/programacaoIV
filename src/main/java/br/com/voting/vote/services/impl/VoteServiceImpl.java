package br.com.voting.vote.services.impl;

import br.com.voting.vote.dtos.VoteDTO;
import br.com.voting.vote.enums.StatusVotingSession;
import br.com.voting.vote.enums.TypeVote;
import br.com.voting.vote.models.Associate;
import br.com.voting.vote.models.Topic;
import br.com.voting.vote.models.Vote;
import br.com.voting.vote.models.VotingSession;
import br.com.voting.vote.repositories.AssociateRepository;
import br.com.voting.vote.repositories.TopicRepository;
import br.com.voting.vote.repositories.VoteRepository;
import br.com.voting.vote.repositories.VotingSessionRepository;
import br.com.voting.vote.services.VoteService;
import br.com.voting.vote.services.exceptions.ResourceNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    private AssociateRepository associateRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private VotingSessionRepository votingSessionRepository;
    @Autowired
    private VoteRepository voteRepository;


    @Override
    public Vote registerVote(Vote objVote) {

        objVote.getVotingSession().setStatus(StatusVotingSession.CLOSE);

        if (existsByAssociateAndTopic(objVote.getAssociate(), objVote.getTopic().getId())) {
            throw new ResourceNotFoundExceptions("Associate already voted on this topic");
        }
        // Verifica se a sessão de votação está dentro do período de votação
        if (isVotingSessionNotStarted(objVote.getVotingSession())) {
            objVote.getVotingSession().setStatus(StatusVotingSession.CLOSE);
            throw new ResourceNotFoundExceptions("Voting session has not started yet.");

        } else if (isVotingSessionExpired(objVote.getVotingSession())) {
            Vote vtexception = new Vote();
            vtexception.setVotingSession(objVote.getVotingSession());
            vtexception.getVotingSession().setStatus(StatusVotingSession.CLOSE);
            // Salvar o novo objeto Vote no repositório
            voteRepository.save(vtexception);

            throw new ResourceNotFoundExceptions("Voting session has ended. Cannot register vote.");

        } else {
            // Define o status da sessão como "OPEN" se estiver dentro do período de votação
            objVote.getVotingSession().setStatus(StatusVotingSession.OPEN);
        }

        // Salva o voto se o associado ainda não votou no mesmo tópico
        return voteRepository.save(objVote);

    }


    @Override
    public int contVotesTopic(Long topicId, TypeVote type) {

        return 0;
    }

    @Override
    public Vote fromDTO(VoteDTO objDTO) {

        Vote vote = new Vote();
        /* Obtendo o ID do associado do DTO */
        Long associateId = objDTO.getAssociateId();

        /* Verificando se o ID do associado não é nulo */
        if (associateId == null) {
            throw new ResourceNotFoundExceptions(associateId);
        }
        /* Busque o associado correspondente no banco de dados */
        Associate associate = associateRepository.findById(associateId)
                .orElseThrow(() -> new ResourceNotFoundExceptions("AssociateId not found: " + associateId));
        /* Defina o associado no objeto Vote */
        vote.setAssociate(associate);



        /* TOPIC */
        Long topicId = objDTO.getTopicId();
        if (topicId == null) {
            throw new ResourceNotFoundExceptions(topicId);
        }

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundExceptions(" TopicId not found: " + topicId));


        vote.setTopic(topic);



        /*VotingSession*/
        Long votingSessionId = objDTO.getVotingSessionId();
        if (votingSessionId == null) {
            throw new ResourceNotFoundExceptions(votingSessionId);
        }

        VotingSession votingSession = votingSessionRepository.findById(votingSessionId)
                .orElseThrow(() -> new ResourceNotFoundExceptions(" VotingSessionId not found: "+ votingSessionId));


        vote.setVotingSession(votingSession);

        vote.setTypeVote(objDTO.getTypeVote());

        // Retorne o objeto Vote criado
        return vote;
    }

    @Override
    public boolean existsByAssociateAndTopic(Associate associate, Long topicId) {
        List<Vote> votes = associate.getVotes();

        for (Vote vote : votes) {
            if (vote.getTopic().getId().equals(topicId)) {
                return true;
            }
        }

        return false;
    }

    public boolean isVotingSessionExpired(VotingSession votingSession) {
        // Obtém a data e hora atual
        LocalDateTime now = LocalDateTime.now();
        // Obtém o endTime da sessão de votação
        LocalDateTime endTime = votingSession.getEndTime();

        // Verifica se a data e hora atual é posterior ao endTime
        return now.isAfter(endTime);
    }

    public boolean isVotingSessionNotStarted(VotingSession votingSession) {
        // Obtém a data e hora atual
        LocalDateTime now = LocalDateTime.now();
        // Obtém o startTime da sessão de votação
        LocalDateTime startTime = votingSession.getStartTime();

        // Verifica se a data e hora atual é anterior ao startTime
        return now.isBefore(startTime);
    }

}
