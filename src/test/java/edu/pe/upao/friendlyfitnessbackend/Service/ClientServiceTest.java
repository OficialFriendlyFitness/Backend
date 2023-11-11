package edu.pe.upao.friendlyfitnessbackend.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import edu.pe.upao.friendlyfitnessbackend.models.Client;
import edu.pe.upao.friendlyfitnessbackend.repositories.ClientRepository;
import edu.pe.upao.friendlyfitnessbackend.services.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void testUpdateClient() {
        // Arrange
        Long clientId = 1L;
        Client existingClient = new Client();
        existingClient.setFirstName("John");
        existingClient.setLastName("Doe");

        Client updatedClient = new Client();
        updatedClient.setFirstName("Jane");
        updatedClient.setLastName("Doe");

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(any(Client.class))).thenReturn(updatedClient);

        // Act
        String result = clientService.updateClient(updatedClient, clientId);

        // Assert
        assertEquals("Cliente actualizado correctamente", result);
        assertEquals("Jane", existingClient.getFirstName());
        assertEquals("Doe", existingClient.getLastName());

        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, times(1)).save(existingClient);
    }

    @Test
    void testViewProfile() {
        // Arrange
        Long clientId = 1L;
        Client expectedClient = new Client();
        expectedClient.setFirstName("John");
        expectedClient.setLastName("Doe");

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(expectedClient));

        // Act
        Client result = clientService.viewProfile(clientId);

        // Assert
        assertEquals(expectedClient, result);

        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    void testAddClient_Success() {
        // Arrange
        Client newClient = new Client();
        newClient.setFirstName("John");
        newClient.setLastName("Doe");
        newClient.setEmail("john.doe@example.com");
        newClient.setPassword("securePassword");

        when(clientRepository.findByEmail(newClient.getEmail())).thenReturn(Arrays.asList());
        when(clientRepository.save(any(Client.class))).thenReturn(newClient);

        // Act
        String result = clientService.addClient(newClient);

        // Assert
        assertEquals("Usuario registrado correctamente", result);

        verify(clientRepository, times(1)).findByEmail(newClient.getEmail());
        verify(clientRepository, times(1)).save(newClient);
    }

    @Test
    void testAddClient_EmailInUse() {
        // Arrange
        Client existingClient = new Client();
        existingClient.setEmail("john.doe@example.com");

        Client newClient = new Client();
        newClient.setEmail("john.doe@example.com");

        when(clientRepository.findByEmail(newClient.getEmail())).thenReturn(Arrays.asList(existingClient));

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> clientService.addClient(newClient));

        verify(clientRepository, times(1)).findByEmail(newClient.getEmail());
        verify(clientRepository, never()).save(any(Client.class));
    }

    @Test
    void testAddClient_PasswordLengthInvalid() {
        // Arrange
        Client newClient = new Client();
        newClient.setPassword("short");

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> clientService.addClient(newClient));

        verify(clientRepository, never()).findByEmail(anyString());
        verify(clientRepository, never()).save(any(Client.class));
    }

    @Test
    void testAddClient_PasswordFormatInvalid() {
        // Arrange
        Client newClient = new Client();
        newClient.setPassword("password123"); // No symbols

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> clientService.addClient(newClient));

        verify(clientRepository, never()).findByEmail(anyString());
        verify(clientRepository, never()).save(any(Client.class));
    }

    @Test
    void testLogin_Success() {
        // Arrange
        String email = "john.doe@example.com";
        String password = "securePassword";
        Client existingClient = new Client();
        existingClient.setEmail(email);
        existingClient.setPassword(password);

        when(clientRepository.findByEmail(email)).thenReturn(Arrays.asList(existingClient));

        // Act
        Client result = clientService.login(email, password);

        // Assert
        assertEquals(existingClient, result);

        verify(clientRepository, times(1)).findByEmail(email);
    }

    @Test
    void testLogin_IncorrectPassword() {
        // Arrange
        String email = "john.doe@example.com";
        String password = "wrongPassword";
        Client existingClient = new Client();
        existingClient.setEmail(email);
        existingClient.setPassword("securePassword");

        when(clientRepository.findByEmail(email)).thenReturn(Arrays.asList(existingClient));

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> clientService.login(email, password));

        verify(clientRepository, times(1)).findByEmail(email);
    }

    @Test
    void testLogin_IncorrectEmail() {
        // Arrange
        String email = "nonexistent@example.com";
        String password = "securePassword";

        when(clientRepository.findByEmail(email)).thenReturn(Arrays.asList());

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> clientService.login(email, password));

        verify(clientRepository, times(1)).findByEmail(email);
    }
}
