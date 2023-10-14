package edu.pe.upao.friendlyfitnessbackend.repositories;

import edu.pe.upao.friendlyfitnessbackend.models.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
