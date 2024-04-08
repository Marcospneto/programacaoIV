package br.com.voting.vote.services.impl;

import br.com.voting.vote.dtos.VotingSessionDTO;
import br.com.voting.vote.enums.StatusVotingSession;
import br.com.voting.vote.models.VotingSession;
import br.com.voting.vote.repositories.TopicRepository;
import br.com.voting.vote.repositories.VotingSessionRepository;
import br.com.voting.vote.services.VotingSessionService;
import br.com.voting.vote.services.exceptions.ResourceNotFoundExceptions;
import br.com.voting.vote.services.exceptions.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VotingSessionServiceImpl implements VotingSessionService {

    @Autowired
    private TopicRepository topicRepository;


    @Autowired
    private VotingSessionRepository votingSessionRepository;


    @Override
    public void openVotingSession(VotingSessionDTO votingSessionDTO) {


        LocalDateTime now = LocalDateTime.now();


        //Abrindo a sessão
        VotingSession votingSession = new VotingSession();
        /*Definindo o horario de inicio da sessão como horario atual*/

        if (votingSessionDTO.getStartTime() != null) {
            votingSession.setStartTime(votingSessionDTO.getStartTime());
        } else {
            votingSession.setStartTime(LocalDateTime.now());
        }
        if (votingSessionDTO.getEndTime() != null) {
            votingSession.setEndTime(votingSessionDTO.getEndTime());
        } else {
            votingSession.setEndTime(LocalDateTime.now().plusMinutes(1));
        }

        Long topicId = votingSessionDTO.getTopicId();
        if (topicId == null) {
            throw new ResourceNotFoundExceptions(topicId);
        }
        /*Obtendo o TOPICO associado a sessao de votação com base no ID fornecido no DTO*/
        var topic = topicRepository.findById(topicId).orElseThrow(() ->
                new ResourceNotFoundExceptions("Topic not found" + ", " + "topicId: " + votingSessionDTO.getTopicId()));
        /*Definindo o topico da sessao de votação*/
        votingSession.setTopic(topic);

        // Verificar se estamos dentro do intervalo de tempo definido para a sessão
        if (now.isBefore(votingSession.getStartTime()) || now.isAfter(votingSession.getEndTime())) {
            votingSession.setStatus(StatusVotingSession.CLOSE);
        } else {
            votingSession.setStatus(StatusVotingSession.OPEN);
        }
        votingSessionRepository.save(votingSession);


    }


}

