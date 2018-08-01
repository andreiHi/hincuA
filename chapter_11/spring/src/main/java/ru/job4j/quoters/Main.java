package ru.job4j.quoters;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 01.08.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context= new ClassPathXmlApplicationContext("spring-context.xml");
        while (true) {
            Thread.sleep(100);
            context.getBean(TerminatorQuoter.class).sayQuote();
        }
    }
}
