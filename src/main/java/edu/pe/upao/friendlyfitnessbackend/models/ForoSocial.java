package edu.pe.upao.friendlyfitnessbackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

public class ForoSocial {
    @OneToMany
    @Column(name = "publication_id")
    Publication publication;
}
