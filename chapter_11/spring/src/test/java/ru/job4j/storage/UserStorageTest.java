package ru.job4j.storage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.models.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 07.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
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
        assertThat(id, is(1));

    }
}