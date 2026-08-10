package ru.yandex.practicum.filmorate.exception;

/**
 * Exception thrown when an object is not found.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
