package br.com.voting.vote.controllers;
import br.com.voting.vote.dtos.VotingSessionDTO;
import br.com.voting.vote.services.VotingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/votingsession")
public class VotingSessionController {
    @Autowired
    private VotingSessionService votingSessionService;

    @PostMapping("/open")
    public ResponseEntity<Void> openVotingSession(@RequestBody VotingSessionDTO votingSessionDTO) {
        votingSessionService.openVotingSession(votingSessionDTO);

        return ResponseEntity.ok().build();
    }
}
