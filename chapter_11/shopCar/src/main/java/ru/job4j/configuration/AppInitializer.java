package ru.job4j.configuration;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import ru.job4j.dao.modeldao.UserServiceImpl;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 *
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class AppInitializer implements WebApplicationInitializer {
    private static final Logger LOG = LogManager.getLogger(AppInitializer.class);

    @Override
    public void onStartup(ServletContext context) {
        WebApplicationContext webContext = createContext(context);
        context.addListener(new ContextLoaderListener(webContext));
        registeredDispatcher(context, webContext);
        registeredFilters(context, webContext);
        StartingData start = new StartingData();
      //  start.initTables();
        UserServiceImpl userService = new UserServiceImpl();
//        userService.getAll().forEach(System.out::println);
        String fullSavePath = start.createUploadPath(context.getRealPath(""));
        context.setAttribute("fullSavePath", fullSavePath);
        LOG.info("Start of initialize...");
    }

    private void registeredDispatcher(ServletContext context, WebApplicationContext webContext) {
        ServletRegistration.Dynamic registration = context.addServlet("dispatcher", new DispatcherServlet(webContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }

    private WebApplicationContext createContext(ServletContext context) {
        AnnotationConfigWebApplicationContext config = new AnnotationConfigWebApplicationContext();
        config.register(JpaConfiguration.class);
        config.scan("ru.job4j.configuration");
        config.setServletContext(context);
//        config.refresh();
        return config;
    }

    private void registeredFilters(ServletContext context, WebApplicationContext webContext) {
        registeredFilter(context, new CharacterEncodingFilter("UTF-8", true));
    }

    private void registeredFilter(ServletContext container, Filter filter) {
        String filterName = filter.getClass().getSimpleName();
        container.addFilter(filterName, filter)
                .addMappingForUrlPatterns(null, true, "/*");
    }
}
