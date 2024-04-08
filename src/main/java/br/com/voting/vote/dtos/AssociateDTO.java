package br.com.voting.vote.dtos;

public class AssociateDTO {

    private String id;
    private String name;
    private String cpf;

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setNome(String name) {

        this.name = name;
    }

    public String getCpf() {

        return cpf;
    }

    public void setCpf(String cpf) {

        this.cpf = cpf;
    }
}
