package com.example.notesbackend.api;

import com.example.notesbackend.model.Note;
import com.example.notesbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * REST controller providing CRUD endpoints for notes.
 */
@RestController
@RequestMapping("/api/notes")
@Tag(name = "Notes", description = "Endpoints for managing notes.")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"}, allowCredentials = "true") // Enable CORS for local FE dev
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "List all notes", description = "Returns a list of all notes.")
    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.findAll();
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "Get note by ID", description = "Returns the note with the specified ID.")
    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable UUID id) {
        return noteService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note with id %s not found".formatted(id)));
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "Create a new note", description = "Creates a new note with title and optional content.")
    @PostMapping
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note) {
        Note created = noteService.create(note);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "Update an existing note", description = "Updates the note with the specified ID.")
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(
            @PathVariable UUID id,
            @Valid @RequestBody Note note
    ) {
        return noteService.update(id, note)
                .map(updated -> ResponseEntity.ok(updated))
                .orElseThrow(() -> new NoSuchElementException("Note with id %s not found".formatted(id)));
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "Delete a note", description = "Deletes the note with the specified ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable UUID id) {
        boolean deleted = noteService.delete(id);
        if (!deleted) {
            throw new NoSuchElementException("Note with id %s not found".formatted(id));
        }
        return ResponseEntity.noContent().build();
    }
}
