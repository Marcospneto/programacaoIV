package br.com.voting.vote.services.exceptions;

public class ResourceNotFoundExceptions extends RuntimeException{
    private static final long SerialVersionUID = 1L;

    public ResourceNotFoundExceptions(Object message){

        super ("Resource not found: " + message);
    }
}
