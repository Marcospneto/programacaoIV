package br.com.voting.vote.services;

import br.com.voting.vote.dtos.TopicDTO;
import br.com.voting.vote.models.Topic;


public interface TopicService {
    public Topic createTopic (Topic obj);

    public Topic fromDTO (TopicDTO objDTO);



}
