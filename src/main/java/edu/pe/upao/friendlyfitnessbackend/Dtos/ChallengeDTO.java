package edu.pe.upao.friendlyfitnessbackend.Dtos;

import edu.pe.upao.friendlyfitnessbackend.models.Challenge;
import edu.pe.upao.friendlyfitnessbackend.models.Routine;
import lombok.Getter;

@Getter
public class ChallengeDTO {
    private int time;
    private String video;
    private RoutinesDTO routinesDTO;

    public ChallengeDTO(Challenge challenge) {
        this.time = time;
        this.video = video;
        this.routinesDTO = routinesDTO;

    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setRoutinesDTO(RoutinesDTO routinesDTO) {
        this.routinesDTO = routinesDTO;
    }
}
