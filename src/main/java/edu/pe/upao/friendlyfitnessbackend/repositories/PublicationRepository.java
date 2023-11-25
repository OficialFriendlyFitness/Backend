package edu.pe.upao.friendlyfitnessbackend.repositories;

import edu.pe.upao.friendlyfitnessbackend.models.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
}
