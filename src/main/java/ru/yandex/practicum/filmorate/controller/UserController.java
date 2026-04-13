package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for User operations.
 */
@RestController
@RequestMapping("/users")
@Slf4j
public final class UserController {

    /** Memory storage for users. */
    private final Map<Integer, User> users = new HashMap<>();
    /** ID generator for user. */
    private int generatedId = 0;

    /**
     * Creates a user.
     * @param user user.
     * @return the created user.
     */
    @PostMapping
    public User create(@Valid @RequestBody final User user) {
        log.info("Received request to create user: {}", user);
        validateUser(user);
        user.setId(++generatedId);
        users.put(user.getId(), user);
        log.info("User created: {}", user);
        return user;
    }

    /**
     * Updates an existing user.
     * @param user user.
     * @return the updated user.
     */
    @PutMapping
    public User update(@Valid @RequestBody final User user) {
        log.info("Received request to update user: {}", user);
        if (!users.containsKey(user.getId())) {
            log.error("User with id {} not found", user.getId());
            throw new ValidationException("User not found to update");
        }
        validateUser(user);
        users.put(user.getId(), user);
        log.info("User updated: {}", user);
        return user;
    }

    /**
     * Finds all users.
     * @return collection of users.
     */
    @GetMapping
    public Collection<User> findAll() {
        log.info("Retrieving all users. Total users: {}", users.size());
        return users.values();
    }

    private void validateUser(final User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            log.info("User name blank. Using login: {}", user.getLogin());
            user.setName(user.getLogin());
        }
    }
}
