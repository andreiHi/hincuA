package ru.job4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

    }
}
