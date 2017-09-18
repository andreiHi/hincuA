package ru.job4j.litle.user;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * SortUserTest.
 * @author Hincu Andrei (andreih1981@gmail.com) by 18.09.17;
 * @version $Id$
 * @since 0.1
 */
public class SortUserTest {
    /**
     * Test.
     */
    @Test
    public void whenAddThreeUsersThenUsersSortedByAge() {
        SortUser sortUser = new SortUser();
        ArrayList<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(new User("Vasea",55),
                new User("Kolea",33),
                new User("Dasha",25))
        );
        TreeSet<User> result = (TreeSet<User>) sortUser.sort(list);
        System.out.println(result);
        Set ex = new TreeSet<User>();
        ex.addAll(Arrays.asList( new User("Dasha",25),
                new User("Kolea",33),
                new User("Vasea",55)));
        assertThat(result, is(ex));
    }
}
