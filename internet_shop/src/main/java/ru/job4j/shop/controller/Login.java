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
public class Login extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(Login.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("reg_login");
        System.out.println(login);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
