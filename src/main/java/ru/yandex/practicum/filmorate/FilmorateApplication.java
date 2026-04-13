package ru.yandex.practicum.filmorate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class.
 */
@SpringBootApplication
public final class FilmorateApplication {

    /**
     * Default constructor to hide public one.
     */
    private FilmorateApplication() {
    }

    /**
     * Application entry point.
     * @param args arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(FilmorateApplication.class, args);
    }
}
