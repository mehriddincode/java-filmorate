package ru.yandex.practicum.filmorate.controller;

/**
 * Standard error response returned by the API.
 */
public class ErrorResponse {

    /** Short error type description. */
    private final String error;

    /** Detailed error message. */
    private final String message;

    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
