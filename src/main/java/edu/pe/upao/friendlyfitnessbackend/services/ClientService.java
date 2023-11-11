package edu.pe.upao.friendlyfitnessbackend.services;
import edu.pe.upao.friendlyfitnessbackend.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import edu.pe.upao.friendlyfitnessbackend.models.Client;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

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

        // Realiza las validaciones necesarias para la actualización
        // Puedes adaptar estas validaciones según tus requerimientos específicos

        if (isEmptyOrWhitespace(updatedClient.getFirstName()) || isEmptyOrWhitespace(updatedClient.getLastName())) {
            throw new IllegalStateException("Los campos 'firstName' y 'lastName' son requeridos");
        }

        // Actualiza los campos permitidos
        existingClient.setFirstName(updatedClient.getFirstName());
        existingClient.setLastName(updatedClient.getLastName());

        // Otros campos que puedes actualizar según tus necesidades
        existingClient.setCell(updatedClient.getCell());
        existingClient.setTastes(updatedClient.getTastes());
        existingClient.setExpectations(updatedClient.getExpectations());
        existingClient.setPreferences(updatedClient.getPreferences());

        // Guarda el cliente actualizado en la base de datos
        clientRepository.save(existingClient);

        return "Cliente actualizado correctamente";
    }

    public Client viewProfile(Long clientId) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        return optionalClient.orElse(null);
    }
    public String addClient(Client client) {
        List<Client> existingUserByEmail = clientRepository.findByEmail(client.getEmail());
        if (isEmptyOrWhitespace(client.getFirstName()) || isEmptyOrWhitespace(client.getLastName()) || isEmptyOrWhitespace(client.getEmail()) || (client.getPassword()) == null) {
            throw new IllegalStateException("Todos los campos son requeridos");
        }
        if (!existingUserByEmail.isEmpty()) {
            throw new IllegalStateException("El correo que ingresaste ya está en uso");
        }
        if (client.getPassword().length() < 8 || client.getPassword().length() > 16) {
            throw new IllegalStateException("La contraseña debe tener entre 8 y 16 caracteres");
        }
     /*     if (!containsSymbol(client.getPassword()) || !containsUpperCase(client.getPassword()) || !containsLowerCase(client.getPassword())) {
            throw new IllegalStateException("La contraseña debe contener símbolos, letras mayúsculas y minúsculas");
        }  */
        clientRepository.save(client);
        return "Usuario registrado correctamente";
    }

    /*   public boolean containsSymbol(String password) {
           // Verifica si la contraseña contiene al menos un símbolo
           return Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find();
       }

       public boolean containsUpperCase(String password) {
           // Verifica si la contraseña contiene al menos una letra mayúscula
           return Pattern.compile("[A-Z]").matcher(password).find();
       }

       public boolean containsLowerCase(String password) {
           // Verifica si la contraseña contiene al menos una letra minúscula
           return Pattern.compile("[a-z]").matcher(password).find();
       }
   */
    public Client login(String email, String password) {

        if (isEmptyOrWhitespace(email) || isEmptyOrWhitespace(password)) {
            throw new IllegalStateException("Correo y contraseña son campos requeridos");
        }
        List<Client> existingUserByCount = clientRepository.findByEmail(email);
        if (!existingUserByCount.isEmpty()) {
            Client useremail = existingUserByCount.get(0);
            // Verificar si la contraseña coincide
            if (useremail.getPassword().equals(password)) {
                // Las credenciales son válidas
                return useremail;
            } else {
                throw new IllegalStateException("contraseña incorrecta");
            }
        } else {
            throw new IllegalStateException("Correo y contraseña incorrectas");
        }
    }
}