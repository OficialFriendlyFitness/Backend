package edu.pe.upao.friendlyfitnessbackend.repositories;

import edu.pe.upao.friendlyfitnessbackend.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("select u from Client u where u.email = :email")

    Optional<Client> findByEmail(String email);
}