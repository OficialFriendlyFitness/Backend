package edu.pe.upao.friendlyfitnessbackend.repositories;

import edu.pe.upao.friendlyfitnessbackend.models.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoutinesRepository extends JpaRepository<Routine, Long> {
}
