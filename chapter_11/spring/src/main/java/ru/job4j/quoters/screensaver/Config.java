package ru.job4j.quoters.screensaver;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.*;

import java.awt.*;
import java.util.Random;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.08.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Configuration
@ComponentScan(basePackages = "ru.job4j.quoters.screensaver")
public class Config {
    private static final Logger LOG = LogManager.getLogger(Config.class);

    @Bean
    @Scope(value = "periodical")
    public Color color() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    @Bean
    public ColorFrame frame() {
        return new ColorFrame() {
            @Override
            protected Color getColor() {
                return color();
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        while (true) {
            context.getBean(ColorFrame.class).showOnRandomPlace();
            Thread.sleep(100);
        }
    }
}
