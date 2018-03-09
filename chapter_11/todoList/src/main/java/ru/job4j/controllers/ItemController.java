package ru.job4j.controllers;

import org.hibernate.Session;
import ru.job4j.model.Item;
import ru.job4j.service.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 08.03.18;
 * @version $Id$
 * @since 0.1
 */
public class ItemController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Item> items = session.createQuery("from Item ").list();
        items.forEach(System.out::println);
    }
}
