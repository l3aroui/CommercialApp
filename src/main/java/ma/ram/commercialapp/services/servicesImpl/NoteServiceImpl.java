package ma.ram.commercialapp.services.servicesImpl;

import ma.ram.commercialapp.entities.Client;
import ma.ram.commercialapp.entities.Note;
import ma.ram.commercialapp.repositories.NoteRepository;
import ma.ram.commercialapp.services.NoteServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NoteServiceImpl implements NoteServiceI {
    private final NoteRepository noteRepository;
    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    @Override
    public Note addNote(Note note, Client client) {
        client.getNotes().add(note);
        return noteRepository.save(note);
    }
    @Override
    public Note updateNote(Note note) {
        Note updatedNote=noteRepository.findById(note.getId()).orElseThrow(()->new RuntimeException("note not found"));
        updatedNote.setNote(note.getNote());
        updatedNote.setCreationDate(LocalDateTime.now());
        noteRepository.save(updatedNote);
        return updatedNote;
    }
    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
