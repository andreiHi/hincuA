package ru.job4j.storage;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.models.User;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 07.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Ignore
public class UserStorageTest {
    private UserStorage storage;
    @Before
    public void start() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        this.storage = context.getBean(UserStorage.class);
    }
    @After
    public void after() {
        storage.cear();
    }

    @Test
    public void whenUserWasSaved() {
        long id = storage.create(new User(35, "Andrei"));
        assertThat(id, is(1L));
    }

    @Test
    public void whenWasSavedTwoEqualsUsers() throws Exception {
        long id = storage.create(new User(35, "Petr"));
        long id2 = storage.create(new User(35, "Petr"));
        assertThat(id, is(1L));
        assertThat(id2, is(-1L));
    }

    @Test
    public void whenUserWasUpdate() {
        long id = storage.create(new User(35, "Petr"));
        storage.update(new User(id, 33, "Petr"));
        User user = storage.read(id);
        assertThat(33, is(user.getAge()));
    }

    @Test
    public void whenUserWasDeleted() {
        Long id = storage.create(new User(33, "Andrei"));
        boolean del = storage.delete(id);
        assertTrue(del);
    }
    @Test
    public void whenUserNotFound() {
        Long id = storage.create(new User(33, "Andrei"));
        boolean del = storage.delete(25L);
        assertFalse(del);
    }

    @Test
    public void whenUserWasDeletedByUser() {
        Long id = storage.create(new User(33, "Andrei"));
        User user = new User();
        user.setId(id);
        boolean del = storage.delete(user);
        assertTrue(del);
    }

    @Test
    public void whenSelectedAllUsers() {
        storage.create(new User(35, "Andrei"));
        storage.create(new User(38, "Andrei"));
        List<User> users = storage.getAll();
        assertThat(users.size(), is(2));
    }

    @Test
    public void whenUserForUpdateNotExist() {
        boolean update = storage.update(new User(15L, 33, "Petr"));
        assertFalse(update);
    }
    @Test
    public void whenUserWasDeletedByUserAndUserNotExist() {
        boolean del = storage.delete(new User(15L, 33, "Andrei"));
        assertFalse(del);
    }

    @Test
    public void whenUserNotFoundByName() {
        long id = storage.create(new User(33, "Andrei"));
        long id2 = storage.create(new User(33, "Petr"));
        assertThat(id, is(1L));
        assertThat(id2, is(2L));
    }
}