package ru.job4j.storage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.models.User;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 07.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UserStorageTest {


    @Test
    public void whenUserWasSaved() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserStorage storage = context.getBean(UserStorage.class);
        storage.create(new User(1L, "Andrei"));
    }

}