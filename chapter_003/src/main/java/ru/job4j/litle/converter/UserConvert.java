package ru.job4j.litle.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * UserConvert.
 * @author Hincu Andrei (andreih1981@gmail.com) by 17.09.17;
 * @version $Id$
 * @since 0.1
 */
public class UserConvert {
    /**
     *Метод принимает в себя список пользователей
     * и конвертирует его в Map с ключом Integer id и соответствующим ему User.
     * @param list список пользователей.
     * @return Map с ключом id и значением соответствующего User.
     */
    public HashMap<Integer, User> process(List<User> list) {
    HashMap<Integer, User> map = new HashMap<>();
        for (User user : list) {
            int key = user.getId();
            map.put(key, user);
        }

    return map;
    }
    public Map<Integer, User> process2(List<User> list) {
    return list.stream()
            .collect(Collectors.toMap(User::getId, user -> user));
    }

    public Map<Integer, User> convert(List<User> list) {
        return list.stream()
                .collect(Collectors
                        .toMap(User::getId, //    @param keyMapper a mapping function to produce keys
                                user -> user, // @param valueMapper a mapping function to produce values
                                (a, b) -> a, //  @param mergeFunction a merge function, used to resolve collisions between  values associated with the same key,
                                HashMap::new //  @param mapFactory  a supplier providing a new empty {@code Map}
                                             //   into which the results will be inserted
                        ));
    }
    public HashMap<Integer, User> process3(List<User> list) {
        return (HashMap<Integer, User>) list.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
    }
}
