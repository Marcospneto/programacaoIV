package br.com.voting.vote.controllers;

import br.com.voting.vote.dtos.TopicDTO;
import br.com.voting.vote.models.Topic;
import br.com.voting.vote.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;


    @PostMapping
    public ResponseEntity<Void> createTopic(@RequestBody TopicDTO objDTO) {
        Topic obj = topicService.fromDTO(objDTO);
        /* Direcionando para o service e o service direciona para o repositorio para ser
           salvo no banco
        */
        obj = topicService.createTopic(obj);
        /*
         * Boa pratica: Retornar a URL do cabeçalho criado Pega o endereço do novo
         * objeto que inseriu
         */
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(obj.getId()).toUri();
        /*O created retorna o codigo 201 http (Codigo de resposta quando cria um novo recurso)*/
        return ResponseEntity.created(uri).build();


    }
}
