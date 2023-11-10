package edu.pe.upao.friendlyfitnessbackend.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import edu.pe.upao.friendlyfitnessbackend.Dtos.RoutinesDTO;
import edu.pe.upao.friendlyfitnessbackend.models.Client;
import edu.pe.upao.friendlyfitnessbackend.models.Routine;
import edu.pe.upao.friendlyfitnessbackend.repositories.RoutinesRepository;
import edu.pe.upao.friendlyfitnessbackend.services.RoutinesService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class RoutinesServiceTest {

    private final RoutinesRepository routinesRepository = mock(RoutinesRepository.class);
    private final RoutinesService routinesService = new RoutinesService(routinesRepository);

    @Test
    void testGetAllRoutines() {
        // Arrange
        List<Routine> mockRoutines = new ArrayList<>();
        Routine mockRoutine1 = new Routine();
        Routine mockRoutine2 = new Routine();
        mockRoutines.add(mockRoutine1);
        mockRoutines.add(mockRoutine2);

        when(routinesRepository.findAll()).thenReturn(mockRoutines);

        // Act
        List<RoutinesDTO> result = routinesService.getAllRoutines();

        // Assert
        assertEquals(2, result.size());
        verify(routinesRepository, times(1)).findAll();
    }

    @Test
    void testGetRoutineById() {
        // Arrange
        Long routinesID = 1L;
        Routine mockRoutine = new Routine();
        mockRoutine.setRoutinesID(routinesID);

        when(routinesRepository.findById(routinesID)).thenReturn(Optional.of(mockRoutine));

        // Act
        Optional<Routine> result = routinesService.getRoutineById(routinesID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(routinesID, result.get().getRoutinesID());
        verify(routinesRepository, times(1)).findById(routinesID);
    }

    @Test
    void testAddRoutine_Success() {
        // Arrange
        Routine mockRoutine = new Routine();
        mockRoutine.setDescription("Mock Description");
        mockRoutine.setDuration(30);
        mockRoutine.setLevel("Beginner");
        mockRoutine.setExercise("Running");
        Client mockClient = new Client();
        mockClient.setEmail("client@example.com");
        mockRoutine.setClient(mockClient);

        when(routinesRepository.save(any(Routine.class))).thenReturn(mockRoutine);

        // Act
        String result = routinesService.addRoutine(mockRoutine);

        // Assert
        assertEquals("Rutina subida de manera correcta", result);
        verify(routinesRepository, times(1)).save(mockRoutine);
    }

    @Test
    void testAddRoutine_MissingFields() {
        // Arrange
        Routine mockRoutine = new Routine();
        Client mockClient = new Client();
        mockClient.setEmail("client@example.com");
        mockRoutine.setClient(mockClient);

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> routinesService.addRoutine(mockRoutine));

        verify(routinesRepository, never()).save(any(Routine.class));
    }
}
