package com.example.notesbackend.repository;

import com.example.notesbackend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * JPA repository for Note entities.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {
    // Default CRUD operations provided by JpaRepository
}
