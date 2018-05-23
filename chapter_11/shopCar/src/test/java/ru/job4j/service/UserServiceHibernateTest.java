package ru.job4j.service;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.usersmodels.User;
import ru.job4j.service.oldservicehibernate.UserServiceHibernate;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UserServiceHibernateTest {

    private UserServiceHibernate userServiceHibernate;
    @Before
    public void init() {
        userServiceHibernate = new UserServiceHibernate();
    }

    @Test
    public void whenUserWasAdd() {
        User user = new User("Andrei", "email@ttt.com", "123458", "1234586");
        long id = userServiceHibernate.saveUser(user);
        assertThat("Andrei", is(userServiceHibernate.getUserById(id).getLogin()));
    }

    @Test
    public void whenUserWasUpdate() {
        User user = new User("Andrei1", "email1@ttt.com", "1213458", "12134586");
        Long id = userServiceHibernate.saveUser(user);
        user.setId(id);
        user.setLogin("Petr");
        userServiceHibernate.updateUser(user);
        assertThat("Petr", is(userServiceHibernate.getUserById(id).getLogin()));
    }

    @Test
    public void whenUserWasFind() {
        User user = new User("Andrei2", "email2@ttt.com", "1232458", "12324586");
        Long id = userServiceHibernate.saveUser(user);
        User user1 = userServiceHibernate.getUserById(id);
        assertThat(user1.getId(), is(user.getId()));
    }

    @Test
    public void whenWasFindAllUsers() throws Exception {
        User user = new User("Andrei3", "email3@ttt.com", "123245", "324586");
        User user2 = new User("Andrei4", "email4@ttt.com", "12324", "32458");
        userServiceHibernate.saveUser(user);
        userServiceHibernate.saveUser(user2);
        List<User> users = userServiceHibernate.getAllUsers();
        assertTrue(users.size() > 0);
    }

    @Test
    public void whenUserWasDeletedById() throws Exception {
        User user = new User("Andrei5", "email5@ttt.com", "123241", "32458651");
        long id = userServiceHibernate.saveUser(user);
        boolean success = userServiceHibernate.deleteUser(id);
        assertThat(success, is(true));

    }

    @Test
    public void whnUserWasDeletedByLogin() throws Exception {
        User user = new User("Andrei6", "email6@ttt.com", "123641", "362458651");
        userServiceHibernate.saveUser(user);
        userServiceHibernate.deleteUser(user);
        User user1 = userServiceHibernate.getUserByLogin("Andrei6");
        assertThat(user1, is(User.UNKNOWN_USER));
    }

    @Test
    public void whenWasSavedNewUser() throws Exception {
        User user = new User("Andrei168", "email168@ttt.com", "12836411", "3114588651");
        String ok = userServiceHibernate.saveIfValid(user);
        assertThat(ok, is("ok"));
    }
    @Test
    public void whenWasSavedUserAndLoginExist() throws Exception {
        User user = new User("Andrei17", "email17@ttt.com", "123641111", "11458651");
        User user1 = new User("Andrei17", "email18@ttt.com", "123641111", "1458651");
        String ok = userServiceHibernate.saveIfValid(user);
        String ok2 = userServiceHibernate.saveIfValid(user1);
        assertFalse(ok.equalsIgnoreCase(ok2));
    }

}