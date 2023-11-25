package edu.pe.upao.friendlyfitnessbackend.Dtos;

import edu.pe.upao.friendlyfitnessbackend.models.Client;
import edu.pe.upao.friendlyfitnessbackend.models.Routine;
import lombok.Getter;

@Getter
public class RoutinesDTO {
    private String description;
    private int duration;
    private String level;
    private String exercise;
    private ClientDTO clientDTO;

    public RoutinesDTO(Routine routine) {
        this.description = routine.getDescription();
        this.duration = routine.getDuration();
        this.level = routine.getLevel();
        this.exercise = routine.getExercise();

        if (routine.getClient() != null) {
            this.clientDTO = new ClientDTO(routine.getClient().getEmail());
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }
}
