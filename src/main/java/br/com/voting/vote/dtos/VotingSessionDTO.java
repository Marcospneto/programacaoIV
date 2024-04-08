package br.com.voting.vote.dtos;

import br.com.voting.vote.enums.StatusVotingSession;
import br.com.voting.vote.models.Topic;
import br.com.voting.vote.models.VotingSession;

import java.time.LocalDateTime;

public class VotingSessionDTO {


    private Long topicId;
    private StatusVotingSession status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public VotingSessionDTO(Long topicId, StatusVotingSession status, LocalDateTime startTime, LocalDateTime endTime) {

        this.topicId = topicId;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public Long getTopicId() {

        return topicId;
    }

    public void setTopic(Long topicId) {

        this.topicId = topicId;
    }

    public StatusVotingSession getStatus() {

        return status;
    }

    public void setStatus(StatusVotingSession status) {

        this.status = status;
    }

    public LocalDateTime getStartTime() {

        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {

        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {

        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {

        this.endTime = endTime;
    }
}
