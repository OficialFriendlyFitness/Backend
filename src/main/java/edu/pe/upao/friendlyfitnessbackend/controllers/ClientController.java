package edu.pe.upao.friendlyfitnessbackend.controllers;


import edu.pe.upao.friendlyfitnessbackend.models.Client;
import edu.pe.upao.friendlyfitnessbackend.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping
    private List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/{clientID}")
    public Client getClientById(@PathVariable Long clientID){
        return clientService.getClientById(clientID).orElse(new Client());
    }

    @PostMapping
    public void addClient(@RequestBody Client client){
        clientService.addClient(client);
    }

    @DeleteMapping("/{clientID}")
    public void deleteClient(@PathVariable Long clientID){
        clientService.deleteUserById(clientID);
    }

}
