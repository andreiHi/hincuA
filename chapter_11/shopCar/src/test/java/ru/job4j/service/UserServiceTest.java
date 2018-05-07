package ru.job4j.service;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.User;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UserServiceTest {

    private UserService userService;
    @Before
    public void init() {
        userService = new UserService();
    }

    @Test
    public void whenUserWasAdd() {
        User user = new User("Andrei", "email@ttt.com", "123458", "1234586");
        long id = userService.saveUser(user);
        assertThat("Andrei", is(userService.getUserById(id).getLogin()));
    }

    @Test
    public void whenUserWasUpdate() {
        User user = new User("Andrei1", "email1@ttt.com", "1213458", "12134586");
        Long id = userService.saveUser(user);
        user.setId(id);
        user.setLogin("Petr");
        userService.updateUser(user);
        assertThat("Petr", is(userService.getUserById(id).getLogin()));
    }

    @Test
    public void whenUserWasFind() {
        User user = new User("Andrei2", "email2@ttt.com", "1232458", "12324586");
        Long id = userService.saveUser(user);
        User user1 = userService.getUserById(id);
        assertThat(user1.getId(), is(user.getId()));
    }

    @Test
    public void whenWasFindAllUsers() throws Exception {
        User user = new User("Andrei3", "email3@ttt.com", "123245", "324586");
        User user2 = new User("Andrei4", "email4@ttt.com", "12324", "32458");
        userService.saveUser(user);
        userService.saveUser(user2);
        List<User> users = userService.getAllUsers();
        assertTrue(users.size() > 0);
    }

    @Test
    public void whenUserWasDeletedById() throws Exception {
        User user = new User("Andrei5", "email5@ttt.com", "123241", "32458651");
        long id = userService.saveUser(user);
        boolean success = userService.deleteUser(id);
        assertThat(success, is(true));

    }

    @Test
    public void whnUserWasDeletedByLogin() throws Exception {
        User user = new User("Andrei6", "email6@ttt.com", "123641", "362458651");
        userService.saveUser(user);
        userService.deleteUser(user);
        User user1 = userService.getUserByLogin("Andrei6");
        assertThat(user1, is(User.UNKNOWN_USER));
    }

    @Test
    public void whenWasSavedNewUser() throws Exception {
        User user = new User("Andrei168", "email168@ttt.com", "12836411", "3114588651");
        String ok = userService.saveIfValid(user);
        assertThat(ok, is("ok"));
    }
    @Test
    public void whenWasSavedUserAndLoginExist() throws Exception {
        User user = new User("Andrei17", "email17@ttt.com", "123641111", "11458651");
        User user1 = new User("Andrei17", "email18@ttt.com", "123641111", "1458651");
        String ok = userService.saveIfValid(user);
        String ok2 = userService.saveIfValid(user1);
        assertFalse(ok.equalsIgnoreCase(ok2));
    }

}