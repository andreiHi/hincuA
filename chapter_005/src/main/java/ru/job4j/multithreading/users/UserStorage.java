package ru.job4j.multithreading.users;

import ru.job4j.multithreading.users.exeptions.CanNotAddOrUpdateOrDeleteUserException;

import java.util.HashMap;
import java.util.Map;

/**
 * Хранилище пользователей.
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 08.11.17;
 * @version $Id$
 * @since 0.1
 */
public class UserStorage {
    public UserStorage() {
        this.storage = new HashMap<>();
    }

    /**
     * Для хранения данных используем HashMap;
     */
    private final Map<Integer, User> storage;

    /**
     * Метод добовляет нового пользователя прредварительно
     * проверив отсутствие в хранилище пользователя с таким же id
     * @param user пользователь.
     */
    public void add(User user) {
        User u;
        u = storage.putIfAbsent(user.getId(), user);
        if (u != null) {
            throw new CanNotAddOrUpdateOrDeleteUserException("Данный пользователь уже есть в базе данных");
        }
    }

    /**
     * Метод обновляет пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        final int id = user.getId();
        if (storage.containsKey(id)) {
            storage.put(id, user);
        } else {
            throw new CanNotAddOrUpdateOrDeleteUserException("Данного пользователя нет в базе данных.");
        }
    }

    public Map<Integer, User> getStorage() {
        return storage;
    }

    /**
     * Метод удаляет пользователя из хранилища.
     * @param user пользователь.
     */
    public void delete(User user) {
        int id = user.getId();
        if (storage.containsKey(id)) {
            storage.remove(id, user);
        } else {
            throw new CanNotAddOrUpdateOrDeleteUserException("Данного пользователя нет в базе данных.");
        }
    }
    public void transfer(int fromId, int toId, int amaunt) throws InterruptedException {
        User from = storage.get(fromId);
        User to = storage.get(toId);
        if (fromId > toId) {
            synchronized (from) {
                Thread.sleep(1000);
                synchronized (to) {
                    if (from.getAmount() >= amaunt) {
                        from.setAmount(from.getAmount() - amaunt);
                        to.setAmount(to.getAmount() + amaunt);
                    }
                }
            }
        } else {
            synchronized (to) {
                Thread.sleep(1000);
                synchronized (from) {
                    if (from.getAmount() >= amaunt) {
                        from.setAmount(from.getAmount() - amaunt);
                        to.setAmount(to.getAmount() + amaunt);
                    }
                }
            }
        }
    }
}
