package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;
import java.util.List;

/**
 * Interface for Film Storage.
 */
public interface FilmStorage {
    Film create(Film film);
    Film update(Film film);
    Film getById(Long id);
    List<Film> findAll();
}
