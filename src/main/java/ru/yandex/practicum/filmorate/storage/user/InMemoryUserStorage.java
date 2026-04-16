package ru.yandex.practicum.filmorate.storage.user;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In memory user storage.
 */
@Component
public class InMemoryUserStorage implements UserStorage {

    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong generatedId = new AtomicLong(0);

    @Override
    public User create(User user) {
        user.setId(generatedId.incrementAndGet());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(User user) {
        if (!users.containsKey(user.getId())) {
            throw new NotFoundException("User not found to update");
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User getById(Long id) {
        if (!users.containsKey(id)) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        return users.get(id);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
