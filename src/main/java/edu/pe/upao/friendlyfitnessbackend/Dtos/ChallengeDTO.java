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
        this.time = challenge.getTime();
        this.video = challenge.getVideo();
        if (challenge.getRoutine() != null) {
            this.routinesDTO = new RoutinesDTO(challenge.getRoutine());
        }
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
