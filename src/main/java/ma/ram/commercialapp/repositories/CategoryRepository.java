package ma.ram.commercialapp.repositories;

import ma.ram.commercialapp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<List<Category>> findAllByCommercialId(Long id);

}