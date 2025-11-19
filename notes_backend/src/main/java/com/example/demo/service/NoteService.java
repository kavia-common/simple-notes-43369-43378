package com.example.notesbackend.service;

import com.example.notesbackend.model.Note;
import com.example.notesbackend.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for managing Note entities with validation.
 */
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    // PUBLIC_INTERFACE
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // PUBLIC_INTERFACE
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    // PUBLIC_INTERFACE
    public Optional<Note> findById(UUID id) {
        return noteRepository.findById(id);
    }

    // PUBLIC_INTERFACE
    public Note create(Note note) {
        // Validation is handled by controller using @Valid
        return noteRepository.save(note);
    }

    // PUBLIC_INTERFACE
    public Optional<Note> update(UUID id, Note newNoteData) {
        return noteRepository.findById(id).map(existing -> {
            existing.setTitle(newNoteData.getTitle());
            existing.setContent(newNoteData.getContent());
            return noteRepository.save(existing);
        });
    }

    // PUBLIC_INTERFACE
    public boolean delete(UUID id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // PUBLIC_INTERFACE
    public void seedSampleData() {
        if (noteRepository.count() == 0) {
            Note note1 = new Note("Welcome note", "This is your first note!");
            Note note2 = new Note("Another note", "Spring Boot is running. Enjoy taking notes.");
            noteRepository.save(note1);
            noteRepository.save(note2);
        }
    }
}
