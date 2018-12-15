package  ru.job4j.multithreading.chash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;


/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 15.11.17;
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class NonBlockingCash {
    @GuardedBy("this")
    private ConcurrentHashMap<Integer, User> map = new ConcurrentHashMap<>();
    public void add(User user) {
        map.putIfAbsent(user.getId(), user);
    }

    public void delete(User user) {
        map.remove(user.getId());
    }

    public void update(User user) {
        map.computeIfPresent(user.getId(), (k, userM) -> {

            if (user.getVersion() < userM.getVersion()) {
                throw new RuntimeException();
            }
            userM.update(user);
            userM.setVersion(userM.getVersion() + 1);
            return userM;
        });
    }
    public User getValue(int id) {
        return map.get(id);
    }
}

class Main {

    public static void main(String[] args) {

        User user = new User("Vasea", 1);
        NonBlockingCash non = new NonBlockingCash();
        non.add(user);
        User user1 = new User("Petea", 1);
        non.update(user1);
        System.out.println(non.getValue(1));

    }
}
