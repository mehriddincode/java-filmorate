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
        Film film = new Film();
        film.setName("Valid Name");
        film.setDescription("Valid Description");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(120);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailWhenNameIsBlank() {
        Film film = new Film();
        film.setName(""); // Blank
        film.setDescription("Valid Description");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(120);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenDescriptionIsTooLong() {
        Film film = new Film();
        film.setName("Valid Name");
        film.setDescription("A".repeat(201)); // > 200 characters
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(120);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldSucceedWhenDescriptionIsExactly200Chars() {
        Film film = new Film();
        film.setName("Valid Name");
        film.setDescription("A".repeat(200)); // Exactly 200 characters
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(120);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailWhenReleaseDateIsTooOld() {
        Film film = new Film();
        film.setName("Valid Name");
        film.setDescription("Valid Description");
        film.setReleaseDate(LocalDate.of(1895, 12, 27)); // Before 1895-12-28
        film.setDuration(120);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldSucceedWhenReleaseDateIsExactlyBoundary() {
        Film film = new Film();
        film.setName("Valid Name");
        film.setDescription("Valid Description");
        film.setReleaseDate(LocalDate.of(1895, 12, 28)); // Exactly 1895-12-28
        film.setDuration(120);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailWhenDurationIsNegative() {
        Film film = new Film();
        film.setName("Valid Name");
        film.setDescription("Valid Description");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(-1);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenDurationIsZero() {
        Film film = new Film();
        film.setName("Valid Name");
        film.setDescription("Valid Description");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(0);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }
}
