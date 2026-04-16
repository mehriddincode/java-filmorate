package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.List;

/**
 * Controller for Film operations.
 */
@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public Film create(@Valid @RequestBody final Film film) {
        log.info("Received request to create film: {}", film);
        return filmService.create(film);
    }

    @PutMapping
    public Film update(@Valid @RequestBody final Film film) {
        log.info("Received request to update film: {}", film);
        return filmService.update(film);
    }

    @GetMapping
    public List<Film> findAll() {
        log.info("Retrieving all films");
        return filmService.findAll();
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable Long id) {
        log.info("Retrieving film by id: {}", id);
        return filmService.getById(id);
    }

    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable Long id, @PathVariable Long userId) {
        log.info("User {} liking film {}", userId, id);
        filmService.addLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void removeLike(@PathVariable Long id, @PathVariable Long userId) {
        log.info("User {} removing like from film {}", userId, id);
        filmService.removeLike(id, userId);
    }

    @GetMapping("/popular")
    public List<Film> getPopularFilms(@RequestParam(defaultValue = "10") int count) {
        log.info("Retrieving top {} popular films", count);
        return filmService.getPopularFilms(count);
    }
}
