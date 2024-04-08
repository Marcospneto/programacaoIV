package br.com.voting.vote.services.impl;

import br.com.voting.vote.dtos.TopicDTO;
import br.com.voting.vote.models.Topic;
import br.com.voting.vote.repositories.TopicRepository;

import br.com.voting.vote.services.TopicService;
import br.com.voting.vote.services.exceptions.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService{
    @Autowired
    private TopicRepository topicRepository;


    @Override
    public Topic createTopic(Topic obj) {
        if (obj.getName() == null || obj.getName().isEmpty()) {
            throw new ResourceValidationException("Invalid field! "+"name:"+obj.getName());
        }
        return topicRepository.save(obj);
    }

    @Override
    public Topic fromDTO(TopicDTO objDTO) {

        return new Topic(objDTO.getName());
    }
}
