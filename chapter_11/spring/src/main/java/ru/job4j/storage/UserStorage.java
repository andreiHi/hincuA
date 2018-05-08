package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.job4j.models.User;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 07.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Component
public class UserStorage  {

    private final Storage storage;

    @Autowired
    public UserStorage(@Qualifier("memoryStorage") final Storage storage) {
        this.storage = storage;
    }

    public long create(User user) {
        System.out.println("User saved");
       return this.storage.create(user);
    }

    public User read(long id) {
        return (User) this.storage.read(id);
    }

    public List<User> getAll() {
        return this.storage.getAll();
    }

    public boolean update(User user) {
        return this.storage.update(user);
    }

    public boolean delete(User user) {
        return this.storage.delete(user);
    }

    public boolean delete(long id) {
        return this.storage.delete(id);
    }
}
