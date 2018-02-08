package ru.job4j.html.controller;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.html.model.User;
import ru.job4j.html.service.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 07.02.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UserController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserController.class);
    DBConnection dbConnection = DBConnection.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        String answer = req.getParameter("select");
        if ("login".equals(answer)) {
            boolean check = dbConnection.checkLogin(req.getParameter("login"));
            String j = new Gson().toJson(check);
            pw.append(j);
        }
        pw.flush();
    }

    @Override
    public void destroy() {
        super.destroy();
        dbConnection.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String country = req.getParameter("country_id");
        String town = req.getParameter("city_id");
        User user = new User(login, name, email, password, country, town, "user", new Timestamp(System.currentTimeMillis()));
        boolean add = dbConnection.addNewUser(user);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        String json = new Gson().toJson(add);
        pw.append(json);
        System.out.println(add);
        pw.flush();
    }
}
