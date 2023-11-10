package edu.pe.upao.friendlyfitnessbackend.controllers;

import edu.pe.upao.friendlyfitnessbackend.models.Challenge;
import edu.pe.upao.friendlyfitnessbackend.models.Routine;
import edu.pe.upao.friendlyfitnessbackend.services.ChallengeService;
import edu.pe.upao.friendlyfitnessbackend.services.RoutinesService;
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
    private List<Challenge> getAllChallenge(){
        return challengeService.getAllChallenges();
    }

    @PostMapping
    public void addChallenge(@RequestBody Challenge challenge){
        challengeService.addChallenge(challenge);
    }

}
