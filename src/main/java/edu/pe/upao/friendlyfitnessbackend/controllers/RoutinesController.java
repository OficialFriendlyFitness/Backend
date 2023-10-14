package edu.pe.upao.friendlyfitnessbackend.controllers;

import edu.pe.upao.friendlyfitnessbackend.models.Routine;
import edu.pe.upao.friendlyfitnessbackend.services.RoutinesService;
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
    private List<Routine> getAllRoutines(){
        return routinesService.getAllRoutines();
    }

    @PostMapping
    public void addRoutine(@RequestBody Routine routine){
        routinesService.addRoutine(routine);
    }

    @PutMapping
    public void addRoutine(@RequestBody Routine routine, @PathVariable Long routinesID){
        routinesService.updateRoutine(routine, routinesID);
    }

    @DeleteMapping("/{routinesID}")
    public void deleteRoutine(@PathVariable Long routinesID){
        routinesService.deleteRoutineById(routinesID);
    }
}
