package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * User model representing a person using the system.
 */
@Getter
@EqualsAndHashCode
public final class User {

    /** User ID. */
    @Setter
    private Long id;

    /** User email. */
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private final String email;

    /** User login. */
    @NotBlank(message = "Login cannot be blank")
    @Pattern(regexp = "^\\S+$", message = "Login cannot contain spaces")
    private final String login;

    /** User name for display. */
    private final String name;

    /** User birthday. */
    @PastOrPresent(message = "Birthday cannot be in the future")
    private final LocalDate birthday;

    /** User friends IDs. */
    private final Set<Long> friends = new HashSet<>();

    @JsonCreator
    public User(@JsonProperty("email") String email,
                @JsonProperty("login") String login,
                @JsonProperty("name") String name,
                @JsonProperty("birthday") LocalDate birthday) {
        this.email = email;
        this.login = login;
        this.name = (name == null || name.isBlank()) ? login : name;
        this.birthday = birthday;
    }
}
