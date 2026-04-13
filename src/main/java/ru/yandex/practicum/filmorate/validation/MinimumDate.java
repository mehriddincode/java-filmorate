package ru.yandex.practicum.filmorate.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for minimum date validation.
 */
@Documented
@Constraint(validatedBy = MinimumDateValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MinimumDate {
    /**
     * Message when validation fails.
     * @return message string
     */
    String message() default "Date cannot be earlier than minimum";

    /**
     * Groups.
     * @return classes string
     */
    Class<?>[] groups() default {};

    /**
     * Payload.
     * @return classes payload
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * Minimum date string.
     * @return date string
     */
    String value();
}
