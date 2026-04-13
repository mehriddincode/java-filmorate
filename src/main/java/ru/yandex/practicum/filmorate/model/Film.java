package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.yandex.practicum.filmorate.validation.MinimumDate;

import java.time.LocalDate;

/**
 * Film model representing a movie.
 */
@Data
public final class Film {
    /** Maximum description length. */
    private static final int MAX_DESCRIPTION_LENGTH = 200;

    /** Film ID. */
    private int id;

    /** Film name. */
    @NotBlank(message = "Name cannot be blank")
    private String name;

    /** Film description. */
    @Size(max = MAX_DESCRIPTION_LENGTH,
          message = "Maximum desc length is 200 chars")
    private String description;

    /** Film release date. */
    @MinimumDate(value = "1895-12-28",
                 message = "Release date cannot be earlier than min")
    private LocalDate releaseDate;

    /** Film duration. */
    @Positive(message = "Duration must be positive")
    private int duration;
}
