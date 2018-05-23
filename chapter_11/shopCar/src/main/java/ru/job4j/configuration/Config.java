package ru.job4j.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 23.05.18;
 * @version $Id$
 * @since 0.1
 */
@Configuration
@ComponentScan({
        "ru.job4j.controller",
        "ru.job4j.service"
})
public class Config {

    @Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocations(getResource());
        return configurer;
    }
    private static Resource[] getResource() {
        return new Resource[]{new ClassPathResource("application.properties")};
    }
}
