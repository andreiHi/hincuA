package ru.job4j.service;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.Advert;
import ru.job4j.model.usersmodels.User;
import ru.job4j.model.car.Car;

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
        boolean state = service.changeState(id, "SOLD");
        assertTrue(state);
    }
    @Test
    public void whenWasStateChange2() throws Exception {
        Advert advert = new Advert();
        long id = service.save(advert);
        boolean state = service.changeState(id, "NEW");
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

    @Test
    public void whenWasCalledGetAll() {
        Advert advert = new Advert();
        Advert advert2 = new Advert();
        User user = new User("Andrei18", "email181@ttt.com", "1236411", "14551");
        UserService userService = new UserService();
        userService.saveUser(user);
        advert.setUser(user);
        service.save(advert);
        service.save(advert2);
        assertTrue(service.getAll().size() > 1);
    }

    @Test
    public void whenWasSelectedByQuery() {
        Advert advert = new Advert();
        User user = new User("Andrei19", "email189@ttt.com", "12364911", "145519");
        UserService userService = new UserService();
        userService.saveUser(user);
        advert.setUser(user);
        long id = service.save(advert);
        List<Advert> adverts = service.getByQuery("from Advert");
        boolean flag = false;
        for (Advert a : adverts) {
            if (a.getId().equals(id)) {
              flag = true;
              break;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void whenWasSelectedByQueryWithJoin() {
        Advert advert = new Advert();
        Car car = new Car();
        User user = new User("Andrei19", "email189@ttt.com", "12364911", "145519");
        UserService userService = new UserService();
        userService.saveUser(user);
        advert.setUser(user);
        advert.setCar(car);
        long id = service.save(advert);
        List<Advert> adverts = service.getByQueryWithJoin("from Advert as a join fetch Image as i on i.car.id = a.car.id");
        boolean flag = false;
        for (Advert a : adverts) {
            if (a.getId().equals(id)) {
                flag = true;
                break;
            }
        }
        assertFalse(flag);
    }
}