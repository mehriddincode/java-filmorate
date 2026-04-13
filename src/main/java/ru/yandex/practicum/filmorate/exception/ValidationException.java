package ru.yandex.practicum.filmorate.exception;

/**
 * Exception thrown when validation fails.
 */
public class ValidationException extends RuntimeException {
    /**
     * Constructor with message.
     * @param message exception message
     */
    public ValidationException(final String message) {
        super(message);
    }
}
