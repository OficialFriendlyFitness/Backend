package edu.pe.upao.friendlyfitnessbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;
    @Column(name = "comment_day")
    private Instant commentDay;
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public Instant getCommentDay() {
        return commentDay;
    }

    public void setCommentDay(Instant commentDay) {
        this.commentDay = commentDay;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }
}
