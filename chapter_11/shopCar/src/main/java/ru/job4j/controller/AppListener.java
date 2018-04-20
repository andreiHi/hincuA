package ru.job4j.controller;

import ru.job4j.dao.HibernateService;
import ru.job4j.dao.StartingData;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 07.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class AppListener implements ServletContextListener {
    /**
     * Insert initial data in DB when application start first time.
     * @param servletContextEvent servletContextEvent instance of this application.
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        StartingData start = new StartingData();
        start.initTables();
        String fullSavePath = start.createUploadPath(servletContextEvent.getServletContext().getRealPath(""));
        servletContextEvent.getServletContext().setAttribute("fullSavePath", fullSavePath);
    }

    /**
     * Close hibernate session factory when application is closed.
     * @param servletContextEvent servletContextEvent instance of this application.
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        HibernateService.closeFactory();
    }
}
