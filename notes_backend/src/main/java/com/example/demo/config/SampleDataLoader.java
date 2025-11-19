package com.example.notesbackend.config;

import com.example.notesbackend.service.NoteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Seeds sample notes data into the database at startup.
 */
@Component
public class SampleDataLoader implements CommandLineRunner {

    private final NoteService noteService;

    public SampleDataLoader(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    public void run(String... args) {
        // Seed a fixed small dataset only if database is empty; avoids unbounded inserts in CI
        noteService.seedSampleData();
    }
}
