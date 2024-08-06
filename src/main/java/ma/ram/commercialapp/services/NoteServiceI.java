package ma.ram.commercialapp.services;

import ma.ram.commercialapp.entities.Client;
import ma.ram.commercialapp.entities.Commercial;
import ma.ram.commercialapp.entities.Note;

public interface NoteServiceI {
    Note addNote(Note note, Client client);
    Note updateNote(Note note);
    void deleteNote(Long id);
}
