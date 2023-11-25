package edu.pe.upao.friendlyfitnessbackend.Dtos;

import edu.pe.upao.friendlyfitnessbackend.models.Publication;
import lombok.Getter;

import java.time.Instant;

@Getter
public class PublicationDTO {
    private String description;
    private String title;
    private Instant datePublication;
    private ClientDTO clientDTO;

    public PublicationDTO(Publication publication) {
        this.description = publication.getDescription();
        this.title = publication.getTitle();
        this.datePublication = publication.getDatePublication();
        if (publication.getClient() != null) {
            this.clientDTO = new ClientDTO(publication.getClient().getEmail());
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDatePublication(Instant datePublication) {
        this.datePublication = datePublication;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }
}
