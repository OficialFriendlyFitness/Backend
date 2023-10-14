package edu.pe.upao.friendlyfitnessbackend.services;

import edu.pe.upao.friendlyfitnessbackend.models.Challenge;
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

    public List<Challenge> getAllChallenges(){
        return challengeRepository.findAll();
    }

    public Optional<Challenge> getChallengeById(Long challengeID){
        return challengeRepository.findById(challengeID);
    }

    public Challenge addChallenge(Challenge challengeID){
        return challengeRepository.save(challengeID);
    }

    public void deleteChallengeById(Long challengeID){
        challengeRepository.deleteById(challengeID);
    }
}
