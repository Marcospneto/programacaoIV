package br.com.voting.vote.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "associate")
public class Associate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "cpf")
    @NotBlank
    private String cpf;

    @OneToMany(mappedBy = "associate")
    private List<Vote> votes;

    public Associate() {

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

    public String getCpf() {

        return cpf;
    }

    public void setCpf(String cpf) {

        this.cpf = cpf;
    }

    public List<Vote> getVotes() {

        return votes;
    }

    public void setVotes(List<Vote> votes) {

        this.votes = votes;
    }
}

