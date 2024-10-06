package ma.ram.commercialapp.controllers;

import ma.ram.commercialapp.entities.Note;
import ma.ram.commercialapp.services.NoteServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {
    private final NoteServiceI noteService;
    @Autowired
    public NoteController(NoteServiceI noteServiceI) {
        this.noteService = noteServiceI;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Note>> listClientNote(@PathVariable("id") Long clientId){
        return ResponseEntity.ok(noteService.showAllClientNote(clientId));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Note> addNoteToClient(@RequestBody Note note,@PathVariable("id") Long clientId){
        return ResponseEntity.ok(noteService.addNote(note,clientId));
    }

    @DeleteMapping("{id}")
    public void deleteNote(@PathVariable("id") Long noteId){
        noteService.deleteNote(noteId);
    }

    @PostMapping("/edit")
    public ResponseEntity<Note> editNote(@RequestBody Note note){
        return ResponseEntity.ok(noteService.updateNote(note));
    }
}
