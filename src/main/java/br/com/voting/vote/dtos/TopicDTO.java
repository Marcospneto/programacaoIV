package br.com.voting.vote.dtos;

import br.com.voting.vote.models.Topic;

import java.io.Serializable;

public class TopicDTO implements Serializable {
    private Long id;
    private String name;

    public TopicDTO() {

    }

    public TopicDTO(Topic obj) {

        name = obj.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}
