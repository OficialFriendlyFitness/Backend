package edu.pe.upao.friendlyfitnessbackend.Service;

import edu.pe.upao.friendlyfitnessbackend.Dtos.ClientDTO;
import edu.pe.upao.friendlyfitnessbackend.models.Client;
import edu.pe.upao.friendlyfitnessbackend.repositories.ClientRepository;
import edu.pe.upao.friendlyfitnessbackend.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateClient() {
        // Arrange
        Client updatedClient = new Client();
        updatedClient.setFirstName("John");
        updatedClient.setLastName("Doe");
        updatedClient.setCell("123456789");
        updatedClient.setTastes("Fitness");
        updatedClient.setExpectations("Lose weight");
        updatedClient.setPreferences("Healthy diet");

        Long clientId = 1L;
        Client existingClient = new Client();
        existingClient.setClientID(clientId);
        existingClient.setFirstName("Old");
        existingClient.setLastName("Client");

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));

        // Act
        String result = clientService.updateClient(updatedClient, clientId);

        // Assert
        assertEquals("Cliente actualizado correctamente", result);
        assertEquals("John", existingClient.getFirstName());
        assertEquals("Doe", existingClient.getLastName());
        assertEquals("123456789", existingClient.getCell());
        assertEquals("Fitness", existingClient.getTastes());
        assertEquals("Lose weight", existingClient.getExpectations());
        assertEquals("Healthy diet", existingClient.getPreferences());

        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, times(1)).save(existingClient);
    }

    @Test
    public void testViewProfile() {
        // Arrange
        Long clientId = 1L;
        Client client = new Client();
        client.setClientID(clientId);
        client.setFirstName("John");
        client.setLastName("Doe");
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        // Act
        ClientDTO clientDTO = clientService.viewProfile(clientId);

        // Assert
        assertNotNull(clientDTO);
        assertEquals("John", clientDTO.getFirstName());
        assertEquals("Doe", clientDTO.getLastName());

        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    public void testAddClient() {
        // Arrange
        Client newClient = new Client();
        newClient.setFirstName("John");
        newClient.setLastName("Doe");
        newClient.setEmail("john.doe@example.com");
        newClient.setPassword("password");
        newClient.setAge(25);

        when(clientRepository.findByEmail(newClient.getEmail())).thenReturn(Optional.empty());

        // Act
        String result = clientService.addClient(newClient);

        // Assert
        assertEquals("Usuario registrado correctamente", result);
        verify(clientRepository, times(1)).findByEmail(newClient.getEmail());
        verify(clientRepository, times(1)).save(newClient);
    }
    @Test
    public void testUpdateClient_ClientNotFound() {
        // Arrange
        Long clientId = 1L;
        Client updatedClient = new Client();

        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clientService.updateClient(updatedClient, clientId));

        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, never()).save(any());
    }

    @Test
    public void testUpdateClient_InvalidFields() {
        // Arrange
        Long clientId = 1L;
        Client updatedClient = new Client();

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(new Client()));

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> clientService.updateClient(updatedClient, clientId));

        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, never()).save(any());
    }

    @Test
    public void testAddClient_EmailInUse() {
        // Arrange
        Client newClient = new Client();
        newClient.setEmail("existing.email@example.com");

        when(clientRepository.findByEmail(newClient.getEmail())).thenReturn(Optional.of(new Client()));

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> clientService.addClient(newClient));

        verify(clientRepository, times(1)).findByEmail(newClient.getEmail());
        verify(clientRepository, never()).save(any());
    }
    @Test
    public void testAddClient_Underage() {
        // Arrange
        Client newClient = new Client();
        newClient.setEmail("new.email@example.com");
        newClient.setFirstName("John");
        newClient.setLastName("Doe");
        newClient.setAge(17);

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> clientService.addClient(newClient));

        verify(clientRepository, times(1)).findByEmail(newClient.getEmail());
        verify(clientRepository, never()).save(any());
    }
    @Test
    public void testAddClient_ContainsNumbersInNameOrLastName() {
        // Arrange
        Client newClient = new Client();
        newClient.setEmail("new.email@example.com");
        newClient.setFirstName("John123");
        newClient.setLastName("Doe");

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> clientService.addClient(newClient));

        verify(clientRepository, times(1)).findByEmail(newClient.getEmail());
        verify(clientRepository, never()).save(any());
    }
    @Test
    public void testAddClient_PasswordLengthOutOfRange() {
        // Arrange
        Client newClient = new Client();
        newClient.setEmail("new.email@example.com");
        newClient.setFirstName("John");
        newClient.setLastName("Doe");
        newClient.setPassword("short");

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> clientService.addClient(newClient));

        verify(clientRepository, times(1)).findByEmail(newClient.getEmail());
        verify(clientRepository, never()).save(any());
    }

}