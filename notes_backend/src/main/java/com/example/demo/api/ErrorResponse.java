package com.example.notesbackend.api;

/**
 * Standard error response for API errors.
 */
public class ErrorResponse {
    private String error;
    private String message;

    // PUBLIC_INTERFACE
    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    // Getters and setters

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
