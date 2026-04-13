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

class UserValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldCreateUserWithValidData() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setLogin("testlogin");
        user.setName("Test Name");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailWhenEmailIsBlank() {
        User user = new User();
        user.setEmail("");
        user.setLogin("testlogin");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenEmailIsInvalid() {
        User user = new User();
        user.setEmail("invalid-email");
        user.setLogin("testlogin");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenLoginIsBlank() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setLogin("");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenLoginContainsSpaces() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setLogin("test login");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenBirthdayIsInFuture() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setLogin("testlogin");
        user.setBirthday(LocalDate.now().plusDays(1)); // Future date

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldSucceedWhenBirthdayIsToday() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setLogin("testlogin");
        user.setBirthday(LocalDate.now()); // Today

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());
    }
}
