package ma.ram.commercialapp.services;
import ma.ram.commercialapp.entities.Note;

import java.util.List;

public interface NoteServiceI {
    Note addNote(Note note, Long clientId);
    List<Note> showAllClientNote(Long clientId);
    Note updateNote(Note note);
    void deleteNote(Long id);
}
