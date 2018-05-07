package ru.job4j.storage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 07.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class MemoryStorage implements Storage<User> {
    private static final Logger LOG = LogManager.getLogger(MemoryStorage.class);
    private Map<Long, User> users = new HashMap<>();

    @Override
    public long create(User user) {
        this.users.put(user.getId(), user);
        return user.getId();
    }

    @Override
    public User read(long id) {
        return this.users.get(id);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) this.users.values();
    }

    @Override
    public boolean update(User user) {
       User user1 = users.put(user.getId(), user);
       return user1 != null;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
