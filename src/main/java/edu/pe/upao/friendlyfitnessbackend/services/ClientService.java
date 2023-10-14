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

    private final ClientRepository clientRespository;

    public ClientService(ClientRepository clientRespository) {
        this.clientRespository = clientRespository;
    }

    public List<Client> getAllClients(){
        return clientRespository.findAll();
    }

    public Optional<Client> getClientById(Long clientID){
        return clientRespository.findById(clientID);
    }

    public Client addClient(Client clientID){
        return clientRespository.save(clientID);
    }

    public void deleteUserById(Long clientId){
        clientRespository.deleteById(clientId);
    }

}
