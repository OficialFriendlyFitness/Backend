package edu.pe.upao.friendlyfitnessbackend.controllers;


import edu.pe.upao.friendlyfitnessbackend.models.Client;
import edu.pe.upao.friendlyfitnessbackend.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/login")
    public ResponseEntity<ApiComment> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        Client client = clientService.verifyAccount(email, password);

        if (client != null) {
            String comment = "Sesión Iniciada, Bienvenido -> " + client.getFirstName() + client.getLastName();
            ApiComment res = new ApiComment(comment, client);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}


