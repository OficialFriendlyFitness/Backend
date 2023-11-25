package edu.pe.upao.friendlyfitnessbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
@Entity
@Table(name = "publications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publication {
    @Id
    @Column(name = "publication_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publicationID;
    @Column(name = "publict_descriptions")
    private String description;
    @Column(name = "date_publications")
    private Instant datePublication;
    @Column(name = "publict_title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    public Long getPublicationID() {
        return publicationID;
    }

    public void setPublicationID(Long publicationID) {
        this.publicationID = publicationID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Instant datePublication) {
        this.datePublication = datePublication;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
