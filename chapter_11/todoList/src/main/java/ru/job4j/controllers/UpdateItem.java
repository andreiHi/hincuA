package ru.job4j.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.model.Item;
import ru.job4j.service.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 11.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UpdateItem extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UpdateItem.class);
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String done = req.getParameter("setDone");
        int id = Integer.valueOf(req.getParameter("id"));
        System.out.println(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Item item = session.load(Item.class, new Integer(id));
        if ("true".equals(done)) {
            item.setDone(true);
        } else {
            item.setDone(false);
        }
        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void destroy() {
        super.destroy();
        sessionFactory.close();
    }
}
