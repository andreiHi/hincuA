package ru.job4j.quoters.screensaver;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.08.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Component
public class CustomScopeRegistryBeanFactoryPostProcessor implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerScope("periodical", new PeriodicalScopeConfigurer());
    }
}
