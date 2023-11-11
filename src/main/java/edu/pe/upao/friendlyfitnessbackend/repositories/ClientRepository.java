package edu.pe.upao.friendlyfitnessbackend.repositories;

import edu.pe.upao.friendlyfitnessbackend.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByEmail(String email);
}