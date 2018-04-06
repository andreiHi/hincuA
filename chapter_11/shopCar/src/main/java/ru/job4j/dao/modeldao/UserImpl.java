package ru.job4j.dao.modeldao;

import org.hibernate.query.Query;
import ru.job4j.dao.AbstractController;
import ru.job4j.model.User;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UserImpl extends AbstractController<User, Long> {

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return (List<User>) getCurrentSession().createQuery("from User").list();
    }

    @Override
    public User getEntityById(Long id) {
        return (User) getCurrentSession().get(User.class, id);
    }

    @Override
    public boolean delete(Long id) {
        boolean flag = false;
        User user = getEntityById(id);
        if (user != null) {
            flag = true;
            getCurrentSession().delete(user);
        }
        return flag;
    }

    @Override
    public void delete(User user) {
        getCurrentSession().delete(user);
    }

    @Override
    public Long save(User user) {
        return (Long) getCurrentSession().save(user);
    }

    @Override
    public void update(User user) {
        getCurrentSession().update(user);
    }

    public String saveIfValid(User user) {
        Query<User> query =  getCurrentSession()
                .createQuery("from User where login = :login or email = :email or phone = :phone", User.class);
        query.setParameter("login", user.getLogin());
        query.setParameter("email", user.getEmail());
        query.setParameter("phone", user.getPhone());
        String answer;
        List<User> users = query.list();
        if (users.isEmpty()) {
            getCurrentSession().save(user);
            answer = "ok";
        } else {
            answer = users.get(0).findEquals(user);
        }
        return answer;
    }

    public User getUserByLogin(String login) {
        User user;
        Query<User> query = getCurrentSession().createQuery("from User where login = :login", User.class);
        query.setParameter("login", login);
        List<User> users = query.list();
        if (users.isEmpty()) {
            user = User.UNKNOWN_USER;
        } else {
            user = users.get(0);
        }
        return user;
    }
//    @SuppressWarnings("unchecked")
//    @Override
//    public List<User> getAll() {
//        return transaction(session -> session.createQuery("from User").list());
//    }
//
//    @Override
//    public User getEntityById(Long id) {
//        return transaction(session -> session.get(User.class, id));
//    }
//
//    @Override
//    public boolean delete(Long id) {
//        return transaction(session -> {
//            boolean found = false;
//            User user = UserImpl.this.getEntityById(id);
//            if (user != null) {
//                session.delete(user);
//                found = true;
//            }
//            return found;
//        });
//    }
}
