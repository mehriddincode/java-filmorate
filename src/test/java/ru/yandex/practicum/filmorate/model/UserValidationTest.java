package ru.yandex.practicum.filmorate.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        User user = new User("test@test.com", "testlogin", "Test Name", LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"invalid-email"})
    void shouldFailWhenEmailIsInvalid(String email) {
        User user = new User(email, "testlogin", "Test Name", LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"test login"})
    void shouldFailWhenLoginIsInvalid(String login) {
        User user = new User("test@test.com", login, "Test Name", LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void shouldUseLoginAsNameWhenNameIsBlank(String name) {
        User user = new User("test@test.com", "testlogin", name, LocalDate.of(2000, 1, 1));

        assertEquals("testlogin", user.getName());
    }

    @ParameterizedTest
    @MethodSource("birthdayCases")
    void shouldValidateBirthday(LocalDate birthday, boolean expectedValid) {
        User user = new User("test@test.com", "testlogin", "Test Name", birthday);

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(expectedValid, violations.isEmpty());
    }

    private static Stream<Arguments> birthdayCases() {
        return Stream.of(
                Arguments.of(LocalDate.now().plusDays(1), false),
                Arguments.of(LocalDate.now(), true),
                Arguments.of(LocalDate.of(1990, 1, 1), true)
        );
    }
}
