package ru.job4j.service;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.Advert;
import ru.job4j.model.User;

import java.util.List;

import static org.junit.Assert.*;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 06.05.18;
 * @version $Id$
 * @since 0.1
 */
public class AdvertServiceTest {
    private AdvertService service;
    @Before
    public void start() {
        service = new AdvertService();
    }

    @Test
    public void whenWasStateChange() throws Exception {
        Advert advert = new Advert();
        long id = service.save(advert);
        boolean state = service.changeState(String.valueOf(id), "SOLD");
        assertTrue(state);
    }
    @Test
    public void whenWasStateChange2() throws Exception {
        Advert advert = new Advert();
        long id = service.save(advert);
        boolean state = service.changeState(String.valueOf(id), "NEW");
        assertFalse(state);
    }

    @Test
    public void whenWasCalledAdvertsByUser() throws Exception {
        Advert advert = new Advert();
        User user = new User("Andrei18", "email181@ttt.com", "1236411", "14551");
        UserService userService = new UserService();
        userService.saveUser(user);
        advert.setUser(user);
        service.save(advert);
        List<Advert> adverts = service.getAdvertsByUser(user);
        assertEquals(advert, adverts.get(0));
    }
}