package edu.pe.upao.friendlyfitnessbackend.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import edu.pe.upao.friendlyfitnessbackend.models.Challenge;
import edu.pe.upao.friendlyfitnessbackend.models.Routine;
import edu.pe.upao.friendlyfitnessbackend.repositories.ChallengeRepository;
import edu.pe.upao.friendlyfitnessbackend.services.ChallengeService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ChallengeServiceTest {

    private final ChallengeRepository challengeRepository = mock(ChallengeRepository.class);
    private final ChallengeService challengeService = new ChallengeService(challengeRepository);

    @Test
    void testGetAllChallenges() {
        // Arrange
        List<Challenge> mockChallenges = new ArrayList<>();
        Challenge mockChallenge1 = new Challenge();
        Challenge mockChallenge2 = new Challenge();
        mockChallenges.add(mockChallenge1);
        mockChallenges.add(mockChallenge2);

        when(challengeRepository.findAll()).thenReturn(mockChallenges);

        // Act
        List<Challenge> result = challengeService.getAllChallenges();

        // Assert
        assertEquals(2, result.size());
        verify(challengeRepository, times(1)).findAll();
    }

    @Test
    void testGetChallengeById() {
        // Arrange
        Long challengeID = 1L;
        Challenge mockChallenge = new Challenge();
        mockChallenge.setChallengeID(challengeID);

        when(challengeRepository.findById(challengeID)).thenReturn(Optional.of(mockChallenge));

        // Act
        Optional<Challenge> result = challengeService.getChallengeById(challengeID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(challengeID, result.get().getChallengeID());
        verify(challengeRepository, times(1)).findById(challengeID);
    }

    @Test
    void testAddChallenge_Success() {
        // Arrange
        Challenge mockChallenge = new Challenge();
        mockChallenge.setVideo("Mock Video");
        mockChallenge.setTime(30);
        Routine mockRoutine = new Routine();
        mockRoutine.setRoutinesID(1L);
        mockChallenge.setRoutine(mockRoutine);

        when(challengeRepository.save(any(Challenge.class))).thenReturn(mockChallenge);

        // Act
        String result = challengeService.addChallenge(mockChallenge);

        // Assert
        assertEquals("Reto guardado correctamente", result);
        verify(challengeRepository, times(1)).save(mockChallenge);
    }

    @Test
    void testAddChallenge_MissingFields() {
        // Arrange
        Challenge mockChallenge = new Challenge();

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> challengeService.addChallenge(mockChallenge));

        verify(challengeRepository, never()).save(any(Challenge.class));
    }
}
