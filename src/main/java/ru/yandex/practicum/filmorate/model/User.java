package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

/**
 * User model representing a person using the system.
 */
@Data
public final class User {

    /** User ID. */
    private int id;

    /** User email. */
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    /** User login. */
    @NotBlank(message = "Login cannot be blank")
    @Pattern(regexp = "^\\S+$", message = "Login cannot contain spaces")
    private String login;

    /** User name for display. */
    private String name;

    /** User birthday. */
    @PastOrPresent(message = "Birthday cannot be in the future")
    private LocalDate birthday;
}
