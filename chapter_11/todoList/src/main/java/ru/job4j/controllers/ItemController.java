package ru.job4j.controllers;

import com.google.gson.Gson;
import org.hibernate.Session;
import ru.job4j.model.Item;
import ru.job4j.service.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        List<Item> items = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        String status = req.getParameter("status");
        System.out.println(status);
        if ("not".equals(status)) {
            session.beginTransaction();
            List<Item> o = session.createQuery("select i from Item i where i.done = false ").list();
            System.out.println(o);
            items.addAll( o);
            System.out.println(items);
            session.close();
        }
        String json = new Gson().toJson(items);
        System.out.println(json);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        pw.append(json);
        pw.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
