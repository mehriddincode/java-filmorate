package ru.yandex.practicum.filmorate.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Validator for MinimumDate annotation.
 */
public final class MinimumDateValidator
        implements ConstraintValidator<MinimumDate, LocalDate> {

    /** minimum date. */
    private LocalDate minimumDate;

    /**
     * Init.
     * @param constraintAnnotation annotation
     */
    @Override
    public void initialize(final MinimumDate constraintAnnotation) {
        minimumDate = LocalDate.parse(constraintAnnotation.value());
    }

    /**
     * Checks if valid.
     * @param value date value
     * @param context context
     * @return true if valid
     */
    @Override
    public boolean isValid(final LocalDate value,
                           final ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Use @NotNull for null checks
        }
        return !value.isBefore(minimumDate);
    }
}
