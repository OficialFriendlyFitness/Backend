package edu.pe.upao.friendlyfitnessbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "challenge")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Challenge {
    @Id
    @Column(name = "challenge_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeID;
    @Column (name = "time")
    private int time;
    @Column (name = "video")
    private String video;

    @ManyToOne
    @JoinColumn(name = "routines_id")
    private  Routine routine;

    public Long getChallengeID() {
        return challengeID;
    }

    public void setChallengeID(Long challengeID) {
        this.challengeID = challengeID;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }
}
