package edu.pe.upao.friendlyfitnessbackend.services;

import edu.pe.upao.friendlyfitnessbackend.Dtos.PublicationDTO;
import edu.pe.upao.friendlyfitnessbackend.models.Publication;
import edu.pe.upao.friendlyfitnessbackend.repositories.PublicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PublicationService {
    private final PublicationRepository publicationRepository;

    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    private boolean isEmptyOrWhitespace(String value) {
        return value == null || value.trim().isEmpty();
    }

    public String addPublication(Publication publication) {
        if (isEmptyOrWhitespace(publication.getDescription()) || publication.getTitle() == null) {
            throw new IllegalStateException("Todos los campos son requeridos");
        }
        publication.setDatePublication(Instant.now());
        publicationRepository.save(publication);
        return "Publicacion subida de manera correcta";
    }
    public List<PublicationDTO> getAllPublications() {
        List<Publication> publications = publicationRepository.findAll();
        List<PublicationDTO> publicationAll = new ArrayList<>();
        for (Publication publication : publications) {
            publicationAll.add(new PublicationDTO(publication));
        }
        return publicationAll;
    }
}
