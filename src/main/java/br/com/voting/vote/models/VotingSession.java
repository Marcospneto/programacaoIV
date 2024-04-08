package br.com.voting.vote.models;

import br.com.voting.vote.enums.StatusVotingSession;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "voting_session")
public class VotingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusVotingSession status;


    public VotingSession() {
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
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

    public Topic getTopic() {

        return topic;
    }

    public void setTopic(Topic topic) {

        this.topic = topic;
    }

    public StatusVotingSession getStatus() {

        return status;
    }

    public void setStatus(StatusVotingSession status) {

        this.status = status;
    }
}
