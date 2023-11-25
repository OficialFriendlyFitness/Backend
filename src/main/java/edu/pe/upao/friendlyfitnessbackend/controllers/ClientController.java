package edu.pe.upao.friendlyfitnessbackend.controllers;

import edu.pe.upao.friendlyfitnessbackend.Dtos.ClientDTO;
import edu.pe.upao.friendlyfitnessbackend.mappers.LoginRequest;
import edu.pe.upao.friendlyfitnessbackend.mappers.LoginResponse;
import edu.pe.upao.friendlyfitnessbackend.models.Client;
import edu.pe.upao.friendlyfitnessbackend.repositories.ClientRepository;
import edu.pe.upao.friendlyfitnessbackend.services.ClientService;
import edu.pe.upao.friendlyfitnessbackend.util.EncryptionUtil;
import edu.pe.upao.friendlyfitnessbackend.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AuthenticationManager authenticationManager;
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
    private ResponseEntity<ClientDTO> viewProfile(@PathVariable Long clientID) {
        ClientDTO clientDTO = clientService.viewProfile(clientID);

        if (clientDTO != null) {
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception{
        Optional<Client> client = clientRepository.findByEmail(loginRequest.getEmail());
        if(client.isPresent()){
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
                return new LoginResponse(EncryptionUtil.encrypt(jwtTokenUtil.generateToken(client.get())));
            }catch (AuthenticationException e){
                //pass to the throw.
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo y/o contraseña incorrecta");
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