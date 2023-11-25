package edu.pe.upao.friendlyfitnessbackend.services;
import edu.pe.upao.friendlyfitnessbackend.Dtos.ClientDTO;
import edu.pe.upao.friendlyfitnessbackend.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import edu.pe.upao.friendlyfitnessbackend.models.Client;

import java.util.Optional;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRespository) {
        this.clientRepository = clientRespository;
    }

    private boolean isEmptyOrWhitespace(String value) {
        return value == null || value.trim().isEmpty();
    }

    public String updateClient(Client updatedClient, Long clientId) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        if (isEmptyOrWhitespace(updatedClient.getFirstName()) || isEmptyOrWhitespace(updatedClient.getLastName())) {
            throw new IllegalStateException("Los campos 'firstName' y 'lastName' son requeridos");
        }
        existingClient.setFirstName(updatedClient.getFirstName());
        existingClient.setLastName(updatedClient.getLastName());
        existingClient.setCell(updatedClient.getCell());
        existingClient.setTastes(updatedClient.getTastes());
        existingClient.setExpectations(updatedClient.getExpectations());
        existingClient.setPreferences(updatedClient.getPreferences());
        clientRepository.save(existingClient);

        return "Cliente actualizado correctamente";
    }

    public ClientDTO viewProfile(Long clientId) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        return optionalClient.map(ClientDTO::new).orElse(null);
    }

    public String addClient(Client client) {
        Optional<Client> existingUserByEmail = clientRepository.findByEmail(client.getEmail());
        if (isEmptyOrWhitespace(client.getFirstName()) || isEmptyOrWhitespace(client.getLastName()) || isEmptyOrWhitespace(client.getEmail()) || (client.getPassword()) == null) {
            throw new IllegalStateException("Todos los campos son requeridos");
        }
        if (containsNumbers(client.getFirstName()) || containsNumbers(client.getLastName())) {
            throw new IllegalStateException("No se aceptan numeros en el nombre o apellido");
        }
        if (client.getAge() < 18) {
            throw new IllegalStateException("Debes tener al menos 18 años para registrarte");
        }
        if (!client.getEmail().contains("@")) {
            throw new IllegalStateException("El email debe contener un '@'");
        }
        if (!existingUserByEmail.isEmpty()) {
            throw new IllegalStateException("El correo que ingresaste ya está en uso");
        }
        if (client.getPassword().length() < 8 || client.getPassword().length() > 16) {
            throw new IllegalStateException("La contraseña debe tener entre 8 y 16 caracteres");
        }

        clientRepository.save(client);
        return "Usuario registrado correctamente";
    }

    private boolean containsNumbers(String input) {
        return input.matches(".*\\d.*");
    }

}