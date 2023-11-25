package edu.pe.upao.friendlyfitnessbackend.Service;

import edu.pe.upao.friendlyfitnessbackend.Dtos.PublicationDTO;
import edu.pe.upao.friendlyfitnessbackend.models.Publication;
import edu.pe.upao.friendlyfitnessbackend.repositories.PublicationRepository;
import edu.pe.upao.friendlyfitnessbackend.services.PublicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublicationServiceTest {

    @Mock
    private PublicationRepository publicationRepository;

    @InjectMocks
    private PublicationService publicationService;

    @Test
    void addPublication_validPublication_returnSuccessMessage() {
        // Arrange
        Publication validPublication = new Publication();
        validPublication.setDescription("Description");
        validPublication.setTitle("Title");

        // Act
        String result = publicationService.addPublication(validPublication);

        // Assert
        assertEquals("Publicacion subida de manera correcta", result);
        verify(publicationRepository, times(1)).save(validPublication);
    }

    @Test
    void addPublication_invalidPublication_throwIllegalStateException() {
        // Arrange
        Publication invalidPublication = new Publication();
        invalidPublication.setDescription("Description");

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> publicationService.addPublication(invalidPublication));
        verify(publicationRepository, never()).save(invalidPublication);
    }

    @Test
    void getAllPublications_returnPublicationDTOList() {
        // Arrange
        List<Publication> mockPublications = new ArrayList<>();
        Publication publication1 = new Publication();
        publication1.setTitle("Title1");
        publication1.setDescription("Description1");

        Publication publication2 = new Publication();
        publication2.setTitle("Title2");
        publication2.setDescription("Description2");

        mockPublications.add(publication1);
        mockPublications.add(publication2);

        when(publicationRepository.findAll()).thenReturn(mockPublications);

        // Act
        List<PublicationDTO> result = publicationService.getAllPublications();

        // Assert
        assertEquals(2, result.size());
        // Add more assertions based on your specific requirements
    }
}
