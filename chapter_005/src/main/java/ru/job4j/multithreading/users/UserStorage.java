package ru.job4j.multithreading.users;

import ru.job4j.multithreading.users.exeptions.CanNotAddOrUpdateOrDeleteUserException;

import java.util.ArrayList;
import java.util.List;

/**
 * Хранилище пользователей.
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 08.11.17;
 * @version $Id$
 * @since 0.1
 */
public class UserStorage {
    public UserStorage() {
        this.storage = new ArrayList<>();
    }

    /**
     * Для хранения данных используем Arraylist;
     */
    private final List<User> storage;

    /**
     * Метод добовляет нового пользователя прредварительно
     * проверив отсутствие в хранилище пользователя с таким же id
     * @param user пользователь.
     */
    public void add(User user) {
        final int id = user.getId();
        boolean exist = false;
        for (User u : storage) {
            if (u.getId() == id) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            this.storage.add(user);
        } else {
            throw new CanNotAddOrUpdateOrDeleteUserException("Данный пользователь уже есть в базе данных");
        }

    }

    /**
     * Метод обновляет пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        final int id = user.getId();
        boolean found = false;
        int index = 0;
        for (User u : storage) {
            if (u.getId() == id) {
                index = storage.indexOf(u);
                found = true;
                break;
            }
        }
        if (found) {
            storage.set(index, user);
        } else {
            throw new CanNotAddOrUpdateOrDeleteUserException("Данного пользователя нет в базе данных.");
        }
    }

    public List<User> getStorage() {
        return storage;
    }
    public void delete(User user) {
        boolean fount = false;
        for (int i = 0; i < storage.size(); i++) {
            if (user.getId() == storage.get(i).getId()) {
                storage.remove(i);
                fount = true;
                break;
            }
        }
        if (!fount) {
            throw new CanNotAddOrUpdateOrDeleteUserException("Данного пользователя нет в базе данных.");
        }
    }
}
