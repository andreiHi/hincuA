package ru.job4j.configuration;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class AppInitializer implements WebApplicationInitializer {
    private static final Logger LOG = LogManager.getLogger(AppInitializer.class);

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        StartingData start = new StartingData();
        start.initTables();
        String fullSavePath = start.createUploadPath(context.getRealPath(""));
        context.setAttribute("fullSavePath", fullSavePath);
        LOG.info("Start of initialize...");
    }
}
