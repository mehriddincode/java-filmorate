package ru.yandex.practicum.filmorate.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FilmValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldCreateFilmWithValidData() {
        Film film = new Film("Valid Name", "Valid Description", LocalDate.of(2000, 1, 1), 120);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailWhenNameIsBlank() {
        Film film = new Film("", "Valid Description", LocalDate.of(2000, 1, 1), 120);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenDescriptionIsTooLong() {
        Film film = new Film("Valid Name", "A".repeat(201), LocalDate.of(2000, 1, 1), 120);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldSucceedWhenDescriptionIsExactly200Chars() {
        Film film = new Film("Valid Name", "A".repeat(200), LocalDate.of(2000, 1, 1), 120);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailWhenReleaseDateIsTooOld() {
        Film film = new Film("Valid Name", "Valid Description", LocalDate.of(1895, 12, 27), 120);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldSucceedWhenReleaseDateIsExactlyBoundary() {
        Film film = new Film("Valid Name", "Valid Description", LocalDate.of(1895, 12, 28), 120);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailWhenDurationIsNegative() {
        Film film = new Film("Valid Name", "Valid Description", LocalDate.of(2000, 1, 1), -1);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenDurationIsZero() {
        Film film = new Film("Valid Name", "Valid Description", LocalDate.of(2000, 1, 1), 0);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }
}
