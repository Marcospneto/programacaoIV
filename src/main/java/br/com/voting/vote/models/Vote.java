package br.com.voting.vote.models;

import br.com.voting.vote.enums.TypeVote;
import jakarta.persistence.*;

@Entity
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "associate_id")
    private Associate associate;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne(optional = false)
    @JoinColumn(name = "voting_session_id", nullable = false)
    private VotingSession votingSession;

    @Column(name = "type_vote")
    @Enumerated(EnumType.STRING)
    private TypeVote typeVote;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public Topic getTopic() {

        return topic;
    }

    public void setTopic(Topic topic) {

        this.topic = topic;
    }

    public VotingSession getVotingSession() {

        return votingSession;
    }

    public void setVotingSession(VotingSession votingSession) {

        this.votingSession = votingSession;
    }

    public TypeVote getTypeVote() {

        return typeVote;
    }

    public void setTypeVote(TypeVote typeVote) {

        this.typeVote = typeVote;
    }
}
