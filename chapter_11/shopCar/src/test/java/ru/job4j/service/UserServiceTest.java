package ru.job4j.service;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.Advert;
import ru.job4j.model.User;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
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
         User user = new User("Andrei", "email@ttt.com", "123458", 1234586);
        Advert advert = new Advert();
        List<Advert> adverts = Arrays.asList(advert);
        user.setAdverts(adverts);
         userService.saveUser(user);
        assertThat("Andrei", is(userService.getAllUsers().get(0).getLogin()));
    }

//    @Test
//    public void createNewAdvert() {
//        UserService userService = new UserService();
//       // Advert advert = new Advert("new Advert2", new User(34L), "Moscou");
//      //  advertController.save(advert);
//        User user = userService.getUserById(43L);
//        System.out.println(user);
//      //  userService.getAllUsers().forEach(System.out::println);
//    }

}