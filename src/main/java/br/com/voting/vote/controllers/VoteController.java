package br.com.voting.vote.controllers;

import br.com.voting.vote.dtos.VoteDTO;
import br.com.voting.vote.models.Vote;
import br.com.voting.vote.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    private VoteService voteService;
    @PostMapping
    public ResponseEntity<Void> registerVote(@RequestBody VoteDTO objDTO) {
        Vote objVote = voteService.fromDTO(objDTO);
        /* Direcionando para o service e o service direciona para o repositorio para ser
           salvo no banco*/

        objVote = voteService.registerVote(objVote);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(objVote.getId()).toUri();
        /*O created retorna o codigo 201 http (Codigo de resposta quando cria um novo recurso)*/
        return ResponseEntity.created(uri).build();
    }



}
