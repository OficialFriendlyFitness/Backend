package edu.pe.upao.friendlyfitnessbackend.controllers;

import edu.pe.upao.friendlyfitnessbackend.models.Client;
import edu.pe.upao.friendlyfitnessbackend.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> addClient(@RequestBody Client client){
        try{
            String newClient = clientService.addClient(client);
            return new ResponseEntity<>(newClient, HttpStatus.CREATED);
        } catch (IllegalStateException sms){
            return new ResponseEntity<>(sms.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{clientID}")
    private ResponseEntity<Client> ViewProfile(@PathVariable Long clientID){
        Client client = clientService.viewProfile(clientID);

        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
        try {
            String email = loginRequest.get("email");
            String password = loginRequest.get("password");
            Client loginUser = clientService.login(email, password);
            return new ResponseEntity<>(loginUser, HttpStatus.OK);
        }catch (IllegalStateException sms){
            return new ResponseEntity<>(sms.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<String> updateClient(@RequestBody Client updatedClient, @PathVariable Long clientId) {
        try {
            String result = clientService.updateClient(updatedClient, clientId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}