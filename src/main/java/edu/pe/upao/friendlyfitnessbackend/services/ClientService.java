package edu.pe.upao.friendlyfitnessbackend.services;
import edu.pe.upao.friendlyfitnessbackend.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import edu.pe.upao.friendlyfitnessbackend.models.Client;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRespository) {
        this.clientRepository = clientRespository;
    }

    public Client addClient(Client clientID){
        return clientRepository.save(clientID);
    }

    public Client verifyAccount(String email, String password) {

        Optional<Client> optionalClient = clientRepository.findByEmail(email);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            // Verificar si la contraseña coincide
            if (client.getPassword().equals(password)) {
                // Las credenciales son válidas
                return client;
            }
        }
        // Si no se encontró el usuario o las credenciales no coinciden, devuelve null
        return null;
    }
}
