package ma.ram.commercialapp.repositories;

import ma.ram.commercialapp.entities.Commercial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommercialRepository extends JpaRepository<Commercial, Long>{
    Commercial findByUsername(String username);
    Optional<Commercial> findByKeycloakId(String keycloakId);
}