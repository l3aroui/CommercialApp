package ma.ram.commercialapp.repositories;

import ma.ram.commercialapp.entities.commercial.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Page<Client> findAllByCommercialId(Long id, Pageable pageable);
}