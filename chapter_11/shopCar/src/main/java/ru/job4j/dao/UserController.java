package ru.job4j.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import ru.job4j.model.User;
import ru.job4j.service.HibernateService;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UserController extends AbstractController<User, Integer> {
    private static final Logger LOG = LogManager.getLogger(UserController.class);
    private Session session = HibernateService.getSession();

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getEntityById(Integer id) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }
}
