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
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Controller for Film operations.
 */
@RestController
@RequestMapping("/films")
@Slf4j
public final class FilmController {

    /** Memory storage for films. */
    private final Map<Integer, Film> films = new ConcurrentHashMap<>();
    /** ID generator. */
    private final AtomicInteger generatedId = new AtomicInteger(0);

    /**
     * Creates a film.
     * @param film film.
     * @return the created film.
     */
    @PostMapping
    public Film create(@Valid @RequestBody final Film film) {
        log.info("Received request to create film: {}", film);
        film.setId(generatedId.incrementAndGet());
        films.put(film.getId(), film);
        log.info("Film created: {}", film);
        return film;
    }

    /**
     * Updates a film.
     * @param film film.
     * @return the updated film.
     */
    @PutMapping
    public Film update(@Valid @RequestBody final Film film) {
        log.info("Received request to update film: {}", film);
        if (!films.containsKey(film.getId())) {
            log.error("Film with id {} not found", film.getId());
            throw new ValidationException("Film not found to update");
        }
        films.put(film.getId(), film);
        log.info("Film updated: {}", film);
        return film;
    }

    /**
     * Finds all films.
     * @return list of films.
     */
    @GetMapping
    public List<Film> findAll() {
        log.info("Retrieving all films. Total films: {}", films.size());
        return new ArrayList<>(films.values());
    }
}
