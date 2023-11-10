package edu.pe.upao.friendlyfitnessbackend.controllers;

import edu.pe.upao.friendlyfitnessbackend.Dtos.ChallengeDTO;
import edu.pe.upao.friendlyfitnessbackend.models.Challenge;
import edu.pe.upao.friendlyfitnessbackend.models.Routine;
import edu.pe.upao.friendlyfitnessbackend.services.ChallengeService;
import edu.pe.upao.friendlyfitnessbackend.services.RoutinesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/challenge")
public class ChallengeController {
    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }
    @GetMapping
    private List<ChallengeDTO> getAllChallenge(){
        return challengeService.getAllChallenge();
    }

    @PostMapping
    public ResponseEntity<?> addChallenge(@RequestBody Challenge challenge) {
        try {
            String newChallenge = challengeService.addChallenge(challenge);
            return new ResponseEntity<>(newChallenge, HttpStatus.CREATED);
        } catch (IllegalStateException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
