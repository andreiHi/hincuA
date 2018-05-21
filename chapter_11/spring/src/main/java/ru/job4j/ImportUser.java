package ru.job4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.models.User;
import ru.job4j.storage.JdbcStorage;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class ImportUser {
    private static final Logger LOG = LogManager.getLogger(ImportUser.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        JdbcStorage storage = context.getBean(JdbcStorage.class);
        storage.createTable();
        System.out.println("================Select All==============");
        storage.getAll().forEach(System.out::println);
        System.out.println("================Create User==============");
        long id = storage.create(new User(35, "Andrei"));
        User user = storage.read(id);
        System.out.println(user);
        System.out.println("================Next Query==============");
        storage.getAll().forEach(System.out :: println);
        System.out.println("================Next Query==============");
        boolean update = storage.update(new User(id, 33,  "Petr"));
        if (update) {
            System.out.println("user was update successful");
        } else {
            System.out.println("user dose not exist");
        }
        storage.getAll().forEach(System.out::println);
        System.out.println("================Clear Table==============");
        storage.clear();
    }
}
