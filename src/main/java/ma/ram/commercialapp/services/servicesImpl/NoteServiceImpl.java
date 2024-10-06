package ma.ram.commercialapp.services.servicesImpl;

import ma.ram.commercialapp.entities.Client;
import ma.ram.commercialapp.entities.Note;
import ma.ram.commercialapp.repositories.ClientRepository;
import ma.ram.commercialapp.repositories.NoteRepository;
import ma.ram.commercialapp.services.NoteServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteServiceI {
    private final NoteRepository noteRepository;
    private final ClientRepository clientRepository;
    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, ClientRepository clientRepository) {
        this.noteRepository = noteRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Note addNote(Note note, Long clientId) {
        Client client=clientRepository.findById(clientId).orElseThrow(()->new RuntimeException("client not found"));
        client.getNotes().add(note);
        note.setCreationDate(LocalDateTime.now());
        note.setClient(client);
        return noteRepository.save(note);
    }

    @Override
    public List<Note> showAllClientNote(Long clientId) {
        return noteRepository.findAllByClientId(clientId);
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
