package ru.job4j.component;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 24.05.2018.
 * @version $Id$.
 * @since 0.1.
 */

public class ApplicationListener implements ServletContextListener {
    private static final Logger LOG = LogManager.getLogger(ApplicationListener.class);
    private static final String SAVE_DIRECTORY = "uploadDir";


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String fullSavePath = createUploadPath(context.getRealPath(""));
        LOG.info(String.format("Full save path was created : %s", fullSavePath));
        context.setAttribute("fullSavePath", fullSavePath);
        LOG.info("Application started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("Application destroyed");
    }

    private String createUploadPath(String appPath) {
        appPath = appPath.replace('\\', '/');
        String fullSavePath;
        if (appPath.endsWith("/")) {
            fullSavePath = appPath + SAVE_DIRECTORY;
        } else {
            fullSavePath = appPath + "/" + SAVE_DIRECTORY;
        }
        File fileSaveDir = new File(fullSavePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        return fullSavePath;
    }
}
