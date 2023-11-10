package edu.pe.upao.friendlyfitnessbackend.services;

import edu.pe.upao.friendlyfitnessbackend.models.Challenge;
import edu.pe.upao.friendlyfitnessbackend.models.Client;
import edu.pe.upao.friendlyfitnessbackend.models.Routine;
import edu.pe.upao.friendlyfitnessbackend.repositories.ChallengeRepository;
import edu.pe.upao.friendlyfitnessbackend.repositories.RoutinesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    private boolean isEmptyOrWhitespace(String value) {
        return value == null || value.trim().isEmpty();
    }
    public List<Challenge> getAllChallenges(){
        return challengeRepository.findAll();
    }

    public Optional<Challenge> getChallengeById(Long challengeID){
        return challengeRepository.findById(challengeID);
    }

    public String addChallenge(Challenge challenge){
        if (isEmptyOrWhitespace(challenge.getVideo()) || isEmptyOrWhitespace(String.valueOf(challenge.getTime())) || challenge.getRoutine() == null) {
            throw new IllegalStateException("Todos los campos (video, time, routine) son requeridos");
        }
        challengeRepository.save(challenge);
        return "Reto guardado correctamente";
    }

}
