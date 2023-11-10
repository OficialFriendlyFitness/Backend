package edu.pe.upao.friendlyfitnessbackend.services;

import edu.pe.upao.friendlyfitnessbackend.Dtos.RoutinesDTO;
import edu.pe.upao.friendlyfitnessbackend.models.Routine;
import edu.pe.upao.friendlyfitnessbackend.repositories.RoutinesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoutinesService {
    private final RoutinesRepository routinesRepository;

    public RoutinesService(RoutinesRepository routinesRepository) {
        this.routinesRepository = routinesRepository;
    }

    public List<RoutinesDTO> getAllRoutines() {
        List<Routine> routines = routinesRepository.findAll();
        List<RoutinesDTO> routinesEx = new ArrayList<>();
        for (Routine routine : routines) {
            routinesEx.add(new RoutinesDTO(routine));
        }
        return routinesEx;
    }

    public Optional<Routine> getRoutineById(Long routinesID){
        return routinesRepository.findById(routinesID);
    }

    private boolean isEmptyOrWhitespace(String value) {
        return value == null || value.trim().isEmpty();
    }

    public String addRoutine(Routine routine) {

        if (isEmptyOrWhitespace(routine.getDescription()) || isEmptyOrWhitespace(String.valueOf(routine.getDuration())) || isEmptyOrWhitespace(routine.getLevel()) || routine.getExercise() == null) {
            throw new IllegalStateException("Todos los campos son requeridos");
        }

        routinesRepository.save(routine);
        return "Rutina subida de manera correcta";
    }

}
