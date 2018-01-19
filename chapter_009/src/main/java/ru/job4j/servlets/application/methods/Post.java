package ru.job4j.servlets.application.methods;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.application.UserStore;
import ru.job4j.servlets.crud.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * Добавление нового пользователя.
 * @author Hincu Andrei (andreih1981@gmail.com)on 14.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Post extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(Post.class);
    private  final UserStore userStore = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append("<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "    <meta charset='UTF-8'>"
                + "    <title>Title</title>"
                + "</head>"
                + "<body>"
                + "<h1 align=center>Добавление нового пользователя.</h1>"
                + "<h3><form action='"
                + req.getContextPath()
                + "/new' method='post' align=center>"
                + "Name :  <input type='text' name='name' placeholder='Name'><br>"
                + "Login : <input type='text' name='login' placeholder='Login'><br>"
                + "E-mail: <input type='email' name='email' placeholder='Email' autocomplete='off'><br>"
                + "<button type='submit'>Добавить</button>"
                + "</form><h3>"
                + "</body>"
                + "</html>");

        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        if (login.isEmpty() || name.isEmpty() || email.isEmpty()) {
            doGet(req, resp);
        } else {
            User user  = new User();
            user.setLogin(login);
            user.setName(name);
            user.setEmail(email);
            user.setCreateDate(Calendar.getInstance());
            boolean add = userStore.addNewUser(user);
            PrintWriter writer = resp.getWriter();
            if (add) {
                writer.append("<!DOCTYPE html>"
                        + "<html lang='en'>"
                        + "<head>"
                        + "    <meta charset='UTF-8'>"
                        + "    <title>Add New User</title>"
                        + "</head>"
                        + "<body>"
                        + " <h2><a href='"
                        + req.getContextPath()
                        + "/users'>Назад.</a></h2>"
                        + "<br/>"
                        + "<h3>Пользователь был успешно добавлен</h3>"
                        + "</body>"
                        + "</html>");
            }
            writer.flush();
        }
    }
}
