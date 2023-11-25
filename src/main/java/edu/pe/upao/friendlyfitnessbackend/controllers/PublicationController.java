package edu.pe.upao.friendlyfitnessbackend.controllers;

import edu.pe.upao.friendlyfitnessbackend.Dtos.PublicationDTO;
import edu.pe.upao.friendlyfitnessbackend.models.Publication;
import edu.pe.upao.friendlyfitnessbackend.services.PublicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publication")
public class PublicationController {
    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }
    @PostMapping("/post")
    public ResponseEntity<?> addPost(@RequestBody Publication publication){
        try{
            String newPost= publicationService.addPublication(publication);
            return new ResponseEntity<>(newPost, HttpStatus.CREATED);
        } catch (IllegalStateException sms) {
            return new ResponseEntity<>(sms.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/viewpost")
    public List<PublicationDTO> getAllPublications() {
        return publicationService.getAllPublications();
    }
}
