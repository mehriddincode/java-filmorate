package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.filmorate.validation.MinimumDate;

import java.time.LocalDate;

/**
 * Film model representing a movie.
 */
@Getter
@EqualsAndHashCode
public final class Film {
    /** Maximum description length. */
    private static final int MAX_DESCRIPTION_LENGTH = 200;

    /** Film ID. */
    @Setter
    private Long id;

    /** Film name. */
    @NotBlank(message = "Name cannot be blank")
    private final String name;

    /** Film description. */
    @Size(max = MAX_DESCRIPTION_LENGTH,
          message = "Maximum desc length is 200 chars")
    private final String description;

    /** Film release date. */
    @MinimumDate(value = "1895-12-28",
                 message = "Release date cannot be earlier than min")
    private final LocalDate releaseDate;

    /** Film duration. */
    @Positive(message = "Duration must be positive")
    private final int duration;

    /** User IDs who liked the film. */
    private final java.util.Set<Long> likes = new java.util.HashSet<>();

    @JsonCreator
    public Film(@JsonProperty("name") String name,
                @JsonProperty("description") String description,
                @JsonProperty("releaseDate") LocalDate releaseDate,
                @JsonProperty("duration") int duration) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }
}
