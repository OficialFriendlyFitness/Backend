package edu.pe.upao.friendlyfitnessbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "routines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Routine {
    @Getter
    @Id
    @JsonIgnore
    @Column(name = "routines_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routinesID;
    @Column (name = "description")
    private String description;
    @Column (name = "duration")
    private int duration;
    @Column (name = "level")
    private String level;
    @Column (name = "type_exercise")
    private String exercise;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public void setRoutinesID(Long routinesID) {
        this.routinesID = routinesID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
