package ru.job4j.quoters.context;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import ru.job4j.quoters.Quoter;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.08.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class PropertyFileApplicationContext extends GenericApplicationContext {
    private static final Logger LOG = LogManager.getLogger(PropertyFileApplicationContext.class);

    public PropertyFileApplicationContext(String fileName) {
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(this);
        int i = reader.loadBeanDefinitions(fileName);
        System.out.println("found " + i + " Beans");
        refresh();
    }

    public static void main(String[] args) {
        PropertyFileApplicationContext context = new PropertyFileApplicationContext("context.properties");
        context.getBean(Quoter.class).sayQuote();
    }
}
