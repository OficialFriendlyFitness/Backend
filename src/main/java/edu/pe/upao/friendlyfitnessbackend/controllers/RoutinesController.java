package edu.pe.upao.friendlyfitnessbackend.controllers;

import edu.pe.upao.friendlyfitnessbackend.Dtos.RoutinesDTO;
import edu.pe.upao.friendlyfitnessbackend.models.Routine;
import edu.pe.upao.friendlyfitnessbackend.services.RoutinesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/routines")
public class RoutinesController {
    private final RoutinesService routinesService;

    public RoutinesController(RoutinesService routinesService) {
        this.routinesService = routinesService;
    }
    @GetMapping
    private List<RoutinesDTO> getAllRoutines(){
        return routinesService.getAllRoutines();
    }

    @PostMapping
    public ResponseEntity<String> addRoutine(@RequestBody Routine routine) {
        try {
            String newRoutine = routinesService.addRoutine(routine);
            return new ResponseEntity<>(newRoutine, HttpStatus.CREATED);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
