package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In memory film storage.
 */
@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Long, Film> films = new ConcurrentHashMap<>();
    private final AtomicLong generatedId = new AtomicLong(0);

    @Override
    public Film create(Film film) {
        film.setId(generatedId.incrementAndGet());
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film update(Film film) {
        if (!films.containsKey(film.getId())) {
            throw new NotFoundException("Film not found to update");
        }
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film getById(Long id) {
        if (!films.containsKey(id)) {
            throw new NotFoundException("Film with id " + id + " not found");
        }
        return films.get(id);
    }

    @Override
    public List<Film> findAll() {
        return new ArrayList<>(films.values());
    }
}
