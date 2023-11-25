package edu.pe.upao.friendlyfitnessbackend.config;

import edu.pe.upao.friendlyfitnessbackend.repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class JwtUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);
    private final ClientRepository clientRepository;

    public JwtUserDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("---loadUserByUsername called.---");
        Optional<edu.pe.upao.friendlyfitnessbackend.models.Client> client = clientRepository.findByEmail(username);
        if(client.isPresent()) {
            return client.get();
        } else {
            throw new UsernameNotFoundException("User "+username+" not found.");
        }
    }
}