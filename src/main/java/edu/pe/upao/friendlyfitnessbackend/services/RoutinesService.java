package edu.pe.upao.friendlyfitnessbackend.services;

import edu.pe.upao.friendlyfitnessbackend.models.Routine;
import edu.pe.upao.friendlyfitnessbackend.repositories.RoutinesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoutinesService {
    private final RoutinesRepository routinesRepository;

    public RoutinesService(RoutinesRepository routinesRepository) {
        this.routinesRepository = routinesRepository;
    }

    public List<Routine> getAllRoutines(){
        return routinesRepository.findAll();
    }

    public Optional<Routine> getRoutineById(Long routinesID){
        return routinesRepository.findById(routinesID);
    }

    public Routine addRoutine(Routine routinesID){
        return routinesRepository.save(routinesID);
    }

    public void updateRoutine(Routine routine, Long routinesID){
        Routine routineExists = routinesRepository.findById(routinesID)
                .orElse(new Routine());
        routineExists.setDescription(routine.getDescription());
        routineExists.setDuration(routine.getDuration());
        routineExists.setLevel(routine.getLevel());
        routineExists.setExercise(routine.getExercise());
        routinesRepository.save(routineExists);
    }

    public void deleteRoutineById(Long routinesID){
        routinesRepository.deleteById(routinesID);
    }
}
