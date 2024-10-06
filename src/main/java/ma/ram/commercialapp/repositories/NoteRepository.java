package ma.ram.commercialapp.repositories;

import ma.ram.commercialapp.entities.Commercial;
import ma.ram.commercialapp.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByClientId(Long ClientId);
}