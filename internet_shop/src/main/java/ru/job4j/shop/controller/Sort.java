package ru.job4j.shop.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 19.02.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Sort extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(Sort.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
